package me.sfclog.minions.runnable_core;

import me.sfclog.minions.Main;
import me.sfclog.minions.MinionSystem.MinionManage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AsyncRange {


    public static void update_range() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.pl, new Runnable() {
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(p != null) {
                         MinionManage.getMinionNearPlayerAndRun(p);
                    }
                }
            }
        }, 10, 10);
    }
}
