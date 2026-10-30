package org.chemthunder.arboreal.api.util;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.advancements.triggers.PlayerTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.chemthunder.arboreal.core.networking.s2c.PlayClientSoundPayload;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * @author Chemthunder
 */
@ApiStatus.NonExtendable
@SuppressWarnings("unused")
public abstract class LevelUtil {
    public static <E extends Entity> List<E> getNearbyEntities(Level level, BlockPos pos, int radius, Class<E> eClass) {
        return getNearbyEntities(level, pos, radius, eClass, EntitySelector.NO_SPECTATORS);
    }

    public static <E extends Entity> List<E> getNearbyEntities(Level level, BlockPos pos, int radius, Class<E> eClass, Predicate<? super E> predicate) {
        return level.getEntitiesOfClass(eClass, new AABB(pos).inflate(radius + ((double) radius / 2)), predicate);
    }

    public static void grantCriterion(PlayerTrigger criterion, Entity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {
            criterion.trigger(serverPlayer);
        }
    }

    public static BlockPos toBlockPos(Vec3 vec3d) {
        return new BlockPos.MutableBlockPos(
                vec3d.x,
                vec3d.y,
                vec3d.z
        );
    }

    public static void playClientSound(SoundEvent event, float pitch, float volume, Player target) {
        if (target instanceof ServerPlayer serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new PlayClientSoundPayload(event, pitch, volume));
        }
    }

    public static void spawnTrackingParticles(Level Level, ParticleOptions effect, Vec3 startPos, Vec3 endPos, double speed, int count, double bound) {
        for (int i = 0; i < count; i++) {
            Random random = new Random();

            Vec3 spawnPos = new Vec3(
                    startPos.x + random.nextDouble(-bound, bound),
                    startPos.y + random.nextDouble(-bound, bound),
                    startPos.z + random.nextDouble(-bound, bound)
            );

            Vec3 velocity = endPos.subtract(spawnPos).normalize().reverse().scale(speed);

            Level.addParticle(
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

    @Nullable
    public static EntityHitResult getEntityRaycast(Entity entity, Predicate<Entity> predicate, double range) {
        return ProjectileUtil.getHitResultOnViewVector(entity, predicate, range) instanceof EntityHitResult entityHitResult ? entityHitResult : null;
    }

    @Nullable
    public static EntityHitResult getEntityRaycast(Entity entity, double range) {
        return ProjectileUtil.getHitResultOnViewVector(entity, (target -> true), range) instanceof EntityHitResult entityHitResult ? entityHitResult : null;
    }
}
