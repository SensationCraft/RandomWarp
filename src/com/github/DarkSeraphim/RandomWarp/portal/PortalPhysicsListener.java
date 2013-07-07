package com.github.DarkSeraphim.RandomWarp.portal;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class PortalPhysicsListener
        implements Listener
{

    private final PortalManager manager;

    protected PortalPhysicsListener(PortalManager manager)
    {
        this.manager = manager;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockBurn(BlockBurnEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockFade(BlockFadeEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockForm(BlockFormEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockFromTo(BlockFromToEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockGrow(BlockGrowEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockIgnite(BlockIgniteEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockPhysics(BlockPhysicsEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        for (Block b : event.getBlocks())
        {
            loc = b.getLocation();
            if (this.manager.getPortalAt(loc) != null)
            {
                event.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        if (event.isSticky())
        {
            if (this.manager.getPortalAt(event.getRetractLocation()) != null)
            {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockSpread(BlockSpreadEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntityBlockForm(EntityBlockFormEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onLeavesDecay(LeavesDecayEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntityChangeBlock(EntityChangeBlockEvent event)
    {
        Location loc = event.getBlock().getLocation();
        if (this.manager.getPortalAt(loc) == null)
        {
            return;
        }
        event.setCancelled(true);
    }
}