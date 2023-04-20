package me.sfclog.minions.MinionSystem;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.utility.MinecraftVersion;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedEnumEntityUseAction;
import me.sfclog.minions.Main;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.util.AdminUtil;
import me.sfclog.minions.util.FoodUtil;
import me.sfclog.minions.util.ProtocolLibPacketEntity;
import me.sfclog.minions.util.Send;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.geysermc.floodgate.api.FloodgateApi;
import xyz.xenondevs.particle.ParticleEffect;

public class MinionInteract {

    public static final int MINECRAFT_VERSION = MinecraftVersion.getCurrentVersion().getMinor();

    public static void addInteractListener() {
        ProtocolLibrary.getProtocolManager()
                .addPacketListener(new PacketAdapter(Main.pl, PacketType.Play.Client.USE_ENTITY) {
                    @Override
                    public void onPacketReceiving(PacketEvent event) {
                        PacketContainer container = event.getPacket();
                        int targetId = container.getIntegers().read(0);

                        EnumWrappers.EntityUseAction action;
                        EnumWrappers.Hand usedHand;

                        Minion mi = MinionManage.getMinion(targetId);
                        if (mi != null) {
                            Player p = event.getPlayer();

                            if (MINECRAFT_VERSION >= 17) {
                                WrappedEnumEntityUseAction useAction = container.getEnumEntityUseActions().read(0);
                                // the hand is only available when not attacking
                                action = useAction.getAction();
                                usedHand = action == EnumWrappers.EntityUseAction.ATTACK
                                        ? EnumWrappers.Hand.MAIN_HAND
                                        : useAction.getHand();
                            } else {
                                // the hand is only available when not attacking
                                action = container.getEntityUseActions().read(0);
                                usedHand = action == EnumWrappers.EntityUseAction.ATTACK
                                        ? EnumWrappers.Hand.MAIN_HAND
                                        : container.getHands().optionRead(0).orElse(EnumWrappers.Hand.MAIN_HAND);
                            }
                            Bukkit.getScheduler().runTask(
                                    Main.pl,
                                    () -> {

                                        if(action == EnumWrappers.EntityUseAction.ATTACK && usedHand == EnumWrappers.Hand.MAIN_HAND) {

                                            if (mi.isOwn(p) || AdminUtil.isadmin(p)) {
                                                if (MinionManage.remove(mi)) {
                                                    ProtocolLibPacketEntity.clear_minion(mi);
                                                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100, 1);
                                                    if (p.getInventory().firstEmpty() == -1) {
                                                        p.getWorld().dropItem(p.getLocation(), MinionItem.getMinion(p, mi.getType(), mi.gettier()));
                                                    } else {
                                                        p.getInventory().addItem(MinionItem.getMinion(p, mi.getType(), mi.gettier()));
                                                    }
                                                    Send.send(p, Lang.getlang("Minion.Lang.MinionBreak"));
                                                    p.closeInventory();
                                                }
                                            } else {
                                                Send.send(p, Lang.getlang("Minion.Lang.NotAOwner"));
                                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 1);
                                            }

                                        }
                                        if(action == EnumWrappers.EntityUseAction.INTERACT && usedHand == EnumWrappers.Hand.MAIN_HAND) {

                                            if (p.getInventory() != null) {
                                                if (p.getInventory().getItemInMainHand() != null) {
                                                    ItemStack item = p.getInventory().getItemInMainHand();
                                                    if (item != null && item.getType() != null && item.getType().isEdible()) {
                                                        mi.add_food(FoodUtil.getRegenFood(mi, item.getType()));
                                                        p.playSound(mi.getLoc(), Sound.ENTITY_GENERIC_EAT, 100, 1);
                                                        p.playSound(mi.getLoc(), Sound.ENTITY_VILLAGER_TRADE, 100, 10);
                                                        ParticleEffect.HEART.display(mi.getLoc().clone().add(0.0, 0.8, 0.0));
                                                        item.setAmount(item.getAmount() - 1);
                                                        p.updateInventory();
                                                    } else {
                                                        if (mi.isOwn(p) || AdminUtil.isadmin(p)) {
                                                            if (FloodgateApi.getInstance().isFloodgatePlayer(p.getUniqueId())) {
                                                                MinionMCPEGui.send(p, mi);
                                                            } else {
                                                                Bukkit.getScheduler().runTask(Main.pl, () -> {
                                                                    MinionGui.opengui(p, mi);
                                                                });
                                                            }
                                                        } else {
                                                            Send.send(p, Lang.getlang("Minion.Lang.NotAOwner"));
                                                            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 1);
                                                        }
                                                    }
                                                } else {
                                                    if (mi.isOwn(p) || AdminUtil.isadmin(p)) {
                                                        if (FloodgateApi.getInstance().isFloodgatePlayer(p.getUniqueId())) {
                                                            MinionMCPEGui.send(p, mi);
                                                        } else {
                                                            Bukkit.getScheduler().runTask(Main.pl, () -> {
                                                                MinionGui.opengui(p, mi);
                                                            });
                                                        }
                                                    } else {
                                                        Send.send(p, Lang.getlang("Minion.Lang.NotAOwner"));
                                                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 1);
                                                    }
                                                }
                                            }
                                        }
                                    }
                            );
                        }
                }
                });
    }
}
