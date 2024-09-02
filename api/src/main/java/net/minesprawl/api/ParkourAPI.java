package net.minesprawl.api;

import net.minesprawl.api.exceptions.PluginInstallationException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ParkourAPI {
    private static ParkourManager manager;

    public static ParkourManager getInstance() throws PluginInstallationException {
        if (manager == null) {
            if (!Bukkit.getPluginManager().isPluginEnabled("ParkourPlus")) {
                throw new PluginInstallationException("ParkourPlus is not enabled!");
            }
            Plugin plugin = Bukkit.getPluginManager().getPlugin("ParkourPlus");
            ParkourManagerProvider managerProvider = (ParkourManagerProvider) plugin;
            assert managerProvider != null;
            manager = managerProvider.getManager();
        }
        return manager;
    }
}
