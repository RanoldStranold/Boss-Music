package net.ranold.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.ranold.rbm;

public class ModSounds {
    public static final Identifier WITHER_MUSIC_ID = Identifier.of("rbm", "music.wither");
    public static SoundEvent WITHER_MUSIC_EVENT = SoundEvent.of(WITHER_MUSIC_ID);
    public static final Identifier ENDER_DRAGON_MUSIC_ID = Identifier.of("rbm", "music.ender_dragon");
    public static SoundEvent ENDER_DRAGON_MUSIC_EVENT = SoundEvent.of(ENDER_DRAGON_MUSIC_ID);


    public static void registerAll() {
        Registry.register(Registries.SOUND_EVENT, WITHER_MUSIC_ID, WITHER_MUSIC_EVENT);
        Registry.register(Registries.SOUND_EVENT, ENDER_DRAGON_MUSIC_ID, ENDER_DRAGON_MUSIC_EVENT);
    }
}