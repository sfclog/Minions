package me.sfclog.minions.util;

import me.sfclog.minions.MinionManage.Minion;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AILook {


    public static Location random_look(Minion m , Player p) {
        int ran = RandomUtil.getRandomNumber(0,9);
        if(ran == 1) {
            return p.getLocation().clone().add(0,0.5,0);
        }
        if(ran == 2) {
            Location loc = m.getLoc().clone();
            loc.setYaw(RandomUtil.getRandomNumber(-180,180));
            return loc;
        }
        if(ran == 3) {
            Location loc = m.getLoc().clone();
            loc.setPitch(RandomUtil.getRandomNumber(-90,90));
            return loc;
        }
        return p.getLocation().clone().add(0,0.5,0);
    }

}
