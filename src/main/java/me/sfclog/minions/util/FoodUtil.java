package me.sfclog.minions.util;

import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionSystem.MinionLevel;
import org.bukkit.Material;

public class FoodUtil {


    public static int getRegenFood(Minion mi,Material m) {
        if(m == Material.GOLDEN_APPLE) {
            return 50;
        }
        if(m == Material.ENCHANTED_GOLDEN_APPLE) {
            return MinionLevel.getFoodUpdate(mi.gettier());
        }
        return RandomUtil.getRandomNumber(10,50);
    }
}
