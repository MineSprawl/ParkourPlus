package net.minesprawl.api.events;

import net.minesprawl.api.Course;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class CourseCompletedEvent extends CourseEvent {
    public CourseCompletedEvent(Player who, Course course) {
        super(who, course);
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
