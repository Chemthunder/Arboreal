package org.chemthunder.arboreal.api.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundEvent;

/**
 * @author Chemthunder
 */
public interface CustomConsumeItem {
    ParticleEffect getConsumeParticleEffect(LivingEntity living);

    SoundEvent getConsumeEatSound(LivingEntity living);
}
