package org.chemthunder.arboreal.api.event;

/**
 * @author Chemthunder
 */
public interface ArborealEvent {
    default int getPriority() {
        return 1000;
    }
}
