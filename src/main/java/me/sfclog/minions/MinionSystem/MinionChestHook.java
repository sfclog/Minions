package me.sfclog.minions.MinionSystem;

import me.sfclog.minions.Main;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.runnable_core.AsyncRunnable;
import me.sfclog.minions.util.Send;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class MinionChestHook implements Listener {

    public static HashMap<Player,Minion> hookchestmap = new HashMap<>();


    @EventHandler
    public void onBlockRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() != null) {
            if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if(hookchestmap.containsKey(p)) {
                    Minion mi = hookchestmap.get(p);
                    Block b = e.getClickedBlock();
                    if(b != null) {
                        if(b.getType() != null) {
                           if(b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST || b.getType() == Material.LEGACY_CHEST)  {
                               Location loc = b.getLocation();
                               if(loc != null) {
                                   Send.send(p,Lang.getlang("Minion.Lang.LinkerChestSuccess"));
                                   p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES,100,1);
                                   mi.setChestHook(loc);

                               }

                           } else {
                               Send.send(p,Lang.getlang("Minion.Lang.LinkerChestFail"));
                               p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO,100,1);
                           }
                        }
                    }
                    hookchestmap.remove(p);
                    e.setCancelled(true);
                }
            }
        }
    }

    public static boolean have(Player p) {
        if(hookchestmap.containsKey(p)) {
            return true;
        }
        return false;
    }

    public static void start_hook_chest(Player p , Minion m) {
        if(!hookchestmap.containsKey(p)) {
            hookchestmap.put(p,m);

            Send.send(p, Lang.getlang("Minion.Lang.LinkerChestStart"));
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,100,1);

            new BukkitRunnable() {
                int i = 60;
                @Override
                public void run() {
                    if(i <= 0) {
                        if (hookchestmap.containsKey(p)) {
                            hookchestmap.remove(p);
                            Send.send(p, Lang.getlang("Minion.Lang.LinkerChestTimeOut"));
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL,100,1);
                        }
                        this.cancel();
                    }
                    i--;
                }
            }.runTaskTimer(Main.pl, 20, 20);
        }
    }
}
