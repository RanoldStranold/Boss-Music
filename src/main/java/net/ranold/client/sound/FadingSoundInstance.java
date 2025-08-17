package net.ranold.client.sound;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.TickableSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public class FadingSoundInstance extends PositionedSoundInstance implements TickableSoundInstance {

    private static final float FADE_SPEED = 0.05f;
    private float targetVolume;
    private boolean fadingOut = false;
    private boolean done = false;

    public FadingSoundInstance(Identifier soundId, SoundCategory category, float volume, float x, float y, float z) {
        super(soundId, SoundCategory.MUSIC, volume, 1.0f, Random.create(), true, 0, AttenuationType.LINEAR, x, y, z, false);
        this.volume = 0.0f; // Start silent
        this.targetVolume = volume;
    }

    @Override
    public void tick() {
        if (fadingOut) {
            if (this.volume > 0) {
                this.volume -= FADE_SPEED;
                if (this.volume <= 0) {
                    this.volume = 0;
                    this.done = true; // Set done flag
                }
            }
        } else if (this.volume < targetVolume) {
            this.volume += FADE_SPEED;
            if (this.volume > targetVolume) {
                this.volume = targetVolume;
            }
        }
    }

    public void startFadeOut() {
        this.fadingOut = true;
        this.targetVolume = 0.0f;
    }

    public boolean isFadingOut() {
        return this.fadingOut;
    }

    @Override
    public boolean isDone() {
        return this.done;
    }
}