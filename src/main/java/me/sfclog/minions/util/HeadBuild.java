package me.sfclog.minions.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class HeadBuild {

    public static ItemStack getHead(String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner(texture);
        head.setItemMeta(meta);
        return head;
    }

    public static ItemStack getHead2(String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        UUID hashAsId = new UUID(texture.hashCode(), texture.hashCode());
        Bukkit.getUnsafe().modifyItemStack(head, "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + texture + "\"}]}}}");
        return head;
    }

}
