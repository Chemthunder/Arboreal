package org.chemthunder.arboreal.api.utilities;

import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class WorldUtil {
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
}
