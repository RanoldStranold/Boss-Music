package net.ranold.client.manager;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.ranold.client.sound.FadingSoundInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.world.World;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.client.sound.MusicInstance;
import net.ranold.accessor.IMusicTracker;

public class BossMusicManager {

    private static final Logger LOGGER = LogManager.getLogger("BossMusic");
    private final MinecraftClient client;
    private final Map<UUID, FadingSoundInstance> activeBossMusic;

    public BossMusicManager(MinecraftClient client) {
        this.client = client;
        this.activeBossMusic = new HashMap<>();
    }

    public void startBossMusic(Entity boss, Identifier soundId) {
        if (activeBossMusic.containsKey(boss.getUuid())) {
            return; // Music already playing for this boss
        }

        LOGGER.info("Starting music for boss: " + boss.getName().getString());
        client.getMusicTracker().stop();

        FadingSoundInstance soundInstance = new FadingSoundInstance(
                soundId,
                SoundCategory.MUSIC,
                1.0f, // Volume
                (float) boss.getX(),
                (float) boss.getY(),
                (float) boss.getZ()
        );

        activeBossMusic.put(boss.getUuid(), soundInstance);
        client.getSoundManager().play(soundInstance);
    }

    public void stopBossMusic(UUID bossUuid) {
        FadingSoundInstance instance = activeBossMusic.get(bossUuid);
        if (instance != null && !instance.isFadingOut()) {
            LOGGER.info("Fading out music for boss: " + bossUuid);
            instance.startFadeOut();
        }
    }

    public void stopAllBossMusic() {
        LOGGER.info("Stopping all boss music.");
        for (FadingSoundInstance instance : activeBossMusic.values()) {
            instance.startFadeOut();
        }
    }

    public void tick() {
        // Remove sounds that are done fading out
        activeBossMusic.values().removeIf(instance -> {
            if (instance.isDone()) {
                LOGGER.info("Removing finished sound instance.");
                return true;
            }
            return false;
        });

    }

    public boolean isBossMusicPlaying() {
        if (activeBossMusic.isEmpty()) {
            return false;
        }
        for (FadingSoundInstance instance : activeBossMusic.values()) {
            if (!instance.isFadingOut()) {
                return true;
            }
        }
        return false;
    }

    public Map<UUID, FadingSoundInstance> getActiveBossMusic() {
        return activeBossMusic;
    }

    public void clearMusic() {
        activeBossMusic.clear();
    }
}