package me.sfclog.minions.runnable_core;


import me.sfclog.minions.Main;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionManage.MinionStatus;
import me.sfclog.minions.MinionManage.MinionType;
import me.sfclog.minions.MinionSystem.MinionManage;
import me.sfclog.minions.util.MinionChest;
import me.sfclog.minions.util.SkyBlockHook;
import me.sfclog.simpshopplus.shopmanage.gui.GuiSell;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleEffect;
import java.util.List;

public class AsyncRunnable {
    public static void update_minion(Minion mi) {
        if (mi != null) {
                if (mi != null && !SkyBlockHook.is_is_offline(mi.getLoc()) && MinionManage.have(mi) && mi != null) {



                        //check full inv


                        if(mi.getType() == MinionType.MINER || mi.getType() == MinionType.FARMER || mi.getType() == MinionType.SLAYER || mi.getType() == MinionType.LUMBERJACK ) {
                            if(mi.getChestLinker() == null) {
                                if (mi.minioninventory.firstEmpty() == -1) {
                                    mi.setStatus(MinionStatus.FULLINVENTORY);
                                    return;
                                }
                            }
                            if(mi.getChestLinker() != null) {
                                if(AsyncRunnable.check_chest_is_full(mi)) {
                                    mi.setStatus(MinionStatus.FULLCHEST);
                                    return;
                                }
                            }
                        }



                        //CHECK FEED
                        if(mi.getFood() <= 0) {
                        mi.setStatus(MinionStatus.ISHUNGRY);
                        return;
                        }


                        if (mi.getType() == MinionType.MINER) {
                            MinerUpdate.update(mi);
                        } else if (mi.getType() == MinionType.SLAYER) {
                            SlayerUpdate.update(mi);
                        } else if (mi.getType() == MinionType.FARMER) {
                            FarmerUpdate.update(mi);
                        } else if (mi.getType() == MinionType.FEED) {
                            FeedUpdate.update(mi);
                        } else if (mi.getType() == MinionType.LUMBERJACK) {
                            LumberJackUpdate.update(mi);
                        }

              }
        }
    }

    private static boolean check_chest_is_full(Minion mi) {
        Chest chest = find_get_chest(mi);
        if (chest != null) {
            MinionChest mchest = new MinionChest(chest);
            if (mchest.isFull()) {
                return true;
            }
        }
        return false;
    }



    public static boolean put_item_to_chest_down(Minion m, ItemStack item) {
        if (item != null && item.getType() != null && item.getType() != Material.AIR ) {
            Chest chest = find_chest_down(m,item);
            if (chest != null) {
                MinionChest mchest = new MinionChest(chest);
                if (mchest.addItem(item)) {
                    ParticleEffect.BARRIER.display(chest.getLocation().add(0.5,0.9,0.5));
                    return true;
                }
            }
        }
        return false;
    }

    public static Material get_item_from_chest_near(Minion m) {
        Chest chest = find_chest(m);
        if (chest != null)
            for (ItemStack list : chest.getInventory().getContents()) {
                if (list != null && list.getType() != null && list.getType().isBlock()) {
                    list.setAmount(list.getAmount() - 1);
                    return list.getType();
                }
            }
        return null;
    }


    public static Material get_food_from_chest_near(Minion m) {
        Chest chest = find_food_chest(m);
        if (chest != null)
            for (ItemStack list : chest.getInventory().getContents()) {
                if (list != null && list.getType() != null && list.getType().isEdible()) {
                    list.setAmount(list.getAmount() - 1);
                    return list.getType();
                }
            }
        return null;
    }

    public static Material get_items_from_chest_near(Minion m,String item) {
        Chest chest = find_get_chest(m);
        if (chest != null)
            for (ItemStack list : chest.getInventory().getContents()) {
                if (list != null && list.getType() != null && list.getType().name().contains(item)) {
                    list.setAmount(list.getAmount() - 1);
                    return list.getType();
                }
            }
        return null;
    }


