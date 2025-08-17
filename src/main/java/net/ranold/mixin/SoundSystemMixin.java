package net.ranold.mixin;

import net.minecraft.client.sound.SoundSystem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SoundSystem.class)
public class SoundSystemMixin {
    // The mixin is now empty as the logic has been moved to MusicTrackerMixin.
}
