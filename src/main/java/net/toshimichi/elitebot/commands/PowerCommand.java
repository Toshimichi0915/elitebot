package net.toshimichi.elitebot.commands;

import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.ElitePower;
import net.toshimichi.elitebot.discord.EliteUser;

public class PowerCommand implements EliteCommand, CommandDescription {

    private final EliteBot bot;

    public PowerCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "pp";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        if (args.length < 1) {
            if (user.getElitePower() != null) {
                user.setElitePower(null);
                user.sendMessage("勢力から脱退しました");
            } else {
                user.sendMessage("変更する勢力を指定してください");
            }
            return;
        }
        String powerName = link(args, 0, args.length);
        ElitePower power = bot.getElitePowerProvider().getElitePowerByName(powerName);
        if (power == null) {
            user.sendMessage("その勢力は存在しません: " + powerName);
            return;
        }
        user.setElitePower(power);
        user.sendMessage("勢力を変更しました");
    }

    @Override
    public String getDescription() {
        return "勢力を変更, もしくは勢力から脱退します";
    }
}