    public static void get_item_stack_from_chest_near_and_sell(Player p, Minion m) {
        Chest chest = find_sell_chest(m);
        if (chest != null) {
            GuiSell.sell_api(p,chest);
        }
    }



    public static <BlockState> Chest find_chest_down(Minion m, ItemStack item) {
            if (m.getChestLinker() != null) {
                Block b = m.getChestLinker().getBlock();
                if (b != null && b.getType() != null && (
                        b.getType() == Material.LEGACY_CHEST || b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST || b.getType() == Material.CHEST_MINECART)) {
                    Chest chest = (Chest) b.getState();
                    if (chest != null) {
                        MinionChest mchest = new MinionChest(chest);
                        if (mchest.isItemNotFull(item)) {
                            ParticleEffect.BARRIER.display(chest.getLocation().add(0.5,0.9,0.5));
                            return chest;
                        }
                    }
                 } else {
                    m.setChestHook(null);
                    return  null;
                }
            }
            return null;

    }

    public static <BlockState> Chest find_get_chest(Minion m) {
        if(m.getChestLinker() != null) {
            Block b = m.getChestLinker().getBlock();
            if (b != null && b.getType() != null && (
                    b.getType() == Material.LEGACY_CHEST || b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST)) {
                Chest chest = (Chest)b.getState();
                if (chest != null) {
                    MinionChest mchest = new MinionChest(chest);
                    if (mchest.havefood()) {
                        ParticleEffect.BARRIER.display(chest.getLocation().add(0.5,0.9,0.5));
                        return chest;
                    }
                }
            } else {
                m.setChestHook(null);
                return  null;
            }
        }
        return null;
    }

    public static <BlockState> Chest find_food_chest(Minion m) {
        if(m.getChestLinker() != null) {
            Block b = m.getChestLinker().getBlock();
            if (b != null && b.getType() != null && (
                    b.getType() == Material.LEGACY_CHEST || b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST)) {
                Chest chest = (Chest)b.getState();
                if (chest != null) {
                    MinionChest mchest = new MinionChest(chest);
                    if (mchest.havefood()) {
                        ParticleEffect.BARRIER.display(chest.getLocation().add(0.5,0.9,0.5));
                        return chest;
                    }
                }
            } else {
                m.setChestHook(null);
                return  null;
            }
        }
        return null;
    }
    public static <BlockState> Chest find_chest(Minion m) {
        if(m.getChestLinker() != null) {
            Block b = m.getChestLinker().getBlock();
                    if (b != null && b.getType() != null && (
                            b.getType() == Material.LEGACY_CHEST || b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST)) {
                        Chest chest = (Chest)b.getState();
                        if (chest != null) {
                            MinionChest mchest = new MinionChest(chest);
                            if (mchest.haveblock()) {
                                ParticleEffect.BARRIER.display(chest.getLocation().add(0.5,0.9,0.5));
                                return chest;
                            }
                        }
                 } else {
                      m.setChestHook(null);
                      return  null;
                  }
           }
        return null;
    }


    public static <BlockState> Chest find_sell_chest(Minion m) {
        if(m.getChestLinker() != null) {
            Block b = m.getChestLinker().getBlock();
            if (b != null && b.getType() != null && (
                    b.getType() == Material.LEGACY_CHEST || b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST || b.getType() == Material.CHEST_MINECART)) {
                Chest chest = (Chest)b.getState();
                if (chest != null) {
                    MinionChest mchest = new MinionChest(chest);
                    ParticleEffect.BARRIER.display(chest.getLocation().add(0.5,0.9,0.5));
                    return chest;
                }
            } else {
                m.setChestHook(null);
                return  null;
            }
        }
        return null;
    }


    public static Player getnear_player(Location loc) {
        List<Entity> nearbyEntites = (List<Entity>) loc.getWorld().getNearbyEntities(loc, 5, 5, 5);
        for(Entity e : nearbyEntites) {
            if(e != null && e instanceof  Player) {
                Player p = ((Player) e).getPlayer();
                if(p != null) {
                    return p;
                }
            }
        }
        return null;
    }


}
