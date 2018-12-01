package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.ElitePower;
import net.hacbase.elitebot.discord.ElitePowerProvider;
import net.hacbase.elitebot.discord.EliteUser;

public class PowerRemoveCommand extends AdminCommand {

    private final EliteBot bot;

    public PowerRemoveCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public void conduct(EliteUser admin, String[] args) {
        if (args.length < 1) {
            admin.sendMessage("削除する勢力を指定してください");
            return;
        }
        ElitePowerProvider prov = bot.getElitePowerProvider();
        ElitePower power = prov.getElitePowerByName(args[0]);
        if (power == null) {
            admin.sendMessage("その勢力は存在しません: " + args[0]);
            return;
        }
        bot.getElitePowerProvider().removeElitePower(power);
        admin.sendMessage("勢力を削除しました");
    }

    @Override
    public String getPrefix() {
        return "?ppremove";
    }
}
