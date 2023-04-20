package me.sfclog.minions.runnable_core;

import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionManage.MinionStatus;
import me.sfclog.minions.util.BlockUtil;
import me.sfclog.minions.util.GetLocation;
import me.sfclog.minions.util.RandomUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.List;


public class LumberJackUpdate {



    public static void update(Minion mi) {

        for(Block b : GetLocation.getLocationFarmer(mi.getLoc())) {
            if(b != null && b.getType() != null && b.getType().name().contains("SAPLING")) {
                b.applyBoneMeal(BlockFace.UP);
                mi.look(b.getLocation());
            }
        }
        if(mi.blockcache != null && !mi.blockcache.isEmpty() ) {
            mi.blockcache.removeIf( a -> {
                if(a != null && a.getType() != null) {
                    for(ItemStack item : a.getDrops()) {
                        if(item!= null) {
                            if (!AsyncRunnable.put_item_to_chest_down(mi, item)) {
                                if(mi.getChestLinker() == null) {
                                    mi.add_inventory(item);
                                }
                            }
                        }
                    }
                    mi.look(a.getLocation());
                    mi.downfood();
                    a.setType(Material.AIR);
                }
                return true;
            });

        } else {
            List<Block> b = GetLocation.getLumberJack(mi.getLoc());
            if(b != null && !b.isEmpty()) {
                mi.add_blockcache(b);
            } else {
                //trồng cây gây rừng
                for(Block trong : GetLocation.getLocationFarmer(mi.getLoc())) {
                    if(trong != null && trong.getType() != null && trong.getType() == Material.AIR) {
                        if(trong.getLocation() != null) {
                            Location loc = trong.getLocation().clone().add(0,-1,0);
                            if(loc.getBlock() != null && BlockUtil.is_dirt(loc.getBlock())) {
                                Material n = mi.getItemsInInventory("SAPLING");
                                if(n != null) {
                                    trong.setType(n);
                                }
                            }
                        }
                    }
                }
            }
        }




        mi.setStatus(MinionStatus.ISWORK);
    }
}
