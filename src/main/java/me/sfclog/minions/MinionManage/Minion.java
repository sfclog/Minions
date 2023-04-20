package me.sfclog.minions.MinionManage;

import com.github.unldenis.hologram.AbstractLine;
import com.github.unldenis.hologram.Hologram;
import me.sfclog.minions.Main;
import me.sfclog.minions.MinionSystem.MinionCreate;
import me.sfclog.minions.MinionSystem.MinionInventory;
import me.sfclog.minions.MinionSystem.MinionLevel;
import me.sfclog.minions.data.MinionData;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.runnable_core.AsyncRunnable;
import me.sfclog.minions.util.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Minion {
    String hashbase;

    String own;

    MinionType type;

    Location loc;

    int tier, buildy, grow;


    public boolean start, hide;


    public HashMap<Player, Integer> listshow;

    public Location chestlinker;

    public double food;


    public Hologram holoname;

    public ArrayList<Block> blockcache;

    public Inventory minioninventory;

    public MinionStatus status;





    public Minion(String hash, String own, MinionType type, Location loc, Location chestlinker, List<ItemStack> listitem, int tier) {
        this.hashbase = hash;
        this.own = own;
        this.type = type;
        this.loc = loc;
        this.tier = tier;
        this.buildy = 3;
        this.grow = 0;
        this.start = false;
        this.hide = false;
        this.blockcache = new ArrayList<>();
        this.listshow = new HashMap<>();
        this.chestlinker = chestlinker;
        this.food = MinionLevel.getFoodUpdate(tier);
        this.status = MinionStatus.ISWORK;
        // create minion inventory
        this.minioninventory = Bukkit.createInventory(null, 27);

        //fill blamk inv
        for (Integer in : MinionInventory.getChestLockSlot(this.tier)) {
            minioninventory.setItem(in, new ItemStack(Material.BEDROCK, 64));
        }
        // add item to inv
        if (listitem != null) {
            for (ItemStack item : listitem) {
                if (item != null) {
                    this.minioninventory.addItem(item);
                }
            }
        }
        this.load_holo();
    }



    public void add_blockcache(List<Block> list) {
        if (blockcache != null) {
            blockcache.clear();
            blockcache.addAll(list);
        } else {
            blockcache = new ArrayList<>();
            blockcache.addAll(list);
        }
    }


    public void setStatus(MinionStatus status) {
        if (this.status != status) {

            //play sound

            if (status == MinionStatus.ISHUNGRY) {
                PlaySound.play_sound(this.loc, Sound.ENTITY_VILLAGER_NO, 5);
            } else if (status == MinionStatus.ISWORK) {
                PlaySound.play_sound(this.loc, Sound.ENTITY_VILLAGER_YES, 5);
            } else if (status == MinionStatus.FULLINVENTORY) {
                PlaySound.play_sound(this.loc, Sound.BLOCK_CHEST_LOCKED, 5);
                PlaySound.play_sound(this.loc, Sound.ENTITY_VILLAGER_NO, 5);
            }


            this.status = status;
            this.reload_holo();
        }
    }


    public List<ItemStack> getMinionInventory() {
        List<ItemStack> list = new ArrayList<>();
        if (this.minioninventory != null) {
            for (ItemStack i : this.minioninventory.getContents()) {
                if (i != null && i.getType() != null && i.getType() != Material.BEDROCK) {
                    list.add(i);
                }
            }
        }
        return list;
    }


    public boolean have(int targetId) {
        if (this.listshow.values().contains(targetId)) {
            return true;
        }
        return false;
    }


    public void add_show(Player p) {
        if (!this.listshow.containsKey(p)) {
            int id = RandomUtil.getRandomNumber(0, Integer.MAX_VALUE);
            MinionCreate.build_minion(p, own, id, loc, type, tier);
            this.listshow.put(p, id);
        }
    }

    public void check() {
        for (Player pl : clone(listshow)) {
            if (check(pl)) {
                int id = listshow.get(pl);
                ProtocolLibPacketEntity.clear_minion(id);
                listshow.remove(pl);
            }
        }
    }

    private boolean check(Player p) {
        if (Bukkit.getPlayer(p.getName()) == null) {
            return true;
        }
        if (p.getWorld() != loc.getWorld()) {
            return true;
        }
        if (p.getLocation().distance(loc) > 60) {
            return true;
        }
        return false;
    }


    public List<Player> clone(HashMap<Player, Integer> map) {
        List<Player> list = new ArrayList<>();
        list.addAll(map.keySet());
        return list;
    }


    public int getBuildY() {
        return buildy;
    }

    public void UpY() {
        if (buildy < 256) {
            this.buildy = this.buildy + 1;
        }
    }


    public Location getLoc() {
        return this.loc;
    }

    public MinionType getType() {
        return this.type;
    }

    public int gettier() {
        return this.tier;
    }

    public String getHashBase() {
        return this.hashbase;
    }


    public boolean getStart() {
        return start;
    }

    public void setStart(boolean b) {
        this.start = b;
    }

    public void look(Location location) {
        ProtocolLibPacketEntity.setlookat(this, location);
    }


    public boolean isOwn(Player p) {
        if (p != null) {
            if (own.equals(p.getName())) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> getIds() {
        List<Integer> list = new ArrayList<>();
        list.addAll(this.listshow.values());
        return list;

    }

    public void up_level() {
        this.tier = tier + 1;
        this.food = MinionLevel.getFoodUpdate(tier);
        MinionData.setforcelang("Data.Minion." + getHashBase() + ".Tier", tier);
        MinionData.save();
        setStart(false);
    }


    public void save_inventory() {
        MinionData.setlistitem("Data.Minion." + getHashBase() + ".MinionInventory", this.getMinionInventory());
        MinionData.save();
    }

    public void unload_holo() {
        Main.hologramPool.remove(this.holoname);
    }

    public void reload_holo() {
        List<String> list = new ArrayList<>();
        for (String line : Lang.getarray("Minion.MinionHoloName.Line")) {
            if (line != null) {
                list.add(line.replace("<minion_name>",
                                MinionLevel.getName(this.type).replace("(<owner>)", ""))
                        .replace("[owner]", own)
                        .replace("<food>", String.valueOf(getSumFood()))
                        .replace("<max_food>", String.valueOf(MinionLevel.getFoodUpdate(this.tier)))
                        .replace("<status>", MinionStatus.getStatua(this.status)));
            }
        }

        int i = 0;
        for (AbstractLine line : holoname.getLines()) {
            line.set(list.get(i));
            i++;
        }

    }

    public void load_holo() {
        Hologram.Builder holoname = Hologram.builder();
        for (String line : Lang.getarray("Minion.MinionHoloName.Line")) {
            if (line != null) {
                holoname.addLine(line.replace("<minion_name>",
                                        MinionLevel.getName(this.type).replace("(<owner>)", ""))
                                .replace("[owner]", own)
                                .replace("<status>", MinionStatus.getStatua(this.status))
                                .replace("<food>", String.valueOf(getSumFood()))
                                .replace("<max_food>", String.valueOf(MinionLevel.getFoodUpdate(this.tier)))
                        , false);
            }
        }

        holoname.location(this.loc);
        this.holoname = holoname.build(Main.hologramPool);
    }


    public void drop_inventory() {
        for (ItemStack item : this.getMinionInventory()) {
            if (item != null) {
                this.loc.getWorld().dropItem(this.loc, item);
            }
        }
    }


    public String getOwner() {
        return own;
    }

    public double getFood() {
        return food;
    }

    public int getSumFood() {
        return (int) food;
    }

    public void downfood() {
        if (!(this.food <= 0)) {
            this.food = food - 1;
            reload_holo();
        }

    }

    public void setChestHook(Location loc) {
        MinionData.setforceloc("Data.Minion." + getHashBase() + ".ChestLinker", loc);
        MinionData.save();
        this.chestlinker = loc;

        if (this.minioninventory != null) {
            for (ItemStack item : this.minioninventory.getContents()) {
                if (item != null && item.getType() != null && item.getType() != Material.BEDROCK) {
                    if (!AsyncRunnable.put_item_to_chest_down(this, item)) {
                        loc.getWorld().dropItem(loc, item);
                    }
                }
            }
            this.minioninventory.clear();
        }

    }

    public Location getChestLinker() {
        return this.chestlinker;
    }

    public void add_food(int i) {
        if (!(food > MinionLevel.getFoodUpdate(tier))) {
            this.food = this.food + i;
        } else {
            this.food = MinionLevel.getFoodUpdate(tier);
        }
    }

    public int getgrow() {
        return grow;
    }

    public boolean growismax() {
        return grow > 7 ? true : false;
    }

    public void resetgrow() {
        this.grow = 0;
    }

    public void upgrow() {
        this.grow = grow + 1;
    }

    public boolean isLowFood(int i) {
        int maxfood = MinionLevel.getFoodUpdate(tier) - i;
        if (this.food < maxfood) {
            return true;
        }
        return false;
    }

    public boolean add_inventory(ItemStack item) {
        if (this.minioninventory != null) {
            if (isItemNotFull(item)) {
                this.minioninventory.addItem(new ItemStack[]{item});
                return true;
            }

        }
        return false;

    }

    public boolean isItemNotFull(ItemStack item) {
        if (this.minioninventory != null) {
            for (ItemStack list : this.minioninventory.getContents()) {
                if (list != null && list.getType() != null) {
                    if (!(list.getAmount() >= list.getMaxStackSize())) {
                        if (list.getType() == item.getType()) {
                            return true;
                        }
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }


    public void replace_inventory(List<ItemStack> list) {
        this.minioninventory.clear();
        //fill blamk inv
        for (Integer in : MinionInventory.getChestLockSlot(this.tier)) {
            minioninventory.setItem(in, new ItemStack(Material.BEDROCK, 64));
        }
        for (ItemStack item : list) {
            if (item != null) {
                this.minioninventory.addItem(item);
            }
        }

    }

    public Material getItemsInInventory(String sapling) {
        if (getChestLinker() == null) {
            for (ItemStack item : minioninventory.getContents()) {
                if (item != null && item.getType() != null && item.getType().name().contains(sapling)) {
                    item.setAmount(item.getAmount() - 1);
                    return item.getType();
                }
            }
        } else {
            return AsyncRunnable.get_items_from_chest_near(this, sapling);
        }
        return null;
    }

    public Material getFoodInInventory() {
        if (getChestLinker() == null) {
            for (ItemStack item : minioninventory.getContents()) {
                if (item != null && item.getType() != null && item.getType().isEdible()) {
                    item.setAmount(item.getAmount() - 1);
                    return item.getType();
                }
            }
        } else {
            return AsyncRunnable.get_food_from_chest_near(this);
        }
        return null;
    }

}
