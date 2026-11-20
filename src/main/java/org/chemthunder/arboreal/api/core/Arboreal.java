package org.chemthunder.arboreal.api.core;

import com.google.common.util.concurrent.Runnables;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class Arboreal {
    protected List<ArborealTask> tasks = new ArrayList<>();

    private final String modId;
    private final Logger logger;

    public Arboreal(String modId) {
        this.modId = modId;
        this.logger = LoggerFactory.getLogger(modId);
    }

    public Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(modId, path);
    }

    public <T extends ArborealTask> T createTask(Function<Arboreal, T> createFn) {
        return createFn.apply(this);
    }

    public <T extends ArborealTask, A> T createTask(BiFunction<A, Arboreal, T> createFn, A value) {
        return createFn.apply(value, this);
    }

    public List<ArborealTask> getTasks() {
        return this.tasks;
    }

    public void addHandler(ArborealTask task) {
        this.tasks.add(task);
    }

    public void addHandler(Supplier<ArborealTask> task) {
        this.tasks.add(task.get());
    }

    public void initMain() {
        this.tasks.forEach(ArborealTask::runMain);
        this.logger.info("[MAIN] Completed tasks for {}", this.modId);
    }

    public void initMain(Runnable runnable) {
        runnable.run();
        initMain();
    }

    @Environment(EnvType.CLIENT)
    public void initClient() {
        this.tasks.forEach(ArborealTask::runClient);
        this.logger.info("[CLIENT] Completed tasks for {}", this.modId);
    }

    @Environment(EnvType.CLIENT)
    public void initClient(Runnable runnable) {
        runnable.run();
        initClient();
    }
}
