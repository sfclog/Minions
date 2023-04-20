package me.sfclog.minions.util;

import me.sfclog.minions.lang.Lang;
import org.bukkit.entity.Player;

public class Send {

    public static void send(Player p , String s) {
        p.sendMessage(Lang.getlang("Plugin.Prefix") + Color.tran(s));
    }
}
