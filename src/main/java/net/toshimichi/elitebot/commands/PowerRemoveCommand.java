package net.toshimichi.elitebot.commands;

import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.ElitePower;
import net.toshimichi.elitebot.discord.ElitePowerProvider;
import net.toshimichi.elitebot.discord.EliteUser;

public class PowerRemoveCommand extends AdminCommand implements CommandDescription {

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
        String powerName = link(args, 0, args.length);
        ElitePower power = prov.getElitePowerByName(powerName);
        if (power == null) {
            admin.sendMessage("その勢力は存在しません: " + powerName);
            return;
        }
        bot.getElitePowerProvider().removeElitePower(power);
        admin.sendMessage("勢力を削除しました");
    }

    @Override
    public String getPrefix() {
        return "ppremove";
    }

    @Override
    public String getDescription() {
        return "勢力を削除します";
    }
}
