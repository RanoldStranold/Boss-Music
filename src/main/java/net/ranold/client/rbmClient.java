package net.ranold.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.ranold.rbm;
import net.ranold.client.ticker.BossMusicTicker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class rbmClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("rbm-client");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing RBM Client");

        // Register tick events
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            BossMusicTicker.tick();
            rbm.bossMusicManager.tick();
        });

        // Optional: Clean up resources when disconnecting from a server
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            rbm.bossMusicManager.stopAllBossMusic();
            rbm.bossMusicManager.clearMusic();
        });

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            BossMusicTicker.tick();
        });

        LOGGER.info("RBM Client initialized successfully");
    }
}
