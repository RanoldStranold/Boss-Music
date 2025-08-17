package net.ranold.mixin;

import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.world.World;
import net.ranold.client.manager.BossMusicManager;
import net.ranold.rbm;
import net.ranold.sound.ModSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

@Mixin(WitherEntity.class)
public class WitherEntityMixin {

    // Logic moved to BossMusicTicker
}