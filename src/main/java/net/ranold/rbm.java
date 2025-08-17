package net.ranold;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.ranold.client.manager.BossMusicManager;
import net.ranold.sound.ModSounds;
import net.minecraft.entity.damage.DamageSource;

public class rbm implements ModInitializer {

    public static BossMusicManager bossMusicManager; // Made public static

    @Override
    public void onInitialize() {
        ModSounds.registerAll();

        // Initialize BossMusicManager
        bossMusicManager = new BossMusicManager(MinecraftClient.getInstance());
        // Removed direct reference to SoundSystemMixin.setBossMusicManager

        // Client-side logic is now handled in the BossMusicTicker
    }
}