package net.minesprawl.api.events;

import net.minesprawl.api.Checkpoint;
import net.minesprawl.api.Course;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class CheckpointReachedEvent extends CheckpointEvent {
    private final Course course;

    /**
     * @return the course that the checkpoint belongs to
     */
    public Course getCourse() {
        return course;
    }

    public CheckpointReachedEvent(Player who, Course course, Checkpoint checkpoint) {
        super(who, checkpoint);
        this.course = course;
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
