package me.sfclog.minions.util;

import org.bukkit.entity.Player;

public class AdminUtil {

    public static boolean isadmin(Player p) {
        if(p.isOp() || p.hasPermission("*")) {
            return true;
        }
        return false;
    }
}
