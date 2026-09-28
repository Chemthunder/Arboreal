package org.chemthunder.arboreal.api.event;

import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

/**
 * @author Chemthunder
 */
@ApiStatus.NonExtendable
@SuppressWarnings("unused")
public abstract class EventUtil {
    public static <T> List<T> sortAndCollectEvents(T[] events, ToIntFunction<? super T> priority) {
        List<T> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(priority));
        return sortedEvents;
    }

    public static <T> List<T> sortAndCollectEvents(T[] events, int priority) {
        List<T> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(value -> priority));
        return sortedEvents;
    }

    @ApiStatus.Experimental
    public static <T> void deployVoidEvent(T[] events, Consumer<T> consumer) {
        for (T event : sortAndCollectEvents(events, 1000)) {
            consumer.accept(event);
        }
    }
}
