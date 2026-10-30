package org.chemthunder.arboreal.api.item;


import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;

/**
 * @author Chemthunder
 */
public interface CustomConsumeItem {
    ParticleOptions getConsumeParticleEffect(LivingEntity living);

    SoundEvent getConsumeEatSound(LivingEntity living);
}
