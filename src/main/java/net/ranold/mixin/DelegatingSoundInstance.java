package net.ranold.mixin;

import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public class DelegatingSoundInstance implements SoundInstance {

    protected final SoundInstance delegate;

    public DelegatingSoundInstance(SoundInstance delegate) {
        this.delegate = delegate;
    }

    @Override
    public Identifier getId() {
        return delegate.getId();
    }

    @Override
    public WeightedSoundSet getSoundSet(net.minecraft.client.sound.SoundManager soundManager) {
        return delegate.getSoundSet(soundManager);
    }

    @Override
    public Sound getSound() {
        return delegate.getSound();
    }

    @Override
    public SoundCategory getCategory() {
        return delegate.getCategory();
    }

    @Override
    public boolean isRepeatable() {
        return delegate.isRepeatable();
    }

    @Override
    public boolean isRelative() {
        return delegate.isRelative();
    }

    @Override
    public int getRepeatDelay() {
        return delegate.getRepeatDelay();
    }

    @Override
    public float getVolume() {
        return delegate.getVolume();
    }

    @Override
    public float getPitch() {
        return delegate.getPitch();
    }

    @Override
    public double getX() {
        return delegate.getX();
    }

    @Override
    public double getY() {
        return delegate.getY();
    }

    @Override
    public double getZ() {
        return delegate.getZ();
    }

    @Override
    public AttenuationType getAttenuationType() {
        return delegate.getAttenuationType();
    }

    @Override
    public boolean canPlay() {
        return delegate.canPlay();
    }
}