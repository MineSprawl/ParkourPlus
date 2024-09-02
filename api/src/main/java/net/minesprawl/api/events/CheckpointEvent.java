package net.minesprawl.api.events;

import net.minesprawl.api.Checkpoint;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

public abstract class CheckpointEvent extends PlayerEvent {
    private final Checkpoint checkpoint;

    /**
     * @return the checkpoint that was reached
     */
    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    public CheckpointEvent(Player who, Checkpoint checkpoint1) {
        super(who);
        this.checkpoint = checkpoint1;
    }
}
