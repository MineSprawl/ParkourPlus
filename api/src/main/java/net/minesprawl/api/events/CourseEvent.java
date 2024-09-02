package net.minesprawl.api.events;

import net.minesprawl.api.Course;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

public abstract class CourseEvent extends PlayerEvent {
    private final Course course;

    /**
     * @return the course related to the event
     */
    public Course getCourse() {
        return course;
    }

    public CourseEvent(Player who, Course course) {
        super(who);
        this.course = course;
    }
}
