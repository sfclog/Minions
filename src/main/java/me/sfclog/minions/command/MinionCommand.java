package me.sfclog.minions.command;

import me.sfclog.minions.MinionManage.MinionType;
import me.sfclog.minions.MinionSystem.MinionItem;
import me.sfclog.minions.MinionSystem.MinionManage;
import me.sfclog.minions.util.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MinionCommand implements TabExecutor, CommandExecutor {


    @Override
    public boolean onCommand(CommandSender arg0, org.bukkit.command.Command arg1, String arg2, String[] arg3) {
        if (arg0 instanceof Player) {
            Player p = (Player) arg0;
            if(p.isOp() | p.hasPermission("*")) {
                if (arg1.getName().equalsIgnoreCase("minion")) {
                    if (arg3.length < 1) {
                        send(arg0, " ");
                        send(arg0, "&bMinions &72.0");
                        send(arg0, "&fAuthor: &aSmallFCraft Studio");
                        send(arg0, " ");
                        send(arg0, "&eCác lệnh chính:");
                        send(arg0, " ");
                        send(arg0, "&2Dùng &f/minion give <tên minion> <level> &elấy minion !");
                        send(arg0, " ");

                     } else if (arg3[0].equalsIgnoreCase("giveminion")) {
                        if(arg3.length == 3) {
                            String minion = arg3[1];
                            if(minion != null) {
//                                hmmm, dude, arg3 là cái sau /minion hả , ye

                                int level =arg3[2] != null && !arg3[2].isEmpty() ? Integer
                                        .parseInt(arg3[2]) : 1;
                                int minionlevel = level <= 0 ? 1 : level > 6 ? 6 : level;

                                if(minion.toUpperCase().equals("MINER")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.MINER, minionlevel ));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.MINER, minionlevel ));
                                    }
                                }else  if(minion.toUpperCase().equals("SLAYER")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.SLAYER, minionlevel ));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.SLAYER, minionlevel ));
                                    }
                                } else if (minion.toUpperCase().equals("FARMER")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.FARMER, minionlevel ));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.FARMER, minionlevel ));
                                  }
                                } else if (minion.toUpperCase().equals("FEED")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.FEED, minionlevel ));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.FEED, minionlevel ));
                                 }
                                } else if (minion.toUpperCase().equals("LUMBERJACK")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.LUMBERJACK, minionlevel ));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.LUMBERJACK, minionlevel ));
                                 }


                                } else {
                                    send(arg0, "&4Tên minion không tồn tại !");
                                }
                            } else {
                                send(arg0, " ");
                                send(arg0, "&4Tên minion không hợp lệ !");
                                send(arg0, " ");
                            }

                        } else {
                            send(arg0, " ");
                            send(arg0, "&2Dùng &f/minion giveminion <tên minion> &elấy minion !");
                            send(arg0, " ");
                        }
                    }

                }
            } else {
                send(arg0, " ");
                send(arg0, "&bMinions &72.0");
                send(arg0, "&fAuthor: &aSmallFCraft Studio");
                send(arg0, "&cBản quyền được đăng ký bởi &4SimpMC");
                send(arg0, " ");
                send(arg0, "&cBạn không có quyền dùng lệnh này.");
                send(arg0, " ");
            }
        } else {
            if (arg1.getName().equalsIgnoreCase("minion")) {
                if (arg3.length < 1) {
                    send(arg0, " ");
                    send(arg0, "&bMinions &72.0");
                    send(arg0, "&fAuthor: &aSmallFCraft Studio");
                    send(arg0, " ");
                    send(arg0, "&eCác lệnh chính:");
                    send(arg0, " ");
                    send(arg0, "&2Dùng &f/minion giveminion <tên người chơi> <tên minion> &eđể đưa minion cho người chơi đó !");
                    send(arg0, " ");


                } else if (arg3[0].equalsIgnoreCase("giveminion")) {
                    if (arg3.length == 3) {
                        Player p = Bukkit.getPlayer(arg3[1]);
                        if (p != null) {
                            String minion = arg3[2];
                            if (minion != null) {
                                if (minion.toUpperCase().equals("MINER")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.MINER, 1));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.MINER, 1));
                                    }
                                } else if (minion.toUpperCase().equals("SLAYER")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.SLAYER, 1));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.SLAYER, 1));
                                    }
                                } else if (minion.toUpperCase().equals("FARMER")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.FARMER, 1));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.FARMER, 1));
                                    }
                                } else if (minion.toUpperCase().equals("FEED")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.FEED, 1));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.FEED, 1));
                                    }
                                } else if (minion.toUpperCase().equals("LUMBERJACK")) {
                                    if(p.getInventory().firstEmpty() == -1) {
                                        p.getWorld().dropItem(p.getLocation(),MinionItem.getMinion(p,MinionType.LUMBERJACK, 1));
                                    } else {
                                        p.getInventory().addItem(MinionItem.getMinion(p,MinionType.LUMBERJACK, 1));
                                 }
                                } else {
                                    send(arg0, "&4Tên minion không tồn tại !");
                                }
                            }
                        } else {
                            send(arg0, "&4Người chơi đó đã offline !");
                        }

                    } else {
                        send(arg0, " ");
                        send(arg0, "&2Dùng &f/minion giveminion <tên người chơi> <tên minion> &eđể đưa minion cho người chơi đó !");
                        send(arg0, " ");
                    }
                }
            }
        }

        return false;

    }

    private void send(CommandSender arg0, String s) {
        if(arg0 != null) {
            arg0.sendMessage(Color.tran(s));
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender arg0, org.bukkit.command.Command arg1, String arg2, String[] arg3) {
        ArrayList<String> s = new ArrayList<String>();
        s.add("giveminion");
        if (arg3[0].equalsIgnoreCase("giveminion")) {
            ArrayList<String> s2 = new ArrayList<String>();
            s2.add("MINER");
            s2.add("SLAYER");
            s2.add("FARMER");
            s2.add("FEED");
            s2.add("LUMBERJACK");
            return s2;
        } else {
            return s;
        }
    }

}
