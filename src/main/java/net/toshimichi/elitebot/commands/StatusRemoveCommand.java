package net.toshimichi.elitebot.commands;

import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.EliteStatus;
import net.toshimichi.elitebot.discord.EliteStatusProvider;
import net.toshimichi.elitebot.discord.EliteUser;

public class StatusRemoveCommand extends AdminCommand implements CommandDescription {

    private final EliteBot bot;

    public StatusRemoveCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public void conduct(EliteUser admin, String[] args) {
        if (args.length < 1) {
            admin.sendMessage("削除する状態を指定してください");
            return;
        }
        String statusName = link(args, 0, args.length);
        EliteStatusProvider prov = bot.getEliteStatusProvider();
        EliteStatus status = prov.getEliteStatusByName(statusName);
        if (status == null) {
            admin.sendMessage("その状態は存在しません: " + statusName);
            return;
        }
        prov.removeEliteStatus(status);
        admin.sendMessage("状態を削除しました");
    }

    @Override
    public String getPrefix() {
        return "stremove";
    }

    @Override
    public String getDescription() {
        return "状態を解除します";
    }
}
