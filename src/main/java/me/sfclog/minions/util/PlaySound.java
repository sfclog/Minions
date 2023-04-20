package me.sfclog.minions.util;

import me.sfclog.minions.Main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PlaySound {


    public static void play_sound(Location loc, Sound s , int range) {

        for (Entity target : loc.getWorld().getNearbyEntities(loc, range, range, range)) {

            if (target instanceof Player) {
                Player p = (Player) target;
                if(p != null) {
                    new SoundUtils(Main.pl, p, loc, s , range, 20);
                }
            }
        }
    }
}
