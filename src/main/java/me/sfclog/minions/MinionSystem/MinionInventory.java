package me.sfclog.minions.MinionSystem;

import me.sfclog.minions.MinionManage.Minion;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MinionInventory {

    public static List<Integer> slot;


    static {
        slot = new ArrayList<>();
        //1
        slot.add(12);
        slot.add(13);
        slot.add(14);
        slot.add(15);
        slot.add(16);
        //2
        slot.add(21);
        slot.add(22);
        slot.add(23);
        slot.add(24);
        slot.add(25);
        //3
        slot.add(30);
        slot.add(31);
        slot.add(32);
        slot.add(33);
        slot.add(34);
        //4
        slot.add(39);
        slot.add(40);
        slot.add(41);
        slot.add(42);
        slot.add(43);

    }




    public static List<Integer> getAllSlot(int gettier) {
        List<Integer> sl = new ArrayList<>();

        if(gettier == 1) {
            //row1
            sl.add(12);
            sl.add(13);
            sl.add(14);
            sl.add(15);
            sl.add(16);


            //row2
            sl.add(21);
            sl.add(22);
            sl.add(23);
            sl.add(24);
            sl.add(25);

        }

        if(gettier == 2) {

            //row1
            sl.add(12);
            sl.add(13);
            sl.add(14);
            sl.add(15);
            sl.add(16);


            //row2
            sl.add(21);
            sl.add(22);
            sl.add(23);
            sl.add(24);
            sl.add(25);


            sl.add(30);
            sl.add(31);


        }



        if(gettier == 3) {

            //row1
            sl.add(12);
            sl.add(13);
            sl.add(14);
            sl.add(15);
            sl.add(16);


            //row2
            sl.add(21);
            sl.add(22);
            sl.add(23);
            sl.add(24);
            sl.add(25);


            sl.add(30);
            sl.add(31);

            sl.add(32);
            sl.add(33);



        }

        if(gettier == 4) {

            //row1
            sl.add(12);
            sl.add(13);
            sl.add(14);
            sl.add(15);
            sl.add(16);


            //row2
            sl.add(21);
            sl.add(22);
            sl.add(23);
            sl.add(24);
            sl.add(25);


            sl.add(30);
            sl.add(31);

            sl.add(32);
            sl.add(33);

            sl.add(34);
            sl.add(39);

        }

        if(gettier == 5) {

            //row1
            sl.add(12);
            sl.add(13);
            sl.add(14);
            sl.add(15);
            sl.add(16);


            //row2
            sl.add(21);
            sl.add(22);
            sl.add(23);
            sl.add(24);
            sl.add(25);


            sl.add(30);
            sl.add(31);

            sl.add(32);
            sl.add(33);

            sl.add(34);
            sl.add(39);

            sl.add(40);
            sl.add(41);


        }

        if(gettier == 6) {
            return slot;
        }




        return  sl;

    }



    public static List<Integer> getLockSlot(int gettier) {
        List<Integer> slot = new ArrayList<>();
        if(!(gettier >= 2)) {
            slot.add(30);
            slot.add(31);
        }
        if(!(gettier >= 3)) {
            slot.add(32);
            slot.add(33);
        }
        if(!(gettier >= 4)) {
            slot.add(34);
            slot.add(39);
        }
        if(!(gettier >= 5)) {
            slot.add(40);
            slot.add(41);
        }
        if(!(gettier >= 6)) {
            slot.add(42);
            slot.add(43);
        }
        return slot;

    }


    public static int getSlot(int i) {
        if(i <= slot.size() - 1) {
            return slot.get(i);
        }
        return  -1;
    }


    public static List<Integer> getChestLockSlot(int gettier) {
        List<Integer> slot = new ArrayList<>();

        if(!(gettier >= 2)) {
            slot.add(10);
            slot.add(11);
        }

        if(!(gettier >= 3)) {
            slot.add(12);
            slot.add(13);
        }

        if(!(gettier >= 4)) {
            slot.add(14);
            slot.add(15);
        }

        if(!(gettier >= 5)) {
            slot.add(16);
            slot.add(17);
        }
        if(!(gettier >= 6)) {
            slot.add(18);
            slot.add(19);
        }

        slot.add(20);
        slot.add(21);

        slot.add(22);
        slot.add(23);
        slot.add(24);
        slot.add(25);
        slot.add(26);


        return  slot;


    }

    public static void back_item(Player p , Inventory inv, Minion mi) {
        for(Integer list : MinionInventory.getAllSlot(mi.gettier())) {
            ItemStack item = inv.getItem(list);
            if(item != null) {
                if(p.getInventory().firstEmpty() != -1) {
                    p.getInventory().addItem(item);
                } else {
                    p.getWorld().dropItem(p.getLocation(),item);
                }
            }
            inv.setItem(list,new ItemStack(Material.AIR));
        }
    }
}
