package me.sfclog.minions.MinionSystem;

import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.util.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinionGui implements Listener {

    public static HashMap<Player,Minion> cache = new HashMap<Player,Minion>();


    @EventHandler
    public void inv_close(InventoryCloseEvent e) {
        if (e.getInventory() != null && e.getView() != null && e.getView().getTitle().contains(Lang.getlang("Minion.Gui.PC.Title"))) {
            Player p = (Player) e.getPlayer();
            if (cache.get(p) != null) {
                Minion mi = cache.get(p);
                if (mi != null && mi.getChestLinker() == null) {
                    List<ItemStack> list = new ArrayList<>();
                    for(Integer i : MinionInventory.getAllSlot(mi.gettier())) {
                        ItemStack item = e.getInventory().getItem(i);
                        if(item != null) {
                            if(CheckCustomItem.check_custome_item(item)) {
                                list.add(item);
                            } else {
                                if (p.getInventory().firstEmpty() == -1) {
                                    p.getWorld().dropItem(p.getLocation(), item);
                                } else {
                                    p.getInventory().addItem(item);
                                }
                            }
                        }
                    }
                    mi.replace_inventory(list);
                }
            }
        }
    }




    @EventHandler
    public void inv_click(InventoryDragEvent e) {
        if (e.getInventory() != null && e.getView() != null && e.getView().getTitle().contains(Lang.getlang("Minion.Gui.PC.Title"))) {
            ItemStack item = e.getCursor();
            if(item != null && !CheckCustomItem.check_custome_item(item)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inv_click(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory() != null && e.getClickedInventory() != null  && e.getView() != null && e.getView().getTitle().contains(Lang.getlang("Minion.Gui.PC.Title"))) {


               if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
                 return;
               }

                int slot = e.getSlot();
                if (cache.get(p) != null) {
                    Minion mi = cache.get(p);
                    if (mi != null) {

                        List<Integer> sl = MinionInventory.getAllSlot(mi.gettier());
                        if(!sl.contains(sl)) {
                            e.setCancelled(true);
                        }

                        if (slot == 53) {
                            if (MinionManage.remove(mi)) {
                                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100, 1);
                                if (p.getInventory().firstEmpty() == -1) {
                                    p.getWorld().dropItem(p.getLocation(), MinionItem.getMinion(p, mi.getType(), mi.gettier()));
                                } else {
                                    p.getInventory().addItem(MinionItem.getMinion(p, mi.getType(), mi.gettier()));
                                }
                                Send.send(p, Lang.getlang("Minion.Lang.MinionBreak"));
                                p.closeInventory();
                            }
                        }

                        if (slot == 52) {
                            if(mi.getChestLinker() == null) {
                                if(e.getView().getTopInventory() != null) {
                                    Inventory inv = e.getView().getTopInventory();
                                    if(inv != null) {
                                        MinionInventory.back_item(p,inv,mi);
                                        Send.send(p,Lang.getlang("Minion.Lang.GetAll"));
                                        p.closeInventory();
                                    }
                                }
                            } else {
                                Send.send(p, Lang.getlang("Minion.Lang.GetAllDisable"));
                            }
                        }

                        if (slot == 19) {
                            if (cache.containsKey(p)) {
                                Minion m = cache.get(p);
                                if (m != null) {
                                    MinionLevel.up_level(p, m);
                                }
                            }

                        }
                        if (slot == 28 ) {
                            if(mi.gettier() >= 6) {
                                if (cache.containsKey(p)) {
                                    Minion m = cache.get(p);
                                    if (m != null) {
                                        p.closeInventory();
                                        MinionChestHook.start_hook_chest(p, m);
                                    }
                                }
                            } else {
                                Send.send(p, Lang.getlang("Minion.Lang.LinkerLocked"));
                            }
                        }
                    } else {
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }







    private boolean check(int slot , int gettier) {
        if(gettier == 1) {
            if(slot == 12 || slot == 13 || slot == 14 || slot == 15 || slot == 16 //1
                    || slot == 21 || slot == 22 || slot == 23 || slot == 24 || slot == 25 //2
            ) {
                return false;
            }
        }
        if(gettier == 2) {
            if(slot == 12 || slot == 13 || slot == 14 || slot == 15 || slot == 16 //1
                    || slot == 21 || slot == 22 || slot == 23 || slot == 24 || slot == 25 //2
                    || slot == 30 || slot == 31 || slot == 32 || slot == 33 || slot == 34 // 3

            ) {
                return false;
            }
        }
        if(gettier >= 3) {
            if(slot == 12 || slot == 13 || slot == 14 || slot == 15 || slot == 16 //1

                    || slot == 21 || slot == 22 || slot == 23 || slot == 24 || slot == 25 //2

                    || slot == 30 || slot == 31 || slot == 32 || slot == 33 || slot == 34 // 3

                    || slot == 39 || slot == 40 || slot == 41 || slot == 42 || slot == 43 // 4
            ) {
                return false;
            }
        }
        return true;
    }

    public static void opengui(Player p, Minion m) {
        p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN,100,1);
        // cache open minion
        if(cache.containsKey(p)) {
            cache.remove(p);
            cache.put(p,m);
        } else {
            cache.put(p,m);
        }
        Inventory inv = Bukkit.createInventory(null, 54, Lang.getlang("Minion.Gui.PC.Title"));
        ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta blackmeta = black.getItemMeta();
        blackmeta.setDisplayName(Color.tran("&f"));
        black.setItemMeta(blackmeta);


        inv.setItem(0,black);
        inv.setItem(1,black);
        inv.setItem(2,black);
        inv.setItem(3,black);
        inv.setItem(4,black);
        inv.setItem(5,black);
        inv.setItem(6,black);
        inv.setItem(7,black);
        inv.setItem(8,black);

        inv.setItem(9,black);
        inv.setItem(17,black);

        inv.setItem(18,black);
        inv.setItem(26,black);

        inv.setItem(27,black);
        inv.setItem(35,black);

        inv.setItem(36,black);
        inv.setItem(44,black);

        inv.setItem(45,black);
        inv.setItem(46,black);
        inv.setItem(47,black);
        inv.setItem(48,black);
        inv.setItem(49,black);

        inv.setItem(50,black);
        inv.setItem(51,black);


        inv.setItem(11,black);
        inv.setItem(20,black);
        inv.setItem(29,black);
        inv.setItem(38,black);


        inv.setItem(52, ItemStackBuilder.build(Lang.getlang("Minion.Gui.PC.Item.GetAll.Title"),Lang.getarray("Minion.Gui.PC.Item.GetAll.Lore"),Lang.getlang("Minion.Gui.PC.Item.GetAll.Type")));



        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(Lang.getlang("Minion.Gui.PC.Item.Info.Title"));
        List<String> s = Lang.getarray("Minion.Gui.PC.Item.Info.Lore");

        s.replaceAll(a -> a.replace("<minion_level>", MinionLevel.getLevel(m.gettier())));
        s.replaceAll(a -> a.replace("<minion_name>", MinionLevel.getName(m.getType()).replace("(<owner>)","")));
        s.replaceAll(a -> a.replace("<minion_speed>", ""+MinionLevel.getWorkSpeed(m.getType(),m.gettier())));
        s.replaceAll(a -> a.replace("<owner>", m.getOwner()));

        s.replaceAll(a -> a.replace("<food_max>", String.valueOf(MinionLevel.getFoodUpdate(m.gettier()))));
        s.replaceAll(a -> a.replace("<food>", String.valueOf(m.getSumFood())));


        meta.setLore(s);
        meta.setOwner(p.getName());
        meta.setUnbreakable(true);
        item.setItemMeta(meta);


        inv.setItem(10,item);


        if(!(m.gettier() >= 6)) {
            List<String> s2 = Lang.getarray("Minion.Gui.PC.Item.Update.Lore");
            s2.replaceAll(a -> a.replace("<minion_level>", MinionLevel.getLevel(m.gettier())));
            s2.replaceAll(a -> a.replace("<minion_levelup>", MinionLevel.getLevel(m.gettier() + 1)));
            s2.replaceAll(a -> a.replace("<money>", "" + MinionLevel.getMoneyUpdate(m.gettier() + 1)));
            inv.setItem(19, ItemStackBuilder.build(Lang.getlang("Minion.Gui.PC.Item.Update.Title"), s2, Lang.getlang("Minion.Gui.PC.Item.Update.Type")));
        }else{
            List<String> s1 = Lang.getarray("Minion.Gui.PC.Item.Update2.Lore");
            s1.replaceAll(a -> a.replace("<minion_level>", MinionLevel.getLevel(m.gettier())));
            inv.setItem(19, ItemStackBuilder.build(Lang.getlang("Minion.Gui.PC.Item.Update2.Title"), s1, Lang.getlang("Minion.Gui.PC.Item.Update2.Type")));
        }


        if(m.gettier() >= 6) {
            List<String> chestlinker = Lang.getarray("Minion.Gui.PC.Item.ChestLinker.Lore");
            Location loc = m.getChestLinker();
            if (loc != null) {
                chestlinker.replaceAll(a -> a.replace("<chest_linker_status>", Lang.getlang("Minion.Lang.ChestLinkerStatus.IsLinker")
                        .replace("<x>", String.valueOf(loc.getX()))
                        .replace("<y>", String.valueOf(loc.getY()))
                        .replace("<z>", String.valueOf(loc.getZ()))
                ));
            } else {
                chestlinker.replaceAll(a -> a.replace("<chest_linker_status>", Lang.getlang("Minion.Lang.ChestLinkerStatus.NotLinker")));
            }
            inv.setItem(28, ItemStackBuilder.build(Lang.getlang("Minion.Gui.PC.Item.ChestLinker.Title"), chestlinker, Lang.getlang("Minion.Gui.PC.Item.ChestLinker.Type")));

        } else {
            inv.setItem(28, ItemStackBuilder.build(Lang.getlang("Minion.Gui.PC.Item.ChestLock.Title"),Lang.getarray("Minion.Gui.PC.Item.ChestLock.Lore"),Lang.getlang("Minion.Gui.PC.Item.ChestLock.Type")));
        }


        //inv.setItem(31, ItemStackBuilder.build(Lang.getlang("Minion.Gui.PC.Item.Quit.Title"),Lang.getarray("Minion.Gui.PC.Item.Quit.Lore"),Lang.getlang("Minion.Gui.PC.Item.Quit.Type")));
        inv.setItem(53, ItemStackBuilder.build(Lang.getlang("Minion.Gui.PC.Item.Remove.Title"),Lang.getarray("Minion.Gui.PC.Item.Remove.Lore"),Lang.getlang("Minion.Gui.PC.Item.Remove.Type")));





        List<String> loẻ = new ArrayList<>();
        loẻ.add(" ");
        loẻ.add(Color.tran("&fThông tin:"));
        loẻ.add(" ");
        loẻ.add(Color.tran(" &7&oChúng tôi đang cập nhật"));
        loẻ.add(Color.tran(" &7&otính năng mới cho Minion"));
        loẻ.add(" ");
        loẻ.add(Color.tran(" &eĐóng góp tính năng tại:"));
        loẻ.add(Color.tran(" &1Discord: &ddiscord.gg/simpmc &f#góp-ý"));
        loẻ.add(" ");
        loẻ.add(Color.tran(" By &bSmallFCraft Studio Team"));
        loẻ.add(" ");

        inv.setItem(37, ItemStackBuilder.build(Color.tran("&c&lĐANG CẬP NHẬT"),loẻ,"BARRIER"));




        int i = 0;
        for(ItemStack item1 : m.getMinionInventory()) {
            if(item1 != null) {
                int slot =  MinionInventory.getSlot(i);
                if(slot != -1) {
                    inv.setItem(slot,item1);
                    i++;
                }
            }
        }


        for(Integer is : MinionInventory.getLockSlot(m.gettier())) {
            inv.setItem(is, ItemStackBuilder.build(Lang.getlang("Minion.Gui.PC.Item.SlotLock.Title"),Lang.getarray("Minion.Gui.PC.Item.SlotLock.Lore"),Lang.getlang("Minion.Gui.PC.Item.SlotLock.Type")));
        }

        if(m.getChestLinker() != null) {
            for(Integer list : MinionInventory.slot) {
                inv.setItem(list, ItemStackBuilder.build(Lang.getlang("Minion.Gui.PC.Item.SlotLock2.Title"),Lang.getarray("Minion.Gui.PC.Item.SlotLock2.Lore"),Lang.getlang("Minion.Gui.PC.Item.SlotLock2.Type")));
            }
        }


        p.openInventory(inv);






    }



}
