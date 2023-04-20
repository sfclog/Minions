package me.sfclog.minions.lang;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.sfclog.minions.Main;
import me.sfclog.minions.util.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class Lang {

    public static File locate = new File("plugins/Minions/", "config.yml");
    public static FileConfiguration DataFile = (FileConfiguration)YamlConfiguration.loadConfiguration(locate);




    public static void loadlang() {
        addlang("DisableServer",false);
        addlang("Plugin.Prefix","&7[&#0af8ff&lM&#6cfbff&li&#cefeff&ln&#effeff&li&#d0fbff&lo&#b0f8ff&ln&7] ");


        List<String> line = new ArrayList<>();

        line.add("<minion_name>");
        line.add("&fChủ sỡ hữu: &e[owner]");
        line.add("&fThức ăn: &a<food>/<max_food>");
        line.add("<status>");


        addlang("Minion.MinionHoloName.Line",line);

        addlang("Minion.MinionStatus.FullInv","&cĐầy túi đồ...");
        addlang("Minion.MinionStatus.Work","&aĐang làm việc...");
        addlang("Minion.MinionStatus.Hungry","&eĐang bị đói...");
        addlang("Minion.MinionStatus.FullChest","&cĐầy rương đồ...");


        addlang("Minion.MinionName.Feed","&&b&lMinion &d&lĐầu Bếp");
        addlang("Minion.MinionName.LumberJack","&&b&lMinion &a&lTiều Phu");

        addlang("Minion.ItemSummon.MinerSummon.Name","&b&lMinion &f&lThợ Mỏ &f(<owner>)");
        addlang("Minion.ItemSummon.SlayerSummon.Name","&b&lMinion &c&lĐồ Tể &f(<owner>)");
        addlang("Minion.ItemSummon.FarmerSummon.Name","&&b&lMinion &5&lNông Dân &f(<owner>)");
        addlang("Minion.ItemSummon.FeedSummon.Name","&&b&lMinion &d&lĐầu Bếp &f(<owner>)");
        addlang("Minion.ItemSummon.LumberJackSummon.Name","&&b&lMinion &a&lTiều Phu &f(<owner>)");


        addlang("Minion.Level.LevelI","&e&l★&7&l★★★★★");
        addlang("Minion.Level.LevelII","&e&l★★&7&l★★★★");
        addlang("Minion.Level.LevelIII","&e&l★★★&7&l★★★");
        addlang("Minion.Level.LevelIV","&e&l★★★★&7&l★★");
        addlang("Minion.Level.LevelV","&e&l★★★★★&7&l★");
        addlang("Minion.Level.LevelVI","&e&l★★★★★★");


        addlang("Minion.WorkSpeed.Miner.LevelI",180);
        addlang("Minion.WorkSpeed.Miner.LevelII",150);
        addlang("Minion.WorkSpeed.Miner.LevelIII",120);
        addlang("Minion.WorkSpeed.Miner.LevelIV",90);
        addlang("Minion.WorkSpeed.Miner.LevelV",60);
        addlang("Minion.WorkSpeed.Miner.LevelVI",30);


        addlang("Minion.WorkSpeed.Farmer.LevelI",400);
        addlang("Minion.WorkSpeed.Farmer.LevelII",360);
        addlang("Minion.WorkSpeed.Farmer.LevelIII",320);
        addlang("Minion.WorkSpeed.Farmer.LevelIV",280);
        addlang("Minion.WorkSpeed.Farmer.LevelV",240);
        addlang("Minion.WorkSpeed.Farmer.LevelVI",200);


        addlang("Minion.WorkSpeed.LumberJack.LevelI",400);
        addlang("Minion.WorkSpeed.LumberJack.LevelII",360);
        addlang("Minion.WorkSpeed.LumberJack.LevelIII",320);
        addlang("Minion.WorkSpeed.LumberJack.LevelIV",280);
        addlang("Minion.WorkSpeed.LumberJack.LevelV",240);
        addlang("Minion.WorkSpeed.LumberJack.LevelVI",200);


        addlang("Minion.WorkSpeed.Slayer.LevelI",120);
        addlang("Minion.WorkSpeed.Slayer.LevelII",100);
        addlang("Minion.WorkSpeed.Slayer.LevelIII",80);
        addlang("Minion.WorkSpeed.Slayer.LevelIV",60);
        addlang("Minion.WorkSpeed.Slayer.LevelV",40);
        addlang("Minion.WorkSpeed.Slayer.LevelVI",20);



        addlang("Minion.Food.LevelI",200);
        addlang("Minion.Food.LevelII",400);
        addlang("Minion.Food.LevelIII",600);
        addlang("Minion.Food.LevelIV",800);
        addlang("Minion.Food.LevelV",1000);
        addlang("Minion.Food.LevelVI",1200);

        addlang("Minion.Food.RegenStart",100);


        addlang("Minion.MoneyUpdate.LevelII",500000);
        addlang("Minion.MoneyUpdate.LevelIII", 1500000);
        addlang("Minion.MoneyUpdate.LevelIV", 5000000);
        addlang("Minion.MoneyUpdate.LevelV", 5000000);
        addlang("Minion.CoinUpdate.LevelVI", 50);



        ArrayList<String> s = new ArrayList<String>();
        s.add("&fThông tin:");
        s.add(" ");
        s.add(" &7&oMinion Thợ Mỏ dùng để đào");
        s.add(" &7&ocác khối với bán kính cụ thể.");
        s.add(" ");
        s.add(" &fCấp: <minion_level>");
        s.add(" ");
        s.add(" &aClick để đặt");
        s.add(" ");
        addlang("Minion.ItemSummon.MinerSummon.Lore",s);

        ArrayList<String> s2 = new ArrayList<String>();
        s2.add("&fThông tin:");
        s2.add(" ");
        s2.add(" &7&oMinion Đồ Tể dùng để giết");
        s2.add(" &7&onhững loại quái vật, động");
        s2.add(" &7&ovật trong bán kính cụ thể.");
        s2.add(" ");
        s2.add(" &fCấp: <minion_level>");
        s2.add(" ");
        s2.add(" &aClick để đặt");
        s2.add(" ");
        addlang("Minion.ItemSummon.SlayerSummon.Lore",s2);


        ArrayList<String> s5 = new ArrayList<String>();
        s5.add("&fThông tin:");
        s5.add(" ");
        s5.add(" &7&oMinion Nông Dân giúp bạn trồng");
        s5.add(" &7&otrọt và thu hoạch các cây trồng");
        s5.add(" &7&otrong khu vực nhất định.");
        s5.add(" ");
        s5.add(" &fCấp: <minion_level>");
        s5.add(" ");
        s5.add(" &aClick để đặt");
        s5.add(" ");
        addlang("Minion.ItemSummon.FarmerSummon.Lore",s5);

        ArrayList<String> s6 = new ArrayList<String>();
        s6.add("&fThông tin:");
        s6.add(" ");
        s6.add(" &7&oMinion Đầu Bếp giúp bạn cho các");
        s6.add(" &7&oMinion lân cận ăn để tăng năng");
        s6.add(" &7&osuất làm việc của các Minion khác.");
        s6.add(" ");
        s6.add(" &fCấp: <minion_level>");
        s6.add(" ");
        s6.add(" &aClick để đặt");
        s6.add(" ");
        addlang("Minion.ItemSummon.FeedSummon.Lore",s6);


        ArrayList<String> s7 = new ArrayList<String>();
        s7.add("&fThông tin:");
        s7.add(" ");
        s7.add(" &7&oMinion Tiều Phu giúp bạn trồng");
        s7.add(" &7&ovà thu hoạch mọi loại cây gỗ");
        s7.add(" &7&otrong khu vực nhất định.");
        s7.add(" ");
        s7.add(" &fCấp: <minion_level>");
        s7.add(" ");
        s7.add(" &aClick để đặt");
        s7.add(" ");
        addlang("Minion.ItemSummon.LumberJackSummon.Lore",s7);


        addlang("Minion.Lang.MinionPlace","&6Đã đặt thành công một con &eMinion &6.");
        addlang("Minion.Lang.MinionBreak","&6Đã gỡ thành công một con &eMinion &6.");
        addlang("Minion.Lang.MinionDisable","&eMinion &4bị vô hiệu hoá ở máy chủ này.");


        addlang("Minion.Lang.ChestLinkerStatus.IsLinker","&2Đã liên kết &f(X: <x>, Y: <y>, X: <z>)");
        addlang("Minion.Lang.ChestLinkerStatus.NotLinker","&cKhông liên kết");

        addlang("Minion.Lang.MinionUpdate","&2Bạn đã nâng cấp thành công &eMinion&2 này lên cấp &f<tier> &2với số tiền là &f<money>$&2.");
        addlang("Minion.Lang.NotEnoughMoney","&4Bạn không có đủ &f<money>$ &4để nâng cấp &eMinion&4 này lên cấp &f<tier>&2.");

        addlang("Minion.Lang.MinionIsMaxUpdate","&eMinion &5của bạn đã đạt cấp tối đa rồi.");


        addlang("Minion.Lang.HaveMinionAtLocation","&cĐã có một Minion tồn tại ở vị trí này rồi.");

        addlang("Minion.Lang.LinkerChestTimeOut","&eHết thời gian liên kết rương với Minion.");
        addlang("Minion.Lang.LinkerChestStart","&aClick vào một Rương bất kì để liên kết Minion với Rương đó.");
        addlang("Minion.Lang.LinkerChestSuccess","&aLiên kết thành công Minion với Rương này.");
        addlang("Minion.Lang.LinkerChestFail","&cBạn vừa click vào một Khối không phải là Rương.");

        addlang("Minion.Lang.LinkerLocked","&cLiên kết rương sẽ mở khi bạn nâng cấp Minion lên cấp &f6&c.");
        addlang("Minion.Lang.LinkerIsWork","&cKho của Minion bị vô hiệu hoá khi đang dùng liên kết rương.");


        addlang("Minion.Lang.GetAll","&2Đã rút tất cả vật phẩm trong kho đồ của Minion.");

        addlang("Minion.Lang.GetAllDisable","&cChế độ này bị vô hiệu hoá khi sử dụng Liên Kết Rương.");


        addlang("Minion.Lang.NotAOwner","&cBạn không phải là chủ của Minion này.");


        addlang("Minion.Lang.NeedLinkerChest","&cLiên kết rương để dùng <minion_name>&c.");


        addlang("Minion.Lang.Status.Enable","&2Đang Bật");
        addlang("Minion.Lang.Status.Disable","&4Đã Tắt");


        addlang("Minion.Gui.PC.Title","&4&lCài Đặt Minion");


        addlang("Minion.Gui.PC.Item.Quit.Title","&6Đóng");
        addlang("Minion.Gui.PC.Item.Quit.Type","BARRIER");
        ArrayList<String> Quit = new  ArrayList<String>();
        Quit.add(" ");
        Quit.add("&fThông tin:");
        Quit.add(" ");
        Quit.add(" &7&oDùng để đóng cài đặt này.");
        Quit.add(" ");
        Quit.add(" &aClick Để Đóng");
        Quit.add(" ");
        addlang("Minion.Gui.PC.Item.Quit.Lore",Quit);



        addlang("Minion.Gui.PC.Item.Update.Title","&5&lNÂNG CẤP");
        addlang("Minion.Gui.PC.Item.Update.Type","NETHER_STAR");
        ArrayList<String> Update = new  ArrayList<String>();
        Update.add(" ");
        Update.add("&fThông tin:");
        Update.add(" ");
        Update.add(" &7&oDùng để khai thác tối đa");
        Update.add(" &7&otốc độ Minion của bạn.");
        Update.add(" ");
        Update.add(" &fCấp Hiện Tại&f: <minion_level>");
        Update.add(" &fNâng lên cấp&f: <minion_levelup>");
        Update.add(" ");
        Update.add("&fYêu cầu:");
        Update.add(" ");
        Update.add(" &eGiá nâng cấp&f: &4<money>$");
        Update.add(" ");
        Update.add(" &aClick để nâng cấp");
        Update.add(" ");
        addlang("Minion.Gui.PC.Item.Update.Lore",Update);



        addlang("Minion.Gui.PC.Item.Update2.Title","&5&lNÂNG CẤP");
        addlang("Minion.Gui.PC.Item.Update2.Type","NETHER_STAR");
        ArrayList<String> Update2 = new  ArrayList<String>();
        Update2.add(" ");
        Update2.add("&fThông tin:");
        Update2.add(" ");
        Update2.add(" &7&oDùng để khai thác tối đa");
        Update2.add(" &7&otốc độ Minion của bạn.");
        Update2.add(" ");
        Update2.add(" &fCấp Hiện Tại&f: <minion_level>");
        Update2.add(" ");
        Update2.add(" ");
        Update2.add(" &cĐã đạt cấp tối đa");
        Update2.add(" ");
        addlang("Minion.Gui.PC.Item.Update2.Lore",Update2);



        addlang("Minion.Gui.PC.Item.Info.Title","&A&LTHÔNG TIN");
        ArrayList<String> Info = new  ArrayList<String>();
        Info.add(" ");
        Info.add("&fThông tin:");
        Info.add(" ");
        Info.add(" &7&oChứa thông tin cơ bản của Minion này.");
        Info.add(" ");
        Info.add(" &fTên: <minion_name>");
        Info.add(" &fChủ sỡ hữu: &e<owner>");
        Info.add(" &fCấp&f: <minion_level>");
        Info.add(" &fTốc độ&f: <minion_speed>");
        Info.add(" &fThức ăn&f: &b<food>/<food_max>");
        Info.add(" ");
        addlang("Minion.Gui.PC.Item.Info.Lore",Info);

        addlang("Minion.Gui.PC.Item.ChestLinker.Title","&6&lLIÊN KẾT RƯƠNG");
        addlang("Minion.Gui.PC.Item.ChestLinker.Type","CHEST");
        ArrayList<String> ChestLinker = new  ArrayList<String>();
        ChestLinker.add(" ");
        ChestLinker.add("&fThông tin:");
        ChestLinker.add(" ");
        ChestLinker.add(" &7&oDùng để liên kết một rương bất");
        ChestLinker.add(" &7&okì với Minion này cho phép truyền.");
        ChestLinker.add(" &7&ocác vật phẩm mà Minion thu thập được.");
        ChestLinker.add(" &7&ovào rương đó.");
        ChestLinker.add(" ");
        ChestLinker.add("&fTrạng thái:");
        ChestLinker.add(" ");
        ChestLinker.add(" <chest_linker_status>");
        ChestLinker.add(" ");
        ChestLinker.add(" &aClick để liên kết");
        ChestLinker.add(" ");
        addlang("Minion.Gui.PC.Item.ChestLinker.Lore",ChestLinker);


        addlang("Minion.Gui.PC.Item.Remove.Title","&c&lTHU HỒI");
        addlang("Minion.Gui.PC.Item.Remove.Type","REDSTONE");
        ArrayList<String> Remove = new  ArrayList<String>();
        Remove.add(" ");
        Remove.add("&fThông tin:");
        Remove.add(" ");
        Remove.add(" &7&oDùng để thu hồi lại Minion này");
        Remove.add(" &7&ovà thông số cài đặt sẽ bị mất.");
        Remove.add(" ");
        Remove.add(" &aClick để thu hồi");
        Remove.add(" ");
        addlang("Minion.Gui.PC.Item.Remove.Lore",Remove);


        addlang("Minion.Gui.PC.Item.SlotLock.Title","&c&lKHOÁ");
        addlang("Minion.Gui.PC.Item.SlotLock.Type","WHITE_STAINED_GLASS_PANE");
        ArrayList<String> SlotLock = new  ArrayList<String>();
        SlotLock.add(" ");
        SlotLock.add("&fThông tin:");
        SlotLock.add(" ");
        SlotLock.add(" &7&oÔ đồ này đã bị khoá và");
        SlotLock.add(" &7&obạn cần phải nâng cấp");
        SlotLock.add(" &7&oMinion để mở khoá.");
        SlotLock.add(" ");
        SlotLock.add(" &eNâng cấp để mở khoá");
        SlotLock.add(" ");
        addlang("Minion.Gui.PC.Item.SlotLock.Lore",SlotLock);


        addlang("Minion.Gui.PC.Item.SlotLock2.Title","&c&lVÔ HIỆU HOÁ");
        addlang("Minion.Gui.PC.Item.SlotLock2.Type","RED_STAINED_GLASS_PANE");
        ArrayList<String> SlotLock2 = new  ArrayList<String>();
        SlotLock2.add(" ");
        SlotLock2.add("&fThông tin:");
        SlotLock2.add(" ");
        SlotLock2.add(" &7&oÔ đồ này đã bị vô hiệu hoá");
        SlotLock2.add(" &7&odo bạn đang sử dụng chế độ");
        SlotLock2.add(" &7&oliên kết rương.");
        SlotLock2.add(" ");
        SlotLock2.add(" &eLiên kết rương đang bật");
        SlotLock2.add(" ");
        addlang("Minion.Gui.PC.Item.SlotLock2.Lore",SlotLock2);



        addlang("Minion.Gui.PC.Item.ChestLock.Title","&6&lLIÊN KẾT RƯƠNG");
        addlang("Minion.Gui.PC.Item.ChestLock.Type","CHEST");
        ArrayList<String> ChestLock = new  ArrayList<String>();
        ChestLock.add(" ");
        ChestLock.add("&fThông tin:");
        ChestLock.add(" ");
        ChestLock.add(" &7&oDùng để liên kết một rương bất");
        ChestLock.add(" &7&okì với Minion này cho phép truyền.");
        ChestLock.add(" &7&ocác vật phẩm mà Minion thu thập được.");
        ChestLock.add(" &7&ovào rương đó.");
        ChestLock.add(" ");
        ChestLock.add(" &cYêu cầu cấp &f6 &cđể mở");
        ChestLock.add(" ");
        addlang("Minion.Gui.PC.Item.ChestLock.Lore",ChestLock);


        addlang("Minion.Gui.PC.Item.GetAll.Title","&6&lRÚT TẤT CẢ");
        addlang("Minion.Gui.PC.Item.GetAll.Type","CHEST_MINECART");
        ArrayList<String> GetAll = new  ArrayList<String>();
        GetAll.add(" ");
        GetAll.add("&fThông tin:");
        GetAll.add(" ");
        GetAll.add(" &7&oDùng để lấy tất cả vật phẩm");
        GetAll.add(" &7&otrong kho đồ của Minion.");
        GetAll.add(" ");
        GetAll.add(" &cKhông hoạt động với Liên Kết Rương!");
        GetAll.add(" ");
        addlang("Minion.Gui.PC.Item.GetAll.Lore",GetAll);


        addlang("Minion.Gui.PE.Title","&4&lCài Đặt Minion");

        ArrayList<String> Info1 = new  ArrayList<String>();
        Info1.add("&7&oChứa các thông tin cơ bản về cấp độ, thức ăn...");
        Info1.add(" ");
        Info1.add("&aThông tin chính:");
        Info1.add(" ");
        Info1.add(" &fTên: <minion_name>");
        Info1.add(" &fChủ sỡ hữu: &e<owner>");
        Info1.add(" &fCấp&f: <minion_level>");
        Info1.add(" &fTốc độ&f: <minion_speed>");
        Info1.add(" &fThức ăn&f: &b<food>/<food_max>");
        Info1.add(" ");
        Info1.add("&eLiên kết rương:");
        Info1.add(" ");
        Info1.add(" <chest_linker_status>");
        Info1.add(" ");
        addlang("Minion.Gui.PE.MinionInfo",Info1);



        addlang("Minion.Gui.PE.ButtonUpdate.Title","&5Nâng Cấp Minion &7(<money>$)");
        addlang("Minion.Gui.PE.ButtonUpdate.Image","https://cdn-icons-png.flaticon.com/128/891/891978.png");

        addlang("Minion.Gui.PE.ButtonUpdateMax.Title","&4Minion này đã ở cấp tối đa !");
        addlang("Minion.Gui.PE.ButtonUpdateMax.Image","https://cdn-icons-png.flaticon.com/128/891/891948.png");



        addlang("Minion.Gui.PE.LinkChest.Title","&aLiên Kết Rương");
        addlang("Minion.Gui.PE.LinkChest.Image","https://cdn-icons-png.flaticon.com/128/1355/1355876.png");


        addlang("Minion.Gui.PE.ButtonRemove.Title","&bThu Hồi Minion");
        addlang("Minion.Gui.PE.ButtonRemove.Image","https://cdn-icons-png.flaticon.com/128/401/401036.png");

        addlang("Minion.Gui.PE.SettingGui.Title","&4&lTuỳ Chỉnh Minion");
        addlang("Minion.Gui.PE.SettingGui.Info","&fDùng để tuỳ chỉnh bán kính rương, bán kính hoạt động...");

        addlang("Minion.Gui.PE.Invetory.Title","&5Kho Đồ Minion");
        addlang("Minion.Gui.PE.Invetory.Image","https://cdn-icons-png.flaticon.com/128/3608/3608996.png");


        addlang("Minion.Gui.PE.SettingGui.RangeBar.Title","&eBán Kính Hoạt Động");
        addlang("Minion.Gui.PE.SettingGui.RangeChestBar.Title","&eBán Kính Rương");

        addlang("Minion.Gui.PE.MinionInventory.Title","&c&lKho Đồ Minion");
        try {
            DataFile.save(locate);
            Main.sendlog("§e[Lang] §2Load config !");
        } catch (IOException e) {
            Main.sendlog("§e[Lang] §4Fail to load config !");
            e.printStackTrace();
        }

    }
    public static void save() {
        try {
            DataFile.save(locate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getarray(String s) {
        if(DataFile.contains(s)) {
            List<String> ss = new ArrayList<String>();
            for(String ok : DataFile.getStringList(s)) {
                ss.add(Color.tran(ok));
            }
            return ss;
        }
        return null;
    }
    public static int getint(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getInt(s);
        }
        return 0;
    }
    public static double getdoubl(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getDouble(s);
        }
        return 0;
    }
    public static boolean getb(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getBoolean(s);
        }
        return false;
    }
    public static String getlang(String s) {
        if(DataFile.contains(s)) {
            return Color.tran(DataFile.getString(s));
        }
        return s;
    }

    public static String getlang_nocolor(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getString(s);
        }
        return null;
    }

    public static void addlang(String s , double s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , Boolean s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , List<String> s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void setforcelang(String s , String s2) {
        DataFile.set(s, s2);
    }
    public static void setforcelang(String s, double x) {
        DataFile.set(s, x);
    }
    public static void addlang(String s , String s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , int s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }


}
