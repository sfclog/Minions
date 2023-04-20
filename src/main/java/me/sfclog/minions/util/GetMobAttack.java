package me.sfclog.minions.util;


import org.bukkit.entity.*;

public class GetMobAttack {


    public static boolean attack(Entity e) {
            if(!(e instanceof  Player) && !(e instanceof ArmorStand)) {
                return true;
        }
        return false;

    }
}
