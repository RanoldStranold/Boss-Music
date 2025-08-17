package net.ranold.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.ranold.client.manager.BossMusicManager;
import net.ranold.rbm;
import net.ranold.sound.ModSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onPlayerTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.getWorld().getRegistryKey() == World.END) {
            // This logic should be handled by BossMusicTicker
            // rbm.getBossMusicManager().startBossMusic(player, ModSounds.ENDER_DRAGON_MUSIC_EVENT);
        }
    }
}