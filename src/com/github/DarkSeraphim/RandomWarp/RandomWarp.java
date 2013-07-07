package com.github.DarkSeraphim.RandomWarp;

import com.github.DarkSeraphim.RandomWarp.portal.PortalManager;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author DarkSeraphim
 */
public class RandomWarp extends JavaPlugin
{
    
    int[][] p1 = new int[][]
    {
        new int[]{-3051,3583},
        new int[]{-3051,3568},
        new int[]{-3040,3557},
        new int[]{-3025,3557},
        new int[]{-3014,3568},
        new int[]{-3014,3568},
        new int[]{-3025,3594},
        new int[]{-3040,3594},
    };
    
    int[][] p2 = new int[][]
    {
        new int[]{4757,4688},
        new int[]{4768,4677},
        new int[]{4783,4677},
        new int[]{4794,4688},
        new int[]{4794,4703},
        new int[]{4783,4714},
        new int[]{4768,4714},
        new int[]{4757,4703},
    };
    
    int[][] p3 = new int[][]
    {
        new int[]{-4976,-4779},
        new int[]{-4961,-4779},
        new int[]{-4950,-4768},
        new int[]{-4950,-4753},
        new int[]{-4961,-4742},
        new int[]{-4976,-4742},
        new int[]{-4987,-4753},
        new int[]{-4987,-4768},
    };
    
    int[][] p4 = new int[][]
    {
        new int[]{4197,-5185},
        new int[]{4197,-5200},
        new int[]{4208,-5211},
        new int[]{4223,-5211},
        new int[]{4234,-5200},
        new int[]{4234,-5185},
        new int[]{4223,-5174},
        new int[]{4208,-5174},
    };
    
    final Random rand = new Random();
    
    PortalManager manager;
    
    @Override
    public void onEnable()
    {
        this.manager = new PortalManager();
        this.manager.onEnable(this);
        
        this.manager.preparePortal(new Location(Bukkit.getWorld("Spawn"), 30, 64, -18), new Location(Bukkit.getWorld("Spawn"), 34, 64, -14), p1);
        this.manager.preparePortal(new Location(Bukkit.getWorld("Spawn"), 32, 64, -10), new Location(Bukkit.getWorld("Spawn"), 36, 64, -6), p2);
        this.manager.preparePortal(new Location(Bukkit.getWorld("Spawn"), 32, 64, 6), new Location(Bukkit.getWorld("Spawn"), 36, 64, 10), p3);
        this.manager.preparePortal(new Location(Bukkit.getWorld("Spawn"), 30, 64, 14), new Location(Bukkit.getWorld("Spawn"), 34, 64, 18), p4);
        this.manager.preparePortal(new Location(Bukkit.getWorld("Spawn"), 33, 64, -2), new Location(Bukkit.getWorld("Spawn"), 37, 64, 2), null);
        
    }

}
