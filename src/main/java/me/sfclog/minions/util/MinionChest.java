package me.sfclog.minions.util;


import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MinionChest {

    private Chest chest;


    public MinionChest(Chest chest) {

        this.chest = chest;
    }

    public boolean addItem(ItemStack i) {
        if (isItemNotFull(i)) {
            chest.getInventory().addItem(new ItemStack[] { i });
            return true;
        }
        return false;
    }



    public boolean isItemNotFull(ItemStack item) {
            for (ItemStack list : chest.getInventory().getContents()) {
                if (list != null && list.getType() != null) {
                    if (!(list.getAmount() >= list.getMaxStackSize())) {
                       if(list.getType() == item.getType()) {
                           return true;
                       }
                    }
                 } else {
                   return true;
             }
        }
        return false;

    }

    public boolean haveblock() {
        for (ItemStack list : chest.getInventory().getContents()) {
            if (list != null && list.getType() != null && list.getType().isBlock()) {
                return true;
            }
          }
        return false;

    }


    public boolean havefood() {
        for (ItemStack list : chest.getInventory().getContents()) {
            if (list != null && list.getType() != null && list.getType().isEdible()) {
                return true;
            }
        }
        return false;

    }

    public boolean isFull() {
       return chest.getInventory().firstEmpty() == -1;
    }
}