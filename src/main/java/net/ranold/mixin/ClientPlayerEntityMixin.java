package net.ranold.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.ranold.rbm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "closeScreen", at = @At("HEAD"))
    private void onLeaveWorld(CallbackInfo ci) {
        rbm.bossMusicManager.stopAllBossMusic();
    }
}

