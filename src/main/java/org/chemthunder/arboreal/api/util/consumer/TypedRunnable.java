package org.chemthunder.arboreal.api.util.consumer;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface TypedRunnable<P, R> {
    R run(P param);
}
