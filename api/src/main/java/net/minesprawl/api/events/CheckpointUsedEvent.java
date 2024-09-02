package net.minesprawl.api.events;

import net.minesprawl.api.Checkpoint;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class CheckpointUsedEvent extends CheckpointEvent {
    public CheckpointUsedEvent(Player who, Checkpoint checkpoint) {
        super(who, checkpoint);
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

