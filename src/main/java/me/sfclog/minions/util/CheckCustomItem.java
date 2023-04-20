package me.sfclog.minions.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CheckCustomItem {

    public static boolean check_custome_item(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if(meta != null) {
            if(meta.hasDisplayName()) {
                return false;
            }
            if(meta.hasLore()) {
                return false;
            }
            if(meta.hasEnchants()) {
                return false;
            }
        }

        return true;
    }
}
