package me.sfclog.minions.MinionSystem;

import me.sfclog.minions.Main;
import me.sfclog.minions.MinionManage.Minion;
import me.sfclog.minions.MinionManage.MinionType;
import me.sfclog.minions.lang.Lang;
import me.sfclog.minions.util.Send;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MinionLevel {

    public static String getLevel(int i) {
        if(i == 1) {
            return Lang.getlang("Minion.Level.LevelI");
        }
        if(i == 2) {
            return Lang.getlang("Minion.Level.LevelII");
        }
        if(i == 3) {
            return Lang.getlang("Minion.Level.LevelIII");
        }
        if(i == 4) {
            return Lang.getlang("Minion.Level.LevelIV");
        }
        if(i == 5) {
            return Lang.getlang("Minion.Level.LevelV");
        }
        if(i == 6) {
            return Lang.getlang("Minion.Level.LevelVI");
        }
        return "";
    }





    public static String getName(MinionType type) {
        if(type == MinionType.MINER) {
            return Lang.getlang("Minion.ItemSummon.MinerSummon.Name");
        }
        if(type == MinionType.SLAYER) {
            return Lang.getlang("Minion.ItemSummon.SlayerSummon.Name");
        }
        if(type == MinionType.FARMER) {
            return Lang.getlang("Minion.ItemSummon.FarmerSummon.Name");
        }
        if(type == MinionType.FEED) {
            return Lang.getlang("Minion.ItemSummon.FeedSummon.Name");
        }
        if(type == MinionType.LUMBERJACK) {
            return Lang.getlang("Minion.ItemSummon.LumberJackSummon.Name");
        }
        return "...";
    }



    public static int getWorkSpeed(MinionType type , int tier) {

        if(type == MinionType.MINER) {
            if (tier == 1) {
                return Lang.getint("Minion.WorkSpeed.Miner.LevelI");
            }
            if (tier == 2) {
                return Lang.getint("Minion.WorkSpeed.Miner.LevelII");
            }
            if (tier == 3) {
                return Lang.getint("Minion.WorkSpeed.Miner.LevelIII");
            }
            if (tier == 4) {
                return Lang.getint("Minion.WorkSpeed.Miner.LevelIV");
            }
            if (tier == 5) {
                return Lang.getint("Minion.WorkSpeed.Miner.LevelV");
            }
            if (tier == 6) {
                return Lang.getint("Minion.WorkSpeed.Miner.LevelVI");
            }
        }

        if(type == MinionType.FARMER) {
            if (tier == 1) {
                return Lang.getint("Minion.WorkSpeed.Farmer.LevelI");
            }
            if (tier == 2) {
                return Lang.getint("Minion.WorkSpeed.Farmer.LevelII");
            }
            if (tier == 3) {
                return Lang.getint("Minion.WorkSpeed.Farmer.LevelIII");
            }
            if (tier == 4) {
                return Lang.getint("Minion.WorkSpeed.Farmer.LevelIV");
            }
            if (tier == 5) {
                return Lang.getint("Minion.WorkSpeed.Farmer.LevelV");
            }
            if (tier == 6) {
                return Lang.getint("Minion.WorkSpeed.Farmer.LevelVI");
            }
        }

        if(type == MinionType.LUMBERJACK) {
            if (tier == 1) {
                return Lang.getint("Minion.WorkSpeed.LumberJack.LevelI");
            }
            if (tier == 2) {
                return Lang.getint("Minion.WorkSpeed.LumberJack.LevelII");
            }
            if (tier == 3) {
                return Lang.getint("Minion.WorkSpeed.LumberJack.LevelIII");
            }
            if (tier == 4) {
                return Lang.getint("Minion.WorkSpeed.LumberJack.LevelIV");
            }
            if (tier == 5) {
                return Lang.getint("Minion.WorkSpeed.LumberJack.LevelV");
            }
            if (tier == 6) {
                return Lang.getint("Minion.WorkSpeed.LumberJack.LevelVI");
            }
        }

        if(type == MinionType.SLAYER) {
            if (tier == 1) {
                return Lang.getint("Minion.WorkSpeed.Slayer.LevelI");
            }
            if (tier == 2) {
                return Lang.getint("Minion.WorkSpeed.Slayer.LevelII");
            }
            if (tier == 3) {
                return Lang.getint("Minion.WorkSpeed.Slayer.LevelIII");
            }
            if (tier == 4) {
                return Lang.getint("Minion.WorkSpeed.Slayer.LevelIV");
            }
            if (tier == 5) {
                return Lang.getint("Minion.WorkSpeed.Slayer.LevelV");
            }
            if (tier == 6) {
                return Lang.getint("Minion.WorkSpeed.Slayer.LevelVI");
            }
        }

        if(type == MinionType.SLAYER) {


        }

        return 1;

    }
    public static int getMoneyUpdate(int tier) {
        if(tier == 2) {
            return Lang.getint("Minion.MoneyUpdate.LevelII");
        }
        if(tier == 3) {
            return Lang.getint("Minion.MoneyUpdate.LevelIII");
        }
        if(tier == 4) {
            return Lang.getint("Minion.MoneyUpdate.LevelIV");
        }
        if(tier == 5) {
            return Lang.getint("Minion.MoneyUpdate.LevelV");
        }
        if(tier == 6) {
            return Lang.getint("Minion.CoinUpdate.LevelVI");
        }
        return 99999999;
    }

    public static int getFoodUpdate(int tier) {
        if(tier == 1) {
            return Lang.getint("Minion.Food.LevelI");
        }
        if(tier == 2) {
            return Lang.getint("Minion.Food.LevelII");
        }
        if(tier == 3) {
            return Lang.getint("Minion.Food.LevelIII");
        }
        if(tier == 4) {
            return Lang.getint("Minion.Food.LevelIV");
        }
        if(tier == 5) {
            return Lang.getint("Minion.Food.LevelV");
        }
        if(tier == 6) {
            return Lang.getint("Minion.Food.LevelVI");
        }
        return 100;
    }

    public static void up_level(Player p, Minion m) {
        if(m != null) {
            int tierup = m.gettier() + 1;
            if(!(tierup >= 7)) {
                double player_money = Main.econ.getBalance(p.getName());

                int need_money = getMoneyUpdate(tierup);
                if(player_money >= need_money) {
                Main.econ.withdrawPlayer(p,need_money);
                Send.send(p,Lang.getlang("Minion.Lang.MinionUpdate").replace("<money>",String.valueOf(need_money)).replace("<tier>",getLevel(tierup)));
                //create and remove
                m.up_level();
                p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE , 100 ,1);
                p.closeInventory();
                } else {
                Send.send(p,Lang.getlang("Minion.Lang.NotEnoughMoney").replace("<money>",String.valueOf(need_money)).replace("<tier>",getLevel(tierup)));
                }
            } else {
                Send.send(p,Lang.getlang("Minion.Lang.MinionIsMaxUpdate"));
            }

        }
    }
}
