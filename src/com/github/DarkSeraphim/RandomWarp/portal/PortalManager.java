package com.github.DarkSeraphim.RandomWarp.portal;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PortalManager
{

    JavaPlugin plugin;
    Set<Portal> portals = new HashSet();

    public void onEnable(JavaPlugin plugin)
    {
        this.plugin = plugin;
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PortalTravelListener(this), getPlugin());
        pm.registerEvents(new PortalPhysicsListener(this), getPlugin());
    }

    public void onDisable()
    {
    }

    public void preparePortal(Location p1, Location p2, int[][] endpoints)
    {
        Portal p = Portal.createPortal(p1, p2, endpoints);
        this.portals.add(p);
        p.open(Material.ENDER_PORTAL);
    }

    public JavaPlugin getPlugin()
    {
        return this.plugin;
    }

    public Portal getPortalAt(Location loc)
    {
        for (Portal p : this.portals)
        {
            if (p.isInPortal(loc))
            {
                return p;
            }
        }
        return null;
    }
}