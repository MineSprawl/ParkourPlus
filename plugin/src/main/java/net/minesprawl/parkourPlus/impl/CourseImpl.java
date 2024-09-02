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
import java.util.Objects;
import java.util.function.Consumer;

public class CourseImpl implements Course {
    private final String id;

    public CourseImpl(String id) {
        this.id = id;
    }

    private Location start;
    private Location end;
    private Double endRadius;
    private Double fallYLevel;
    private final ArrayList<Checkpoint> checkpoints = new ArrayList<>();

    private Consumer<CourseStartedEvent> startHandler;
    private Consumer<CourseCompletedEvent> endHandler;
    private Consumer<CourseFallEvent> fallHandler;
    private Consumer<CheckpointReachedEvent> checkpointHandler;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Location getStart() {
        return start;
    }

    @Override
    public Location getEnd() {
        return end;
    }

    @Override
    public Double getEndRadius() {
        return endRadius;
    }

    @Override
    public Double getFallYLevel() {
        return fallYLevel;
    }

    @Override
    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    @Override
    public Consumer<CourseStartedEvent> getStartHandler() {
        return startHandler;
    }

    @Override
    public Consumer<CourseCompletedEvent> getEndHandler() {
        return endHandler;
    }

    @Override
    public Consumer<CourseFallEvent> getFallHandler() {
        return fallHandler;
    }

    @Override
    public Consumer<CheckpointReachedEvent> getCheckpointHandler() {
        return checkpointHandler;
    }

    @Override
    public Course setStart(Location location) {
        start = location;
        return this;
    }

    @Override
    public Course setStartAction(Consumer<CourseStartedEvent> handler) {
        startHandler = handler;
        return this;
    }

    @Override
    public Course setEnd(Location location, Double radius) {
        end = location;
        endRadius = radius;
        return this;
    }

    @Override
    public Course setEnd(Location location) {
        end = location;
        return this;
    }

    @Override
    public Course setEndedAction(Consumer<CourseCompletedEvent> handler) {
        endHandler = handler;
        return this;
    }

    @Override
    public Course setFallDistance(Double y) {
        fallYLevel = y;
        return this;
    }

    @Override
    public Course setFallAction(Consumer<CourseFallEvent> handler) {
        fallHandler = handler;
        return this;
    }

    @Override
    public Course addCheckpoint(Checkpoint checkpoint) {
        Objects.requireNonNull(checkpoint.getLocation(), "Checkpoints must have a valid location");
        checkpoints.add(checkpoint);
        return this;
    }

    @Override
    public Course addCheckpoints(Checkpoint... checkpoints) {
        for (Checkpoint checkpoint : checkpoints) {
            Objects.requireNonNull(checkpoint.getLocation(), "Checkpoints must have a valid location");
        }
        this.checkpoints.addAll(List.of(checkpoints));
        return this;
    }

    @Override
    public Course addCheckpoints(List<Checkpoint> checkpoints) {
        for (Checkpoint checkpoint : checkpoints) {
            Objects.requireNonNull(checkpoint.getLocation(), "Checkpoints must have a valid location");
        }
        this.checkpoints.addAll(checkpoints);
        return this;
    }

    @Override
    public Course setCheckpointReachedAction(Consumer<CheckpointReachedEvent> handler) {
        checkpointHandler = handler;
        return this;
    }
}
