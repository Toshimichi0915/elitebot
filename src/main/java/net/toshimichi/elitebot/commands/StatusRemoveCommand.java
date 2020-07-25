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
            admin.sendMessage("削除するステータスを指定してください");
            return;
        }
        String statusName = link(args, 0, args.length);
        EliteStatusProvider prov = bot.getEliteStatusProvider();
        EliteStatus status = prov.getEliteStatusByName(statusName);
        if (status == null) {
            admin.sendMessage("そのステータスは存在しません: " + statusName);
            return;
        }
        prov.removeEliteStatus(status);
        admin.sendMessage("ステータスを削除しました");
    }

    @Override
    public String getPrefix() {
        return "sr";
    }

    @Override
    public String getDescription() {
        return "ステータスを削除します";
    }
}
