package net.minesprawl.parkourPlus.impl;

import net.minesprawl.api.Checkpoint;
import net.minesprawl.api.events.CheckpointReachedEvent;
import org.bukkit.Location;

import java.util.function.Consumer;

public class CheckpointImpl implements Checkpoint {
    private Location location;
    private Double radius;
    private Consumer<CheckpointReachedEvent> handler;

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Double getRadius() {
        return radius;
    }

    @Override
    public Consumer<CheckpointReachedEvent> getReachedHandler() {
        return handler;
    }

    @Override
    public Checkpoint setLocation(Location location, Double radius) {
        this.location = location;
        this.radius = radius;
        return this;
    }

    @Override
    public Checkpoint setLocation(Location location) {
        this.location = location;
        return this;
    }

    @Override
    public Checkpoint setReachedAction(Consumer<CheckpointReachedEvent> handler) {
        this.handler = handler;
        return this;
    }
}
