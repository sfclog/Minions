package me.sfclog.minions.runnable_core;

import me.sfclog.minions.Main;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionManage.MinionStatus;
import me.sfclog.minions.util.GetLocation;
import me.sfclog.minions.util.PlaySound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class MinerUpdate {

    public static void update(Minion mi) {
        Block b = GetLocation.getBlockMiner(mi.getLoc());
        if (b != null && Main.pl != null) {
            for(ItemStack item : b.getDrops()) {
                if (!AsyncRunnable.put_item_to_chest_down(mi, item)) {
                    if(mi.getChestLinker() == null) {
                        mi.add_inventory(item);
                    } else {
                        mi.setStatus(MinionStatus.FULLCHEST);
                        return;
                    }
                }
                PlaySound.play_sound(b.getLocation(),b.getBlockData().getSoundGroup().getBreakSound(), 10);
                mi.look(b.getLocation());
                mi.downfood();
                b.setType(Material.AIR,true);
                mi.setStatus(MinionStatus.ISWORK);
            }
        }
        mi.setStatus(MinionStatus.ISWORK);
    }
}
