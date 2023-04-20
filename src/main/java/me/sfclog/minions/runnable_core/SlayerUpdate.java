package me.sfclog.minions.runnable_core;

import java.util.List;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionManage.MinionStatus;
import me.sfclog.minions.MinionSystem.MinionManage;
import me.sfclog.minions.util.GetMobAttack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class SlayerUpdate {

    public static void update(Minion mi) {
        List<Entity> nearbyEntites = (List<Entity>)mi.getLoc().getWorld().getNearbyEntities(mi.getLoc(), 3,3, 3);
        for (Entity e : nearbyEntites) {
            if (e instanceof LivingEntity && !check_is_minion(e) && GetMobAttack.attack(e)) {
                mi.look(e.getLocation().add(0.0D, 0.5D, 0.0D));
                mi.downfood();
                ((LivingEntity)e).damage((mi.gettier() == 1) ? 20.0D : ((mi.gettier() == 2) ? 30.0D : 50.0D));
                new ParticleBuilder(ParticleEffect.CRIT, mi.getLoc().clone().add(0.0D, 2.0D, 0.0D))
                        .display();
            }
        }
        mi.setStatus(MinionStatus.ISWORK);

    }

    private static boolean check_is_minion(Entity e) {
        if(e != null && e instanceof Zombie) {
            Zombie zom = (Zombie) e;
            if(MinionManage.is_minion(zom)) {
                return true;
            }
        }
        return false;
    }
}
