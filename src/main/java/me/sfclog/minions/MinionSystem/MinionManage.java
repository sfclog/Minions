package me.sfclog.minions.MinionSystem;

import me.sfclog.minions.Main;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionManage.MinionType;
import me.sfclog.minions.data.MinionData;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.runnable_core.AsyncRunnable;
import me.sfclog.minions.util.DecodeEndCore;
import me.sfclog.minions.util.LocationUtil;
import me.sfclog.minions.util.ProtocolLibPacketEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class MinionManage {


    public static HashSet<Minion> map = new HashSet();


    public static void load_minion_in_config() {
        if (MinionData.DataFile.getConfigurationSection("Data.Minion") != null) {
            for (String basedata : MinionData.DataFile.getConfigurationSection("Data.Minion").getKeys(false)) {
                if (basedata != null) {
                    Location loc = LocationUtil.get_location_cover(DecodeEndCore.decode(basedata));
                    if (loc != null) {
                        String data = MinionData.getlang("Data.Minion." + basedata + ".Type");

                        MinionType type = data != null ? MinionType.valueOf(data) : null;
                        int tier = MinionData.getint("Data.Minion." + basedata + ".Tier");
                        String own = MinionData.getlang("Data.Minion." + basedata + ".Owner");
                        Location chestlinker = MinionData.getLocation("Data.Minion." + basedata + ".ChestLinker");
                        List<ItemStack> minioninventory = MinionData.getlistitem("Data.Minion." + basedata + ".MinionInventory");

                        if (type != null) {
                            Bukkit.getScheduler().runTask(Main.pl, () -> {
                                Minion minion = new Minion(basedata, own, type, loc, chestlinker, minioninventory != null ? minioninventory : new ArrayList<>(), tier);
                                if (minion != null) {
                                    map.add(minion);
                                }
                            });
                        }
                    }
                }

            }
        }
    }





    public static void create_minion(Player p , MinionType type, Location loc, int tier) {
        String hash = LocationUtil.cove_location(loc);
        if(!MinionData.have(hash)) {
            MinionData.addlang("Data.Minion." + hash + ".Owner", p.getName());
            MinionData.addlang("Data.Minion." + hash + ".Type", type.toString());
            MinionData.addlang("Data.Minion." + hash + ".Tier", tier);

            MinionData.save();
            Bukkit.getScheduler().runTask(Main.pl, () -> {
                Minion minion = new Minion(hash, p.getName(), type, loc,null,null, tier);
                if (minion != null) {
                    map.add(minion);
                }
            });
        }
    }





    public static boolean remove(Minion m) {
        if (m != null) {
            ProtocolLibPacketEntity.clear_minion(m);
            m.drop_inventory();
            m.unload_holo();
            MinionData.setforcelang("Data.Minion." + m.getHashBase(), null);
            MinionData.save();
            map.remove(m);
            return true;
        }
        return false;
    }




    public static boolean is_minion(Zombie armorStand) {
        String name = armorStand.getCustomName();
        if(name != null) {
           if(name.contains(Lang.getlang("Minion.ItemSummon.MinerSummon.Name").replace("<owner>","")))  {
               return true;
           }
            if(name.contains(Lang.getlang("Minion.ItemSummon.SlayerSummon.Name").replace("<owner>","")))  {
                return true;
            }
            if(name.contains(Lang.getlang("Minion.ItemSummon.BuilderSummon.Name").replace("<owner>","")))  {
                return true;
            }
            if(name.contains(Lang.getlang("Minion.ItemSummon.SellerSummon.Name").replace("<owner>","")))  {
                return true;
            }
            if(name.contains(Lang.getlang("Minion.ItemSummon.FarmerSummon.Name").replace("<owner>","")))  {
                return true;
            }
            if(name.contains(Lang.getlang("Minion.ItemSummon.FeedSummon.Name").replace("<owner>","")))  {
                return true;
            }
            if(name.contains(Lang.getlang("Minion.ItemSummon.LumberJackSummon.Name").replace("<owner>","")))  {
                return true;
            }

        }
        return false;
    }

    public static void start(Minion m) {
        if(Main.pl != null) {
            if (m.getStart() == false) {
                m.setStart(true);
                if (!Lang.getb("DisableServer")) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            m.check();
                            AsyncRunnable.update_minion(m);

                            if (Main.pl == null || !MinionManage.have(m) ||  m.getFood() <= 0) {
                                m.setStart(false);
                                this.cancel();
                        }
                       }
                    }.runTaskTimer(Main.pl, MinionLevel.getWorkSpeed(m.getType(), m.gettier()) , MinionLevel.getWorkSpeed(m.getType(),m.gettier()));
                }
            }
        }
    }



    public static boolean have(Minion mi) {
        if(map.contains(mi)) {
            return true;
        }
        return false;
    }

    public static void getMinionNearPlayerAndRun(Player p) {
        List<Minion> minion =  map.stream().filter(mini -> check(mini,p)).collect(Collectors.toList());;
        for(Minion m : minion) {
            if(m != null) {
             m.add_show(p);
             MinionManage.start(m);
            }
        }
    }



    public static List<Minion> getMinionNearLocation(Location loc) {
        List<Minion> minion =  map.stream().filter(mini -> check(mini,loc)).collect(Collectors.toList());;
        return minion;
    }

    private static boolean check(Minion mini, Location loc) {
        if(mini.getLoc() != null && loc != null) {
            if (mini.getLoc().getWorld() == loc.getWorld()) {
                if(mini.getLoc().distance(loc) < 30) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean check(Minion mini, Player p) {
        if(mini.getLoc() != null) {
            if (mini.getLoc().getWorld().getName().equals(p.getWorld().getName())) {
                if (mini.getLoc().distance(p.getLocation()) < 30) {
                    return true;
                }
            }
        }
        return false;
    }


    public static Minion getMinion(int targetId) {
        for(Minion m : map) {
            if(m.have(targetId)) {
                return m;
            }
        }
        return null;
    }




    public static List<Integer> getID() {
        List<Integer> listid = new ArrayList<>();
          for(Minion m : map) {
              if (m != null) {
                  listid.addAll(m.getIds());
              }
          }
        return listid;
    }

    public static boolean have_minion_at_loc(Location locfix) {
        for(Minion m : map) {
            if(m.getLoc().equals(locfix)) {
                return true;
            }
        }
        return false;
    }
}


