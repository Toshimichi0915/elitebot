package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.ElitePower;
import net.hacbase.elitebot.discord.EliteUser;

public class PowerChangeCommand implements EliteCommand {

    private final EliteBot bot;

    public PowerChangeCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "?pp";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        if (args.length < 1) {
            user.sendMessage("変更する勢力を指定してください");
            return;
        }
        ElitePower power = bot.getElitePowerProvider().getElitePowerByName(args[0]);
        if (power == null) {
            user.sendMessage("その勢力は存在しません: " + args[0]);
            return;
        }
        user.setElitePower(power);
        user.sendMessage("勢力を変更しました");
    }
}
