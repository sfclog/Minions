package me.sfclog.minions.MinionManage;

import me.sfclog.minions.lang.Lang;

public enum MinionStatus {

    FULLINVENTORY,FULLCHEST,ISHUNGRY,ISWORK;

    public static String getStatua(MinionStatus status) {
        if(status == MinionStatus.FULLINVENTORY) {
            return Lang.getlang("Minion.MinionStatus.FullInv");
        }
        if(status == MinionStatus.ISHUNGRY) {
            return Lang.getlang("Minion.MinionStatus.Hungry");
        }
        if(status == MinionStatus.FULLCHEST) {
            return Lang.getlang("Minion.MinionStatus.FullChest");
        }
        return Lang.getlang("Minion.MinionStatus.Work");
    }
}
