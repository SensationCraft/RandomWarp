package com.github.DarkSeraphim.RandomWarp.portal;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Portal
{

    private final Vector min;
    private final Vector max;
    private final String world;
    private Material portalType = Material.AIR;
    private int[][] endpoints;
    private final Orientation orientation;
    private boolean checkForFeet = true;
    private final Random rand = new Random();
    private static final String CALCULATING = ChatColor.BLUE + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "Calculating location, please wait...";
    private static final String TELEPORTING = ChatColor.BLUE + "" + ChatColor.BOLD + "Teleporting";

    private Portal(World w, Vector a, Vector b, int[][] endpoints)
    {
        this.min = new Vector(Math.min(a.getX(), b.getX()), Math.min(a.getY(), b.getY()), Math.min(a.getZ(), b.getZ()));
        this.max = new Vector(Math.max(a.getX(), b.getX()), Math.max(a.getY(), b.getY()), Math.max(a.getZ(), b.getZ()));
        this.world = w.getName();
        if (this.min.getBlockX() == this.max.getBlockX())
        {
            this.orientation = Orientation.EAST_WEST;
        }
        else if (this.min.getBlockY() == this.max.getBlockY())
        {
            this.orientation = Orientation.UP;
        }
        else if (this.min.getBlockZ() == this.max.getBlockZ())
        {
            this.orientation = Orientation.NORTH_SOUTH;
        }
        else
        {
            this.orientation = Orientation.UNKNOWN;
        }
        this.endpoints = endpoints;
    }

    public static Portal createPortal(Location p1, Location p2, int[][] endpoints)
            throws IllegalArgumentException
    {
        if (p1.getWorld() != p2.getWorld())
        {
            throw new IllegalArgumentException("Your points seem to be in a different world.");
        }

        if ((Math.abs(p1.getBlockX() - p2.getBlockX()) > 1) && (Math.abs(p1.getBlockY() - p2.getBlockY()) > 1) && (Math.abs(p1.getBlockZ() - p2.getBlockZ()) > 1))
        {
            throw new IllegalArgumentException("Currently only flat portals are accepted");
        }
        return new Portal(p1.getWorld(), p1.toVector(), p2.toVector(), endpoints);
    }

    public boolean open(Material type)
    {
        this.portalType = type;
        return true;
    }

    public boolean doesItCheckForFeet()
    {
        return this.checkForFeet;
    }

    public World getWorld()
    {
        return Bukkit.getWorld(this.world);
    }

    public Material getPortalType()
    {
        return this.portalType;
    }

    public Location getTransportLocation(Player player, float yaw, float pitch)
    {
        World w = Bukkit.getWorld("world");
        if (w == null)
        {
            return null;
        }

        Location loc = new Location(w, 0.0D, 64.0D, 0.0D);

        if (this.endpoints != null)
        {
            int[] point = this.endpoints[this.rand.nextInt(this.endpoints.length)];

            int y = w.getHighestBlockYAt(point[0], point[1]);

            loc = new Location(w, point[0], y, point[1]).add(0.5D, 0.0D, 0.5D);
        }
        else
        {
            Faction f = null;
            FLocation floc = new FLocation(loc);
            player.sendMessage(CALCULATING);
            while (true)
            {
                floc.setX(this.rand.nextInt(13001) - 6500);
                floc.setZ(this.rand.nextInt(13001) - 6500);
                f = Board.getFactionAt(floc);
                if ((f.isWarZone()) || (f.isSafeZone()) || (f.isNone()))
                {
                    loc.setX(floc.getX());
                    loc.setZ(floc.getZ());
                    loc.setY(w.getHighestBlockYAt(loc));
                    if ((loc.getBlock().getType() == Material.AIR) && (loc.getBlock().getRelative(BlockFace.DOWN, 1).getType().isSolid()))
                    {
                        break;
                    }
                }
            }
        }
        loc.setYaw(yaw);
        loc.setPitch(pitch);

        return loc;
    }

    public boolean isInPortal(Location loc)
    {
        if (!loc.getWorld().getName().equals(this.world))
        {
            return false;
        }

        boolean x = (this.min.getBlockX() <= loc.getBlockX()) && (loc.getBlockX() <= this.max.getBlockX());
        boolean y = (this.min.getBlockY() <= loc.getBlockY()) && (loc.getBlockY() <= this.max.getBlockY());
        boolean z = (this.min.getBlockZ() <= loc.getBlockZ()) && (loc.getBlockZ() <= this.max.getBlockZ());

        return (x) && (y) && (z);
    }

    boolean isReadyToTravel(Material mat)
    {
        return translateLiquids(mat) == translateLiquids(this.portalType);
    }

    public boolean transport(Player player)
    {
        Location loc = player.getLocation();

        float yaw = loc.getYaw();
        float pitch = loc.getPitch();

        Vector speed = loc.getDirection().normalize().multiply(0.1D);

        if (player.isSprinting())
        {
            speed.multiply(1.25D);
        }

        loc = getTransportLocation(player, yaw, pitch);
        if (loc != null)
        {
            player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 31.0F);
            player.sendMessage(TELEPORTING);
            player.teleport(loc);
            return true;
        }
        return false;
    }

    public static Material translateLiquids(Material mat)
    {
        if (mat == Material.WATER)
        {
            return Material.STATIONARY_WATER;
        }
        if (mat == Material.LAVA)
        {
            return Material.STATIONARY_LAVA;
        }
        return mat;
    }

    private static enum Orientation
    {

        UNKNOWN(new BlockFace[]
{
    BlockFace.SELF
}),
        NORTH_SOUTH(new BlockFace[]
{
    BlockFace.UP, BlockFace.DOWN, BlockFace.EAST, BlockFace.WEST
}),
        EAST_WEST(new BlockFace[]
{
    BlockFace.UP, BlockFace.DOWN, BlockFace.SOUTH, BlockFace.NORTH
}),
        UP(new BlockFace[]
{
    BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST
});
        BlockFace[] neighbours;

        private Orientation(BlockFace[] neighbours)
        {
            this.neighbours = neighbours;
        }

        public BlockFace[] getNeighbours()
        {
            return this.neighbours;
        }
    }
}