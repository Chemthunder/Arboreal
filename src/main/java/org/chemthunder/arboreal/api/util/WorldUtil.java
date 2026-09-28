package org.chemthunder.arboreal.api.util;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.chemthunder.arboreal.core.networking.s2c.PlayClientSoundPayload;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * @author Chemthunder
 */
@ApiStatus.NonExtendable
@SuppressWarnings("unused")
public abstract class WorldUtil {
    public static <E extends Entity> List<E> getNearbyEntities(World world, BlockPos pos, int radius, Class<E> eClass) {
        return getNearbyEntities(world, pos, radius, eClass, EntityPredicates.EXCEPT_SPECTATOR);
    }

    public static <E extends Entity> List<E> getNearbyEntities(World world, BlockPos pos, int radius, Class<E> eClass, Predicate<? super E> predicate) {
        return world.getEntitiesByClass(eClass, new Box(pos).expand(radius + ((double) radius / 2)), predicate);
    }

    public static void grantCriterion(TickCriterion criterion, Entity entity) {
        if (entity instanceof ServerPlayerEntity serverPlayer) {
            criterion.trigger(serverPlayer);
        }
    }

    public static BlockPos toBlockPos(Vec3d vec3d) {
        return new BlockPos.Mutable(
                vec3d.x,
                vec3d.y,
                vec3d.z
        );
    }

    public static void playClientSound(SoundEvent event, float pitch, float volume, PlayerEntity target) {
        if (target instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new PlayClientSoundPayload(event, pitch, volume));
        }
    }

    public static void spawnTrackingParticles(World world, ParticleEffect effect, Vec3d startPos, Vec3d endPos, double speed, int count, double bound) {
        for (int i = 0; i < count; i++) {
            Random random = new Random();

            Vec3d spawnPos = new Vec3d(
                    startPos.x + random.nextDouble(-bound, bound),
                    startPos.y + random.nextDouble(-bound, bound),
                    startPos.z + random.nextDouble(-bound, bound)
            );

            Vec3d velocity = endPos.subtract(spawnPos).normalize().negate().multiply(speed);

            world.addParticleClient(
                    effect,
                    spawnPos.x,
                    spawnPos.y,
                    spawnPos.z,
                    velocity.x,
                    velocity.y,
                    velocity.z
            );
        }
    }
}
