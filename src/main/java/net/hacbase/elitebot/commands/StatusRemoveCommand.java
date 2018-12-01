package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.EliteStatus;
import net.hacbase.elitebot.discord.EliteStatusProvider;
import net.hacbase.elitebot.discord.EliteUser;

public class StatusRemoveCommand extends AdminCommand {

    private final EliteBot bot;

    public StatusRemoveCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public void conduct(EliteUser admin, String[] args) {
        if (args.length < 1) {
            admin.sendMessage("削除する「状態」を指定してください");
            return;
        }
        EliteStatusProvider prov = bot.getEliteStatusProvider();
        EliteStatus status = prov.getEliteStatusByName(args[0]);
        if (status == null) {
            admin.sendMessage("その「状態」は存在しません: " + args[0]);
            return;
        }
        prov.removeEliteStatus(status);
        admin.sendMessage("「状態」を削除しました");
    }

    @Override
    public String getPrefix() {
        return "?stremove";
    }
}
