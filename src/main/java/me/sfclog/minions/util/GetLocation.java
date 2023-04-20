package me.sfclog.minions.util;

import me.sfclog.minions.Main;
import me.sfclog.minions.MinionSystem.LoadSupportBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GetLocation {


    public static Block getBlockMiner(Location loc) {
        for(Block b : getLocation(loc)) {
            if(b != null && b.getType() != null && LoadSupportBlock.m.contains(b.getType()) && check_stackerblock(b)) {
               return b;
            }
        }
        return null;
    }

    private static boolean check_stackerblock(Block b) {
        if(Main.rosestack) {
            return !Main.rsAPI.isBlockStacked(b);
        }
        return true;
    }


    public static List<Block> getLumberJack(Location center) {
        List<Block> list = new ArrayList<Block>();
        int radius = 20;
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block b = center.getBlock().getRelative(x, y, z);
                    if(b != null && b.getType() != null) {
                        if(b.getType().name().contains("LEAVES") || b.getType().name().contains("WOOD") || b.getType().name().contains("LOG")) {
                            list.add(b);
                        }
                    }
                }
            }
        }
        return list;
    }



    public static ArrayList<Block> getLocationFarmer(Location loc) {

        ArrayList<Block> list = new ArrayList<Block>();

        list.add(loc.clone().getBlock());

        list.add(loc.clone().add(1,0,0).getBlock());
        list.add(loc.clone().add(2,0,0).getBlock());

        list.add(loc.clone().add(-1,0,0).getBlock());
        list.add(loc.clone().add(-2,0,0).getBlock());

        list.add(loc.clone().add(0,0,-1).getBlock());
        list.add(loc.clone().add(0,0,-2).getBlock());

        list.add(loc.clone().add(-1,0,-1).getBlock());
        list.add(loc.clone().add(-2,0,-1).getBlock());
        list.add(loc.clone().add(-1,0,-2).getBlock());
        list.add(loc.clone().add(-2,0,-2).getBlock());

        list.add(loc.clone().add(1,0,-1).getBlock());
        list.add(loc.clone().add(2,0,-2).getBlock());


        list.add(loc.clone().add(0,0,1).getBlock());
        list.add(loc.clone().add(0,0,2).getBlock());

        list.add(loc.clone().add(-1,0,1).getBlock());
        list.add(loc.clone().add(-2,0,1).getBlock());
        list.add(loc.clone().add(-1,0,2).getBlock());
        list.add(loc.clone().add(-2,0,2).getBlock());

        list.add(loc.clone().add(1,0,1).getBlock());
        list.add(loc.clone().add(2,0,2).getBlock());
        list.add(loc.clone().add(1,0,-2).getBlock());
        list.add(loc.clone().add(1,0,2).getBlock());
        list.add(loc.clone().add(2,0,1).getBlock());
        list.add(loc.clone().add(2,0,-1).getBlock());

        return list;

    }
    public static ArrayList<Block> getLocation(Location loc) {

        ArrayList<Block> list = new ArrayList<Block>();

        Location loc1 = loc.clone().add(1,-1,0);
        list.add(loc1.getBlock());

        Location loc2 = loc.clone().add(-1,-1,0);
        list.add(loc2.getBlock());

        Location loc3 = loc.clone().add(0,-1,1);
        list.add(loc3.getBlock());

        Location loc4 = loc.clone().add(0,-1,-1);
        list.add(loc4.getBlock());

        Location loc5 = loc.clone().add(1,-1,-1);
        list.add(loc5.getBlock());

        Location loc6 = loc.clone().add(1,-1,1);
        list.add(loc6.getBlock());


        Location loc7 = loc.clone().add(-1,-1,1);
        list.add(loc7.getBlock());

        Location loc8 = loc.clone().add(-1,-1,-1);
        list.add(loc8.getBlock());



        Location loc9 = loc.clone().add(1,0,0);
        list.add(loc9.getBlock());

        Location loc10 = loc.clone().add(-1,0,0);
        list.add(loc10.getBlock());

        Location loc11 = loc.clone().add(0,0,1);
        list.add(loc11.getBlock());

        Location loc12 = loc.clone().add(0,0,-1);
        list.add(loc12.getBlock());

        Location loc13 = loc.clone().add(1,0,-1);
        list.add(loc13.getBlock());

        Location loc14 = loc.clone().add(1,0,1);
        list.add(loc14.getBlock());


        Location loc15 = loc.clone().add(-1,0,1);
        list.add(loc15.getBlock());

        Location loc16 = loc.clone().add(-1,0,-1);
        list.add(loc16.getBlock());

        Location loc17 = loc.clone().add(0,1,0);
        list.add(loc17.getBlock());


        Location loc18 = loc.clone().add(0,-1,0);
        list.add(loc18.getBlock());

        return list;


    }
}
