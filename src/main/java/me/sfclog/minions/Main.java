package me.sfclog.minions;


import com.github.unldenis.hologram.HologramPool;
import dev.rosewood.rosestacker.api.RoseStackerAPI;
import me.sfclog.minions.MinionSystem.*;
import me.sfclog.minions.command.MinionCommand;
import me.sfclog.minions.data.MinionData;
import me.sfclog.minions.event.PlayerEvent;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.runnable_core.AsyncRange;
import me.sfclog.minions.util.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin pl;
    public static Economy econ = null;


    public static RoseStackerAPI rsAPI;


    public static boolean rosestack;

    public static HologramPool hologramPool;


    @Override
    public void onEnable() {
        pl = this;


        if (Bukkit.getPluginManager().getPlugin("RoseStacker") != null) {
            sendlog("§eHook Rose Stack !");
            rsAPI = RoseStackerAPI.getInstance();
            rosestack = true;
        } else {
            rosestack = false;
        }

        Lang.loadlang();
        setupEconomy();

        LoadSupportBlock.load();
        MinionItem.loadMinion();
        MinionManage.load_minion_in_config();

        this.getCommand("minion").setExecutor(new MinionCommand());
        this.getCommand("minion").setTabCompleter(new MinionCommand());

        getServer().getPluginManager().registerEvents(new PlayerEvent(), this);
        getServer().getPluginManager().registerEvents(new MinionGui(), this);
        getServer().getPluginManager().registerEvents(new MinionChestHook(), this);
        getServer().getPluginManager().registerEvents(new MinionMCPEGui(), this);

        if(!Lang.getb("DisableServer")) {
            AsyncRange.update_range();
            MinionInteract.addInteractListener();
        }
        hologramPool = new HologramPool(this, 20, 0.5f, 5f);

    }




    @Override
    public void onDisable() {
        ProtocolLibPacketEntity.clear_minion();
        hologramPool.remove_notask();
    }

    public static void sendlog(String s) {
       Bukkit.getConsoleSender().sendMessage(s);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
