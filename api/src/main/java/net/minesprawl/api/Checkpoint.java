package net.minesprawl.api;

import net.minesprawl.api.events.CheckpointReachedEvent;
import org.bukkit.Location;

import java.util.function.Consumer;

/**
 * Represents a checkpoint in a course
 */
public interface Checkpoint {
    /**
     * @return the location of the checkpoint
     */
    Location getLocation();

    /**
     * @return the accept radius from the checkpoint location to accept
     */
    Double getRadius();

    /**
     * @return a consumer that handles a {@link CheckpointReachedEvent} for this checkpoint
     */
    Consumer<CheckpointReachedEvent> getReachedHandler();

    /**
     * Sets the location the player must reach to obtain this checkpoint
     *
     * @param location the location of the checkpoint
     * @param radius the radius around the location to accept as valid checkpoint positions
     *
     * @return the checkpoint
     */
    Checkpoint setLocation(Location location, Double radius);

    /**
     * Sets the location the player must reach to obtain this checkpoint
     *
     * @param location the location of the checkpoint
     *
     * @return the checkpoint
     */
    Checkpoint setLocation(Location location);

    /**
     * Sets the checkpoint reached action which is a consumer that is called when the player reaches this checkpoint
     *
     * @param handler a consumer that handles a {@link CheckpointReachedEvent}
     *
     * @return the checkpoint
     */
    Checkpoint setReachedAction(Consumer<CheckpointReachedEvent> handler);
}
