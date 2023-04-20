package me.sfclog.minions.MinionSystem;

import me.sfclog.minions.MinionManage.MinionType;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.util.HeadBuild;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinionItem {

    static HashMap<List<String>,Integer> lore = new   HashMap<List<String>,Integer>();

    static ArrayList<String> name = new  ArrayList<String>();

    public static int getLevelMinion(ItemStack i) {
        ItemMeta meta = i.getItemMeta();
        if(meta != null) {
            if(lore.containsKey(meta.getLore())) {
                return lore.get(meta.getLore());
            }
        }
        return 0;

    }




    public static void loadMinion() {
        load_name_minion();
        //MinerSummon
        for (int i = 1; i <= 6; i++) {
            List<String> s = Lang.getarray("Minion.ItemSummon.MinerSummon.Lore");
            if(s != null) {
                String fix = MinionLevel.getLevel(i);
                s.replaceAll(a -> a.replace("<minion_level>", fix));
                lore.put(s,i);
            }
        }
        //SlayerSummon
        for (int i = 1; i <= 6; i++) {
            List<String> s = Lang.getarray("Minion.ItemSummon.SlayerSummon.Lore");
            if(s != null) {
                String fix = MinionLevel.getLevel(i);
                s.replaceAll(a -> a.replace("<minion_level>", fix));
                lore.put(s,i);
            }
        }
        //FarmerSummon
        for (int i = 1; i <= 6; i++) {
            List<String> s = Lang.getarray("Minion.ItemSummon.FarmerSummon.Lore");
            if(s != null) {
                String fix = MinionLevel.getLevel(i);
                s.replaceAll(a -> a.replace("<minion_level>", fix));
                lore.put(s,i);
            }
        }
         //Feed
        for (int i = 1; i <= 6; i++) {
            List<String> s = Lang.getarray("Minion.ItemSummon.FeedSummon.Lore");
            if(s != null) {
                String fix = MinionLevel.getLevel(i);
                s.replaceAll(a -> a.replace("<minion_level>", fix));
                lore.put(s,i);
            }
        }

        //LumberJack
        for (int i = 1; i <= 6; i++) {
            List<String> s = Lang.getarray("Minion.ItemSummon.LumberJackSummon.Lore");
            if(s != null) {
                String fix = MinionLevel.getLevel(i);
                s.replaceAll(a -> a.replace("<minion_level>", fix));
                lore.put(s,i);
            }
        }
    }

    public static void load_name_minion() {
        name.add(Lang.getlang("Minion.MinionName.Miner"));
        name.add(Lang.getlang("Minion.MinionName.Slayer"));
        name.add(Lang.getlang("Minion.MinionName.Builder"));
        name.add(Lang.getlang("Minion.MinionName.Seller"));
        name.add(Lang.getlang("Minion.MinionName.Farmer"));
        name.add(Lang.getlang("Minion.MinionName.Feed"));
        name.add(Lang.getlang("Minion.MinionName.LumberJack"));
    }



    public static Material getItemMinion(MinionType type , int i) {
        if(type == MinionType.MINER) {
            if(i == 1) {
                return Material.WOODEN_PICKAXE;
            }
            if(i == 2) {
                return Material.STONE_PICKAXE;
            }
            if(i == 3) {
                return Material.IRON_PICKAXE;
            }
            if(i == 4) {
                return Material.GOLDEN_PICKAXE;
            }
            if(i == 5) {
                return Material.DIAMOND_PICKAXE;
            }
            if(i == 6) {
                return Material.NETHERITE_PICKAXE;
            }
        }
        if(type == MinionType.SLAYER) {
            if(i == 1) {
                return Material.WOODEN_SWORD;
            }
            if(i == 2) {
                return Material.IRON_SWORD;
            }
            if(i == 3) {
                return Material.GOLDEN_SWORD;
            }
            if(i == 4) {
                return Material.DIAMOND_SWORD;
            }
            if(i >= 5) {
                return Material.NETHERITE_SWORD;
            }
        }
        if(type == MinionType.FARMER) {
            if(i == 1) {
                return Material.WOODEN_HOE;
            }
            if(i == 2) {
                return Material.IRON_HOE;
            }
            if(i == 3) {
                return Material.GOLDEN_HOE;
            }
            if(i == 4) {
                return Material.DIAMOND_HOE;
            }
            if(i >= 5) {
                return Material.NETHERITE_HOE;
            }
        }
        if(type == MinionType.FEED) {
            if(i == 1) {
                return Material.APPLE;
            }
            if(i == 2) {
                return Material.COOKED_BEEF;
            }
            if(i == 3) {
                return Material.COOKED_CHICKEN;
            }
            if(i == 4) {
                return Material.GOLDEN_APPLE;
            }
            if(i >= 5) {
                return Material.ENCHANTED_GOLDEN_APPLE;
            }
        }
        if(type == MinionType.LUMBERJACK) {
            if(i == 1) {
                return Material.WOODEN_AXE;
            }
            if(i == 2) {
                return Material.IRON_AXE;
            }
            if(i == 3) {
                return Material.GOLDEN_AXE;
            }
            if(i == 4) {
                return Material.DIAMOND_AXE;
            }
            if(i >= 5) {
                return Material.NETHERITE_AXE;
            }
        }

        return Material.AIR;

    }


    public static ItemStack getMinion(Player p , MinionType type , int i) {
        if(type == MinionType.MINER) {
            ItemStack item = HeadBuild.getHead(p.getName());
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setDisplayName(Lang.getlang("Minion.MinionName.Miner"));
            List<String> s = Lang.getarray("Minion.ItemSummon.MinerSummon.Lore");
            s.replaceAll(a -> a.replace("<minion_level>", MinionLevel.getLevel(i)));
            meta.setLore(s);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
            return item;
        }
        if(type == MinionType.SLAYER) {
            ItemStack item = HeadBuild.getHead(p.getName());
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setDisplayName(Lang.getlang("Minion.MinionName.Slayer"));
            List<String> s = Lang.getarray("Minion.ItemSummon.SlayerSummon.Lore");
            s.replaceAll(a -> a.replace("<minion_level>", MinionLevel.getLevel(i)));
            meta.setLore(s);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
            return item;
        }
        if(type == MinionType.FARMER) {
            ItemStack item = HeadBuild.getHead(p.getName());
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setDisplayName(Lang.getlang("Minion.MinionName.Farmer"));
            List<String> s = Lang.getarray("Minion.ItemSummon.FarmerSummon.Lore");
            s.replaceAll(a -> a.replace("<minion_level>", MinionLevel.getLevel(i)));
            meta.setLore(s);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
            return item;
        }


        if(type == MinionType.FEED) {
            ItemStack item = HeadBuild.getHead(p.getName());
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setDisplayName(Lang.getlang("Minion.MinionName.Feed"));
            List<String> s = Lang.getarray("Minion.ItemSummon.FeedSummon.Lore");
            s.replaceAll(a -> a.replace("<minion_level>", MinionLevel.getLevel(i)));
            meta.setLore(s);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
            return item;
        }

        if(type == MinionType.LUMBERJACK) {
            ItemStack item = HeadBuild.getHead(p.getName());
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setDisplayName(Lang.getlang("Minion.MinionName.LumberJack"));
            List<String> s = Lang.getarray("Minion.ItemSummon.LumberJackSummon.Lore");
            s.replaceAll(a -> a.replace("<minion_level>", MinionLevel.getLevel(i)));
            meta.setLore(s);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
            return item;
        }


        return null;
    }



    public static boolean is_minion(ItemStack i) {
        ItemMeta meta = i.getItemMeta();
        if(meta != null) {
            if(name.contains(meta.getDisplayName())) {
                return true;
            }
        }
        return false;
    }


    public static MinionType getTypeMinion(ItemStack i) {
        ItemMeta meta = i.getItemMeta();
        if (meta != null) {

            if(meta.getDisplayName().equals(Lang.getlang("Minion.MinionName.Miner"))) {
                return MinionType.MINER;
            }
            if(meta.getDisplayName().equals(Lang.getlang("Minion.MinionName.Slayer"))) {
                return MinionType.SLAYER;
            }
            if(meta.getDisplayName().equals(Lang.getlang("Minion.MinionName.Farmer"))) {
                return MinionType.FARMER;
            }
            if(meta.getDisplayName().equals(Lang.getlang("Minion.MinionName.Feed"))) {
                return MinionType.FEED;
            }
            if(meta.getDisplayName().equals(Lang.getlang("Minion.MinionName.LumberJack"))) {
                return MinionType.LUMBERJACK;
            }

        }
        return null;
    }

}
