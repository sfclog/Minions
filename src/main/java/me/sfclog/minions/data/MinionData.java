package me.sfclog.minions.data;

import me.sfclog.minions.Main;
import me.sfclog.minions.util.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MinionData {

    public static File locate = new File("plugins/Minions/", "data.yml");
    public static FileConfiguration DataFile = (FileConfiguration)YamlConfiguration.loadConfiguration(locate);





    public static void save() {
        try {
            DataFile.save(locate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getarray(String s) {
        if(DataFile.contains(s)) {
            List<String> ss = new ArrayList<String>();
            for(String ok : DataFile.getStringList(s)) {
                ss.add(Color.tran(ok));
            }
            return ss;
        }
        return null;
    }
    public static int getint(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getInt(s);
        }
        return 0;
    }
    public static double getdoubl(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getDouble(s);
        }
        return 0;
    }
    public static boolean getb(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getBoolean(s);
        }
        return false;
    }
    public static String getlang(String s) {
        if(DataFile.contains(s)) {
            return Color.tran(DataFile.getString(s));
        }
        return s;
    }

    public static String getlang_nocolor(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getString(s);
        }
        return null;
    }

    public static void addlang(String s , double s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , Boolean s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , List<String> s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void setforcelang(String s , String s2) {
        DataFile.set(s, s2);
    }
    public static void setforcelang(String s, double x) {
        DataFile.set(s, x);
    }

    public static void setforcelang(String s, boolean x) {
        DataFile.set(s, x);
    }
    public static void addlang(String s , String s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , int s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }


    public static boolean have(String hash) {
        if(DataFile.contains("Data.Minion." + hash)) {
            return true;
        }
        return false;
    }

    public static Location getLocation(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getLocation(s);
        }
        return null;
    }

    public static void setforceloc(String s, Location loc) {
        DataFile.set(s,loc);
    }



    public static List<ItemStack> getlistitem(String s) {
        List<ItemStack> list = new ArrayList<>();
        List<String> it = getarray(s);
        if(it != null) {
        for(String item : it) {
            if(item!= null) {
                String[] m = item.split(":");
                    Material mt = Material.valueOf(m[0]);
                    int amount = Integer.parseInt(m[1]);
                    if(mt != null) {
                        ItemStack itm = new ItemStack(mt, amount);
                        if(itm!= null) {
                            list.add(itm);

                        }
                    }
                }
            }
        }
        return list;
    }



    public static void setlistitem(String s , List<ItemStack> itemdrop) {
        List<String> list = new ArrayList<>();
        for(ItemStack m : itemdrop) {
            if(m != null) {
                list.add(m.getType().name()+":"+m.getAmount());
            }
        }
        DataFile.set(s,list);
    }

}
