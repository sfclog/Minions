package me.sfclog.minions.MinionSystem;


import me.sfclog.minions.MinionManage.MinionType;
import me.sfclog.minions.util.HeadBuild;
import me.sfclog.minions.util.ProtocolLibPacketEntity;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;



public class MinionCreate {



    public static void build_minion(Player p , String own , int id , Location loc, MinionType type , int tier) {
        if(type == MinionType.MINER) {

            ItemStack giap = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta giapmeta = (LeatherArmorMeta) giap.getItemMeta();
            giapmeta.setColor(Color.fromRGB(219,219,219));
            giap.setItemMeta(giapmeta);
            ItemStack quan = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta quanmeta = (LeatherArmorMeta) quan.getItemMeta();
            quanmeta.setColor(Color.fromRGB(158,158,158));
            quan.setItemMeta(quanmeta);
            ItemStack giay = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta giaymeta = (LeatherArmorMeta) giay.getItemMeta();
            giaymeta.setColor(Color.fromRGB(77, 77,77));
            giay.setItemMeta(giaymeta);

            ItemStack item = new ItemStack(MinionItem.getItemMinion(type,tier));
            ProtocolLibPacketEntity.create_entitypacket(p,id,loc, EntityType.ZOMBIE);
            ProtocolLibPacketEntity.setMetadata(p,id);
            ProtocolLibPacketEntity.setarmordata(p,id,HeadBuild.getHead(own),giap,quan,giay,item,item);




        }

        if(type == MinionType.SLAYER) {


            ItemStack giap = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta giapmeta = (LeatherArmorMeta) giap.getItemMeta();
            giapmeta.setColor(Color.fromRGB(255,94,94));
            giap.setItemMeta(giapmeta);
            ItemStack quan = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta quanmeta = (LeatherArmorMeta) quan.getItemMeta();
            quanmeta.setColor(Color.fromRGB(255,127,127));
            quan.setItemMeta(quanmeta);
            ItemStack giay = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta giaymeta = (LeatherArmorMeta) giay.getItemMeta();
            giaymeta.setColor(Color.fromRGB(77, 77,77));
            giay.setItemMeta(giaymeta);
            ItemStack item = new ItemStack(MinionItem.getItemMinion(type,tier));


            ProtocolLibPacketEntity.create_entitypacket(p,id,loc, EntityType.ZOMBIE);
            ProtocolLibPacketEntity.setMetadata(p,id);
            ProtocolLibPacketEntity.setarmordata(p,id,HeadBuild.getHead(own),giap,quan,giay,item,item);



        }



        if(type == MinionType.FARMER) {

            ItemStack giap = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta giapmeta = (LeatherArmorMeta) giap.getItemMeta();
            giapmeta.setColor(Color.fromRGB(181,124,80));
            giap.setItemMeta(giapmeta);
            ItemStack quan = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta quanmeta = (LeatherArmorMeta) quan.getItemMeta();
            quanmeta.setColor(Color.fromRGB(159,133,112));
            quan.setItemMeta(quanmeta);
            ItemStack giay = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta giaymeta = (LeatherArmorMeta) giay.getItemMeta();
            giaymeta.setColor(Color.fromRGB(77, 77,77));
            giay.setItemMeta(giaymeta);
            ItemStack item = new ItemStack(MinionItem.getItemMinion(type,tier));


            ProtocolLibPacketEntity.create_entitypacket(p,id,loc, EntityType.ZOMBIE);
            ProtocolLibPacketEntity.setMetadata(p,id);
            ProtocolLibPacketEntity.setarmordata(p,id,HeadBuild.getHead(own),giap,quan,giay,item,item);



        }


        if(type == MinionType.FEED) {

            ItemStack giap = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta giapmeta = (LeatherArmorMeta) giap.getItemMeta();
            giapmeta.setColor(Color.fromRGB(241,255,87));
            giap.setItemMeta(giapmeta);
            ItemStack quan = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta quanmeta = (LeatherArmorMeta) quan.getItemMeta();
            quanmeta.setColor(Color.fromRGB(245,255,64));
            quan.setItemMeta(quanmeta);
            ItemStack giay = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta giaymeta = (LeatherArmorMeta) giay.getItemMeta();
            giaymeta.setColor(Color.fromRGB(77, 77,77));
            giay.setItemMeta(giaymeta);
            ItemStack item = new ItemStack(MinionItem.getItemMinion(type,tier));

            ProtocolLibPacketEntity.create_entitypacket(p,id,loc, EntityType.ZOMBIE);
            ProtocolLibPacketEntity.setMetadata(p,id);
            ProtocolLibPacketEntity.setarmordata(p,id,HeadBuild.getHead(own),giap,quan,giay,item,item);



        }

        if(type == MinionType.LUMBERJACK) {

            ItemStack giap = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta giapmeta = (LeatherArmorMeta) giap.getItemMeta();
            giapmeta.setColor(Color.fromRGB(118,255,123));
            giap.setItemMeta(giapmeta);
            ItemStack quan = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta quanmeta = (LeatherArmorMeta) quan.getItemMeta();
            quanmeta.setColor(Color.fromRGB(128,255,132));
            quan.setItemMeta(quanmeta);
            ItemStack giay = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta giaymeta = (LeatherArmorMeta) giay.getItemMeta();
            giaymeta.setColor(Color.fromRGB(77, 77,77));
            giay.setItemMeta(giaymeta);

            ItemStack item = new ItemStack(MinionItem.getItemMinion(type,tier));

            ProtocolLibPacketEntity.create_entitypacket(p,id,loc, EntityType.ZOMBIE);
            ProtocolLibPacketEntity.setMetadata(p,id);
            ProtocolLibPacketEntity.setarmordata(p,id,HeadBuild.getHead(own),giap,quan,giay,item,item);



        }

    }





}
