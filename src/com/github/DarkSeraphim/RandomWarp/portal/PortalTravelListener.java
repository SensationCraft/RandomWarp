package com.github.DarkSeraphim.RandomWarp.portal;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.util.BlockVector;

public class PortalTravelListener
        implements Listener
{

    private final PortalManager manager;
    private final Set<String> freshTravelers = new HashSet();

    public PortalTravelListener(PortalManager manager)
    {
        this.manager = manager;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPortal(PlayerPortalEvent event)
    {
        Location from = event.getFrom();
        Portal p = this.manager.getPortalAt(from);
        if (p != null)
        {
            event.setCancelled(true);
            event.getPortalTravelAgent().setCanCreatePortal(false);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPortal(PlayerMoveEvent event)
    {
        if (!actuallyMoved(event.getFrom(), event.getTo()))
        {
            return;
        }
        Player traveler = event.getPlayer();
        Location from = event.getFrom();
        Material feet = from.getBlock().getType();
        Portal p = this.manager.getPortalAt(from);
        if ((p != null) && (p.isReadyToTravel(feet)))
        {
            if (this.freshTravelers.contains(traveler.getName()))
            {
                return;
            }
            if (p.transport(traveler))
            {
                this.freshTravelers.add(traveler.getName());
            }

        }
        else if (this.freshTravelers.contains(traveler.getName()))
        {
            this.freshTravelers.remove(traveler.getName());
        }
    }

    public static boolean actuallyMoved(Location a, Location b)
    {
        BlockVector va = a.toVector().toBlockVector();
        BlockVector vb = b.toVector().toBlockVector();
        return (!va.equals(vb)) || (a.getY() != b.getY());
    }
}