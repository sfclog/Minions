package me.sfclog.minions.runnable_core;

import me.sfclog.minions.Main;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionManage.MinionStatus;
import me.sfclog.minions.util.GetLocation;
import me.sfclog.minions.util.PlaySound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleEffect;;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FarmerUpdate {

    public static HashMap<Material,Material> is_tree;

    public static HashMap<Material,Material> seed_map;

    static {

        is_tree = new HashMap<>();
        is_tree.put(Material.WHEAT,Material.WHEAT);
        is_tree.put(Material.CARROTS,Material.CARROT);
        is_tree.put(Material.BEETROOTS,Material.BEETROOT);
        is_tree.put(Material.POTATOES,Material.POTATO);
        is_tree.put(Material.NETHER_WART,Material.NETHER_WART);
        is_tree.put(Material.PUMPKIN_STEM,Material.PUMPKIN);
        is_tree.put(Material.MELON_STEM,Material.MELON);
        is_tree.put(Material.SWEET_BERRY_BUSH,Material.SWEET_BERRIES);
        is_tree.put(Material.SUGAR_CANE,Material.SUGAR_CANE);
        is_tree.put(Material.CACTUS,Material.CACTUS);


        seed_map = new HashMap<>();
        seed_map.put(Material.WHEAT_SEEDS,Material.WHEAT);
        seed_map.put(Material.CARROT,Material.CARROTS);
        seed_map.put(Material.BEETROOT_SEEDS,Material.BEETROOTS);
        seed_map.put(Material.POTATO,Material.POTATOES);
        seed_map.put(Material.NETHER_WART,Material.NETHER_WART);
        seed_map.put(Material.PUMPKIN_SEEDS,Material.PUMPKIN_STEM);
        seed_map.put(Material.MELON_SEEDS,Material.MELON_STEM);
        seed_map.put(Material.SWEET_BERRIES,Material.SWEET_BERRY_BUSH);
        seed_map.put(Material.SUGAR_CANE,Material.SUGAR_CANE);
        seed_map.put(Material.CACTUS,Material.CACTUS);
    }


    public static Material get(Material seed) {
        for(Material m : is_tree.keySet()) {
            if(m != null && m == seed) {
                return m;
            }
        }
        return null;
    }

    public static void update(Minion m) {

        //farm land fix

        for(Block b : GetLocation.getLocationFarmer(m.getLoc().clone().add(0,-1,0))) {
            if(b != null && b.getType() != null ) {
                if(b.getType().name().contains("DIRT") || b.getType() == Material.GRASS_BLOCK) {
                    b.setType(Material.FARMLAND);
                } else if(b.getType() == Material.FARMLAND) {
                    Farmland data = (Farmland) b.getBlockData();
                    data.setMoisture(7);
                    b.setBlockData(data);
                }
            }
        }

        //trồng cây
        for(Block trong : GetLocation.getLocationFarmer(m.getLoc())) {
            if(trong != null && trong.getType() != null && trong.getType() == Material.AIR) {
                if(trong.getLocation() != null) {
                    Location loc = trong.getLocation().clone().add(0,-1,0);
                    if(loc.getBlock() != null && loc.getBlock().getType() != null && loc.getBlock().getType() == Material.FARMLAND) {
                        for(Material is : seed_map.keySet()) {
                            if (is != null) {
                                Material n = m.getItemsInInventory(is.name());
                                if (n != null) {
                                    Material seed = seed_map.get(n);
                                    if(seed != null) {
                                        trong.setType(seed);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        for(Block b : GetLocation.getLocationFarmer(m.getLoc())) {
            if(b != null && b.getType() != null && is_tree.containsKey(b.getType())) {
                Ageable ageable = (Ageable) b.getBlockData();
                if(!(ageable.getAge() >= ageable.getMaximumAge())) {
                    int timegroup = ageable.getAge() + 1;
                    ageable.setAge(timegroup);
                } else {
                    for(ItemStack item : b.getDrops()) {
                        if(item!= null) {
                            if (!AsyncRunnable.put_item_to_chest_down(m, item)) {
                                if(m.getChestLinker() == null) {
                                    m.add_inventory(item);
                                }
                            }
                        }
                    }
                    ageable.setAge(0);
                    PlaySound.play_sound(b.getLocation(), Sound.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,5);
                    m.look(b.getLocation());
                    m.downfood();
                }

                b.setBlockData(ageable);
            }

        }


        m.setStatus(MinionStatus.ISWORK);

    }
}
