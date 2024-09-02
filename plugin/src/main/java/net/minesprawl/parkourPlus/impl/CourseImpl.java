package net.minesprawl.parkourPlus.impl;

import net.minesprawl.api.Checkpoint;
import net.minesprawl.api.Course;
import net.minesprawl.api.events.CheckpointReachedEvent;
import net.minesprawl.api.events.CourseCompletedEvent;
import net.minesprawl.api.events.CourseFallEvent;
import net.minesprawl.api.events.CourseStartedEvent;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CourseImpl implements Course {
    private final String id;

    private Location start;
    private Location end;
    private Integer endRadius;
    private Integer fallYLevel;
    private ArrayList<Checkpoint> checkpoints;

    private Consumer<CourseStartedEvent> startHandler;
    private Consumer<CourseCompletedEvent> endHandler;
    private Consumer<CourseFallEvent> fallHandler;
    private Consumer<CheckpointReachedEvent> checkpointHandler;

    public String getId() {
        return id;
    }

    public CourseImpl(String id) {
        this.id = id;
    }

    @Override
    public Course setStart(Location location) {
        return this;
    }

    @Override
    public Course setStartAction(Consumer<CourseStartedEvent> handler) {
        return this;
    }

    @Override
    public Course setEnd(Location location, Integer radius) {
        return this;
    }

    @Override
    public Course setEnd(Location location) {
        return this;
    }

    @Override
    public Course setEndedAction(Consumer<CourseCompletedEvent> handler) {
        return this;
    }

    @Override
    public Course setFallDistance(Double y) {
        return this;
    }

    @Override
    public Course setFallAction(Consumer<CourseFallEvent> handler) {
        return this;
    }

    @Override
    public Course addCheckpoint(Checkpoint checkpoint) {
        return this;
    }

    @Override
    public Course addCheckpoints(Checkpoint... checkpoints) {
        return this;
    }

    @Override
    public Course addCheckpoints(List<Checkpoint> checkpoints) {
        return this;
    }

    @Override
    public Course setCheckpointReachedAction(Consumer<CheckpointReachedEvent> handler) {
        return this;
    }
}
