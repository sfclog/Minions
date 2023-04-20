package me.sfclog.minions.MinionSystem;

import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.util.CheckCustomItem;
import me.sfclog.minions.util.ProtocolLibPacketEntity;
import me.sfclog.minions.util.Send;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinionMCPEGui implements Listener {

    public static HashMap<Player,Minion> cache = new HashMap<Player,Minion>();


    @EventHandler
    public void inv_close(InventoryCloseEvent e) {
        if (e.getInventory() != null && e.getView() != null && e.getView().getTitle().contains(Lang.getlang("Minion.Gui.PE.MinionInventory.Title"))) {
            Player p = (Player) e.getPlayer();
            if (cache.get(p) != null) {
                Minion mi = cache.get(p);
                if (mi != null) {

                    List<ItemStack> list = new ArrayList<>();
                    for(ItemStack item : e.getInventory().getContents()) {
                        if(item != null) {
                            if(CheckCustomItem.check_custome_item(item)) {
                                list.add(item);
                            }
                        }
                    }
                    mi.replace_inventory(list);
                    send(p,mi);
                }
            }
        }
    }



    public static void send_minion_inventory(Player p, Minion mi) {
     // cache open minion
     if(cache.containsKey(p)) {
            cache.remove(p);
            cache.put(p,mi);
     } else {
            cache.put(p,mi);
     }
    Inventory inv = Bukkit.createInventory(null, 27, Lang.getlang("Minion.Gui.PE.MinionInventory.Title"));
    for(ItemStack item : mi.getMinionInventory()) {
        if(item != null && item.getType() != null && item.getType() != Material.BEDROCK) {
            inv.addItem(item);
        }
    }
    p.openInventory(inv);
    }



    public static void send(Player p , Minion m) {

        if(p != null) {
            FloodgatePlayer player = FloodgateApi.getInstance().getPlayer(p.getUniqueId());

            if(player != null) {

                SimpleForm.Builder build = SimpleForm.builder();

                build.title(Lang.getlang("Minion.Gui.PE.Title"));
                List<String> s = Lang.getarray("Minion.Gui.PE.MinionInfo");

                s.replaceAll(a -> a.replace("<minion_level>", MinionLevel.getLevel(m.gettier())));
                s.replaceAll(a -> a.replace("<minion_name>", MinionLevel.getName(m.getType()).replace("(<owner>)","")));
                s.replaceAll(a -> a.replace("<minion_speed>", ""+MinionLevel.getWorkSpeed(m.getType(),m.gettier())));
                s.replaceAll(a -> a.replace("<owner>", m.getOwner()));
                s.replaceAll(a -> a.replace("<food_max>", String.valueOf(MinionLevel.getFoodUpdate(m.gettier()))));
                s.replaceAll(a -> a.replace("<food>", String.valueOf(m.getSumFood())));
                s.replaceAll(a -> a.replace("<link_chest>", String.valueOf(m.getFood())));


                Location loc = m.getChestLinker();
                if (loc != null) {
                    s.replaceAll(a -> a.replace("<chest_linker_status>", Lang.getlang("Minion.Lang.ChestLinkerStatus.IsLinker")
                            .replace("<x>", String.valueOf(loc.getX()))
                            .replace("<y>", String.valueOf(loc.getY()))
                            .replace("<z>", String.valueOf(loc.getZ()))
                    ));
                } else {
                    s.replaceAll(a -> a.replace("<chest_linker_status>", Lang.getlang("Minion.Lang.ChestLinkerStatus.NotLinker")));
                }

                StringBuilder sb = new StringBuilder();
                for(String in : s) {
                    if(in != null) {
                        sb.append(in).append("\n");
                    }
                }

                build.content(sb.substring(0, sb.length() - 1));

                if(m.gettier() >= 6) {
                    build.button(Lang.getlang("Minion.Gui.PE.ButtonUpdateMax.Title"), FormImage.Type.URL, Lang.getlang("Minion.Gui.PE.ButtonUpdateMax.Image"));
                } else {
                    build.button(Lang.getlang("Minion.Gui.PE.ButtonUpdate.Title").replace("<money>",String.valueOf(MinionLevel.getMoneyUpdate(m.gettier() + 1))), FormImage.Type.URL, Lang.getlang("Minion.Gui.PE.ButtonUpdate.Image"));
                }

                build.button(Lang.getlang("Minion.Gui.PE.LinkChest.Title"), FormImage.Type.URL, Lang.getlang("Minion.Gui.PE.LinkChest.Image"));
                build.button(Lang.getlang("Minion.Gui.PE.Invetory.Title"), FormImage.Type.URL, Lang.getlang("Minion.Gui.PE.Invetory.Image"));
                build.button(Lang.getlang("Minion.Gui.PE.ButtonRemove.Title"), FormImage.Type.URL, Lang.getlang("Minion.Gui.PE.ButtonRemove.Image"));


                build.validResultHandler(response -> {
                    int i = response.clickedButtonId();
                    if(i == 0) {
                        MinionLevel.up_level(p, m);
                    }
                    if(i == 1) {
                        if(m.gettier() >= 6) {
                            MinionChestHook.start_hook_chest(p, m);
                        }else{
                            Send.send(p, Lang.getlang("Minion.Lang.LinkerLocked"));
                        }
                    }
                    if(i == 2) {
                        Location loc1 = m.getChestLinker();
                        if (loc1 == null) {
                            send_minion_inventory(p,m);
                        } else {
                            Send.send(p,Lang.getlang("Minion.Lang.LinkerIsWork"));
                        }

                    }
                    if(i == 3) {
                        if (MinionManage.remove(m)) {
                            ProtocolLibPacketEntity.clear_minion(m);
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100, 1);

                            if (p.getInventory().firstEmpty() == -1) {
                                p.getWorld().dropItem(p.getLocation(), MinionItem.getMinion(p, m.getType(), m.gettier()));
                            } else {
                                p.getInventory().addItem(MinionItem.getMinion(p, m.getType(), m.gettier()));
                            }
                            Send.send(p, Lang.getlang("Minion.Lang.MinionBreak"));
                            p.closeInventory();
                        }
                    }
                });







                player.sendForm(build);
            }

        }
    }
}
