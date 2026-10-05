package org.chemthunder.arboreal.api.data.server;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.Arboreal;
import org.chemthunder.arboreal.api.util.ItemUtil;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "unused"})
public abstract class ArborealAdvancementProvider extends FabricAdvancementProvider {
    private final Arboreal arboreal;

    public ArborealAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup, Arboreal arboreal) {
        super(output, registryLookup);
        this.arboreal = arboreal;
    }

    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {}

    private AdvancementEntry addContextAdvancement(Optional<AdvancementEntry> root, Context context) {
        Advancement.Builder builder = Advancement.Builder.createUntelemetered()
                .display(
                        context.displayStack,
                        context.title,
                        context.desc,
                        context.rootBackground.orElse(null),
                        context.frame,
                        context.showToast,
                        context.announceToChat,
                        context.hidden
                ).requirements(AdvancementRequirements.allOf(Collections.singleton("e")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("e", context.criterion);

        Advancement.Builder finalizedBuilder = root.isPresent() ? builder.parent(root.get()) : builder;
        return finalizedBuilder.build(this.arboreal.id(context.name));
    }

    private AdvancementEntry addHiddenChallenge(Optional<AdvancementEntry> root, String name, Text title, Text desc, ItemStack displayStack, AdvancementCriterion<?> criterion) {
        return addContextAdvancement(root, new Context(
                name,
                title,
                desc,
                displayStack,
                AdvancementFrame.CHALLENGE,
                Optional.empty(),
                true,
                true,
                true,
                criterion
        ));
    }

    private AdvancementEntry addBasicAdvancement(Optional<AdvancementEntry> root, String name, Text title, Text desc, ItemStack displayStack, AdvancementCriterion<?> criterion) {
        return addContextAdvancement(root, new Context(
                name,
                title,
                desc,
                displayStack,
                AdvancementFrame.TASK,
                Optional.empty(),
                false,
                true,
                false,
                criterion
        ));
    }

    private AdvancementEntry addRootAdvancement(Text title, Text desc, ItemStack displayStack, Identifier background) {
        return addContextAdvancement(Optional.empty(), new Context(
                this.arboreal.modId() + "_root",
                title,
                desc,
                displayStack,
                AdvancementFrame.TASK,
                Optional.of(background),
                false,
                false,
                false,
                TickCriterion.Conditions.createTick()
        ));
    }

    private AdvancementEntry addRootAdvancement(ItemStack displayStack, Identifier background) {
        return addContextAdvancement(Optional.empty(), new Context(
                this.arboreal.modId() + "_root",
                Text.literal(ItemUtil.formatString(this.arboreal.modId())),
                Text.literal("Welcome to " + ItemUtil.formatString(this.arboreal.modId())),
                displayStack,
                AdvancementFrame.TASK,
                Optional.of(background),
                false,
                false,
                false,
                TickCriterion.Conditions.createTick()
        ));
    }

    private record Context(
            String name,
            Text title,
            Text desc,
            ItemStack displayStack,
            AdvancementFrame frame,
            Optional<Identifier> rootBackground,
            boolean showToast,
            boolean announceToChat,
            boolean hidden,
            AdvancementCriterion<?> criterion
    ) {}
}
