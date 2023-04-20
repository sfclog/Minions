package me.sfclog.minions.util;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockUtil {

    public static boolean is_dirt(Block b) {

        if(b.getType() != null) {
            Material m = b.getType();
            if(m == Material.GRASS_BLOCK) {
                return true;
            }
            if(m == Material.FARMLAND) {
                return true;
            }
            if(m.name().contains("DIRT")) {
                return true;
            }
        }

        return false;
    }
}
