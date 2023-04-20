package me.sfclog.minions.runnable_core;

import me.sfclog.minions.Main;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionManage.MinionStatus;
import me.sfclog.minions.MinionSystem.MinionLevel;
import me.sfclog.minions.MinionSystem.MinionManage;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.util.AILook;
import me.sfclog.minions.util.FoodUtil;
import me.sfclog.minions.util.ProtocolLibPacketEntity;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleEffect;

public class FeedUpdate {


    public static void update(Minion mi) {
            for (Minion minion : MinionManage.getMinionNearLocation(mi.getLoc())) {
                if (minion != null) {
                    if(minion.isLowFood(Lang.getint("Minion.Food.RegenStart"))) {

                        Material food = mi.getChestLinker() != null ? AsyncRunnable.get_food_from_chest_near(mi) : mi.getFoodInInventory();
                        if (food != null) {
                            minion.add_food(FoodUtil.getRegenFood(mi, food));
                            mi.look(minion.getLoc());
                            ParticleEffect.HEART.display(minion.getLoc().clone().add(0.0, 0.8, 0.0));
                        }
                    }
                }
            }
        mi.setStatus(MinionStatus.ISWORK);
    }
}
