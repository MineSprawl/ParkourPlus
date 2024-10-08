package net.minesprawl.api;

import net.minesprawl.api.events.CheckpointReachedEvent;
import net.minesprawl.api.events.CourseCompletedEvent;
import net.minesprawl.api.events.CourseFallEvent;
import net.minesprawl.api.events.CourseStartedEvent;
import org.bukkit.Location;

import java.util.List;
import java.util.function.Consumer;

/**
 * Represents a parkour course that can be partaken in by player
 */
public interface Course {
    /**
     * @return the courses unique identifier
     */
    String getId();

    /**
     * @return the starting location
     */
    Location getStart();

    /**
     * @return the ending location
     */
    Location getEnd();

    /**
     * @return the acceptable radius around the end location
     */
    Double getEndRadius();

    /**
     * @return the falling y-level
     */
    Double getFallYLevel();

    /**
     * @return a list of checkpoints in this course
     */
    List<Checkpoint> getCheckpoints();

    /**
     * @return a consumer that handles a {@link CourseStartedEvent}
     */
    Consumer<CourseStartedEvent> getStartHandler();


    /**
     * @return a consumer that handles a {@link CourseCompletedEvent}
     */
    Consumer<CourseCompletedEvent> getEndHandler();


    /**
     * @return a consumer that handles a {@link CourseFallEvent}
     */
    Consumer<CourseFallEvent> getFallHandler();


    /**
     * @return a consumer that handles a {@link CheckpointReachedEvent}
     */
    Consumer<CheckpointReachedEvent> getCheckpointHandler();

    /**
     * Sets the starting location of the parkour course
     *
     * @param location the starting location
     *
     * @return the course
     */
    Course setStart(Location location);

    /**
     * Sets the starting action which is a consumer that
     * is called when a player starts the course
     *
     * @param handler a consumer that handles a {@link CourseStartedEvent}
     *
     * @return the course
     */
    Course setStartAction(Consumer<CourseStartedEvent> handler);

    /**
     * Sets the ending location of the parkour course
     *
     * @param location the ending location
     * @param radius the radius around the location to accept as valid positions of completion
     *
     * @return the course
     */
    Course setEnd(Location location, Double radius);

    /**
     * Sets the ending location of the parkour course
     *
     * @param location the ending location
     *
     * @return the course
     */
    Course setEnd(Location location);

    /**
     * Sets the ending action which is a consumer that is called when a player completes the course
     *
     * @param handler a consumer that handles a {@link CourseCompletedEvent}
     *
     * @return the course
     */
    Course setEndedAction(Consumer<CourseCompletedEvent> handler);

    /**
     * Sets the falling y-level, if a player falls under it then
     * they will be moved back to their last checkpoint.
     *
     * @param y the y-level to be considered the falling y-level
     *
     * @return the course
     */
    Course setFallDistance(Double y);

    /**
     * Sets the falling action which is a consumer that is called when the player falls below the falling
     * y-level, falling y-levels can be defined using the Course#setFallingDistance method.
     *
     * @param handler a consumer that handles a {@link CourseFallEvent}
     *
     * @return the course
     */
    Course setFallAction(Consumer<CourseFallEvent> handler);

    /**
     * Adds a checkpoint to the course
     *
     * @param checkpoint the checkpoint to be added
     *
     * @return the course
     */
    Course addCheckpoint(Checkpoint checkpoint);

    /**
     * Adds checkpoints to the course
     *
     * @param checkpoints the checkpoints to be added
     *
     * @return the course
     */
    Course addCheckpoints(Checkpoint... checkpoints);

    /**
     * Adds checkpoints to the course
     *
     * @param checkpoints the checkpoints to be added
     *
     * @return the course
     */
    Course addCheckpoints(List<Checkpoint> checkpoints);

    /**
     * Sets the checkpoint reached action which is a consumer that is called
     * when the player reaches any checkpoint in the course
     *
     * @param handler a consumer that handles a {@link CheckpointReachedEvent}
     *
     * @return the course
     */
    Course setCheckpointReachedAction(Consumer<CheckpointReachedEvent> handler);
}
