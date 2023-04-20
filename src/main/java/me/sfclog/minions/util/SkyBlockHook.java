package me.sfclog.minions.util;

import me.sfclog.minions.Main;
import me.sfclog.skyblock_cross.region.RegionMath;
import org.bukkit.Location;

public class SkyBlockHook {

    public static boolean is_is_offline(Location loc) {
            String s = RegionMath.getAdminLocation(loc);
            if (s == null || s.equals("~")) {
                return true;
            }

        return false;
    }



}
