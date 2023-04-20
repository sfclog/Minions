package me.sfclog.minions.event;



import me.sfclog.minions.MinionManage.MinionType;
import me.sfclog.minions.MinionSystem.*;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.util.Send;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.MoistureChangeEvent;
import org.bukkit.inventory.ItemStack;


public class PlayerEvent implements Listener {


    @EventHandler (priority = EventPriority.HIGHEST)
    public void farmland(MoistureChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void summon(BlockPlaceEvent e) {
        if (!e.isCancelled()) {
            Player p = e.getPlayer();
            Location oldLoc = e.getBlock().getLocation();
            Location locfix = new Location(oldLoc.getWorld(), oldLoc.getBlockX() + 0.5D, oldLoc.getBlockY(), oldLoc.getBlockZ() + 0.5D);
            if (p != null && p.getInventory() != null) {
                ItemStack i = p.getInventory().getItemInMainHand();
                if (i != null) {
                    if (i.getType() != null && i.getType() == Material.PLAYER_HEAD) {
                        if (MinionItem.is_minion(i)) {
                            if (!Lang.getb("DisableServer")) {
                                if(!MinionManage.have_minion_at_loc(locfix)) {
                                    int level = MinionItem.getLevelMinion(i);
                                    MinionType type = MinionItem.getTypeMinion(i);
                                    if (type != null && !(level <= 0)) {
                                        i.setAmount(i.getAmount() - 1);
                                        p.updateInventory();
                                        MinionManage.create_minion(p, type, locfix, level);
                                        Send.send(p, Lang.getlang("Minion.Lang.MinionPlace"));
                                        p.playSound(p.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,100,1);
                                    }
                                } else {
                                    p.playSound(p.getLocation(),Sound.ENTITY_VILLAGER_NO,100,1);
                                    Send.send(p,Lang.getlang("Minion.Lang.HaveMinionAtLocation"));
                                }

                                e.setCancelled(true);

                               } else {
                                Send.send(p, Lang.getlang("Minion.Lang.MinionDisable"));
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
