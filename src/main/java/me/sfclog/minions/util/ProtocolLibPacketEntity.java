package me.sfclog.minions.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.*;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionSystem.MinionManage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ProtocolLibPacketEntity {





    public static void clear_minion() {
        CompletableFuture.runAsync(() -> {
            for(Minion m : MinionManage.map) {
                if(m != null) {
                    m.save_inventory();
                    clear_minion(m);
                }
            }
        });

    }



    public static void clear_minion(Minion m) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        packet.getIntLists()
                .write(0, m.getIds());
        try {
            for(Player player : Bukkit.getOnlinePlayers()) {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
            }
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }


    public static void clear_minion(int id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        packet.getIntLists()
                .write(0, list);
        try {
            for(Player player : Bukkit.getOnlinePlayers()) {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
            }
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }








    public static void create_entitypacket(Player p , int id ,Location location , EntityType e) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getIntegers().write(0, id);
        packet.getUUIDs().write(0, UUID.randomUUID());
        packet.getEntityTypeModifier().write(0, e);
        packet.getDoubles()
                .write(0, location.getX())
                .write(1, location.getY())
                .write(2, location.getZ());
        try {
           ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }


    public static void setlookat (Minion mi, Location location) {

        for(Integer id : mi.getIds()) {
            Vector dirBetweenLocations = location.toVector().subtract(mi.getLoc().toVector());
            Location loc = mi.getLoc().clone();
            loc.setDirection(dirBetweenLocations);


            PacketContainer container = new PacketContainer(PacketType.Play.Server.ENTITY_LOOK);
            container.getIntegers().write(0, id);
            container.getBytes()
                    .write(0, (byte) loc.getYaw())
                    .write(1, (byte) loc.getPitch());
            container.getBooleans().write(0, true);


            PacketContainer container2 = new PacketContainer(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
            container2.getIntegers().write(0, id);
            container2.getBytes().write(0, (byte) loc.getYaw());


            PacketContainer container3 = new PacketContainer(PacketType.Play.Server.ANIMATION);
            container3.getIntegers()
                    .write(0, id)
                    .write(1, 0);

            try {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ProtocolLibrary.getProtocolManager().sendServerPacket(player, container);
                    ProtocolLibrary.getProtocolManager().sendServerPacket(player, container2);
                    ProtocolLibrary.getProtocolManager().sendServerPacket(player, container3);
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        }





    public static void setMetadata(Player p , int id) {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, id);
        WrappedDataWatcher watcher = new WrappedDataWatcher();

        WrappedDataWatcher.WrappedDataWatcherObject nameVisible = new WrappedDataWatcher.WrappedDataWatcherObject(
                3, WrappedDataWatcher.Registry.get(Boolean.class));
        watcher.setObject(nameVisible, false);



        WrappedDataWatcher.WrappedDataWatcherObject baby = new WrappedDataWatcher.WrappedDataWatcherObject(
                16, WrappedDataWatcher.Registry.get(Boolean.class));
        watcher.setObject(baby, true);


        WrappedDataWatcher.WrappedDataWatcherObject silent = new WrappedDataWatcher.WrappedDataWatcherObject(
                4, WrappedDataWatcher.Registry.get(Boolean.class));
        watcher.setObject(silent, true);


        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());


        try {
              ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void setarmordata(Player p , int id, ItemStack head,ItemStack giap,ItemStack quan,ItemStack giay,ItemStack left , ItemStack right) {

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_EQUIPMENT);
        packet.getIntegers().write(0, id);



        List<Pair<EnumWrappers.ItemSlot,ItemStack>> li = new ArrayList<>();

        li.add(new Pair<>(EnumWrappers.ItemSlot.HEAD, head));
        li.add(new Pair<>(EnumWrappers.ItemSlot.CHEST, giap));
        li.add(new Pair<>(EnumWrappers.ItemSlot.LEGS, quan));
        li.add(new Pair<>(EnumWrappers.ItemSlot.FEET, giay));
        li.add(new Pair<>(EnumWrappers.ItemSlot.OFFHAND, left));
        li.add(new Pair<>(EnumWrappers.ItemSlot.MAINHAND, right));

        packet.getSlotStackPairLists()
                .write(0, li);
        try {
             ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
