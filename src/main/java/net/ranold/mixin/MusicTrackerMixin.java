package net.ranold.mixin;

import net.ranold.accessor.IMusicTracker;
import net.ranold.rbm;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.MusicInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicTracker.class)
public class MusicTrackerMixin implements IMusicTracker {

    private MusicInstance lastVanillaMusic;

    @Inject(method = "play(Lnet/minecraft/client/sound/MusicInstance;)V", at = @At("HEAD"))
    private void onPlayMusicInstance(MusicInstance music, CallbackInfo ci) {
        this.lastVanillaMusic = music;
    }

    @Inject(method = "tick()V", at = @At("HEAD"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        if (rbm.bossMusicManager != null && rbm.bossMusicManager.isBossMusicPlaying()) {
            ci.cancel();
        }
    }

    @Override
    public MusicInstance getLastVanillaMusic() {
        return this.lastVanillaMusic;
    }
}