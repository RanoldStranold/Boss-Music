package net.ranold.client.ticker;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.ranold.client.manager.BossMusicManager;
import net.ranold.rbm;
import net.ranold.sound.ModSounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BossMusicTicker {

    private static final Logger LOGGER = LogManager.getLogger("BossMusicTicker");
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final int START_BOSS_RANGE = 150;
    private static final int STOP_BOSS_RANGE = 170; // Larger range to prevent flickering

    public static void tick() {
        if (client.world == null || client.player == null) {
            if (rbm.bossMusicManager.isBossMusicPlaying()) {
                LOGGER.info("Player left world, stopping all music.");
                rbm.bossMusicManager.stopAllBossMusic();
            }
            return;
        }

        BossMusicManager bossMusicManager = rbm.bossMusicManager;

        // Stop music for out-of-range bosses
        for (UUID bossId : bossMusicManager.getActiveBossMusic().keySet()) {
            Entity activeBoss = null;
            for (Entity entity : client.world.getEntities()) {
                if (entity.getUuid().equals(bossId)) {
                    activeBoss = entity;
                    break;
                }
            }
            if (activeBoss == null || !activeBoss.isAlive() || client.player.distanceTo(activeBoss) > STOP_BOSS_RANGE) {
                LOGGER.info("Boss {} is dead or out of range, stopping music.", bossId);
                bossMusicManager.stopBossMusic(bossId);
            }
        }


        if (!bossMusicManager.isBossMusicPlaying()) {
            // No music is playing, so scan for new bosses using the SMALLER start range
            Box searchBox = new Box(client.player.getBlockPos()).expand(START_BOSS_RANGE);

            // Wither check
            List<WitherEntity> withers = client.world.getEntitiesByClass(WitherEntity.class, searchBox, Entity::isAlive);
            if (!withers.isEmpty()) {
                WitherEntity wither = withers.get(0);
                LOGGER.info("Found Wither, starting music.");
                bossMusicManager.startBossMusic(wither, ModSounds.WITHER_MUSIC_ID);
                return; // Stop after finding one boss
            }

            // Ender Dragon check
            if (client.world.getRegistryKey() == World.END) {
                List<EnderDragonEntity> dragons = client.world.getEntitiesByClass(EnderDragonEntity.class, searchBox, Entity::isAlive);
                if (!dragons.isEmpty()) {
                    EnderDragonEntity dragon = dragons.get(0);
                    LOGGER.info("Found Ender Dragon, starting music.");
                    bossMusicManager.startBossMusic(dragon, ModSounds.ENDER_DRAGON_MUSIC_ID);
                }
            }
        }
    }
}