package net.minesprawl.parkourPlus;

import net.minesprawl.api.ParkourManager;
import net.minesprawl.api.ParkourManagerProvider;
import net.minesprawl.parkourPlus.impl.ParkourManagerImpl;
import org.bukkit.plugin.java.JavaPlugin;

public final class ParkourPlus extends JavaPlugin implements ParkourManagerProvider {
    private ParkourManager parkourManager;

    @Override
    public void onEnable() {
        parkourManager = new ParkourManagerImpl(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public ParkourManager getManager() {
        return parkourManager;
    }
}
