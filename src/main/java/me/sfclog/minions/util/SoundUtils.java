package me.sfclog.minions.util;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SoundUtils  {
    private final int range;
    private final Location music;
    private final Player player;
    private final int ticks;

    private Sound so;

    public SoundUtils(Plugin plugin, Player player, Location music, Sound s , int range, int ticks) {
        this.player = player;
        this.music = music;
        this.range = range;
        this.ticks = ticks;
        this.so = s;

        play();

    }



    public void play() {
        player.playSound(player.getLocation(), so, convertForSound((float) player.getLocation().distance(music), range), 1);
    }

    private static float convertForSound(float x, int range) {
        return Math.max(0, 1 - (x / range));
    }
}