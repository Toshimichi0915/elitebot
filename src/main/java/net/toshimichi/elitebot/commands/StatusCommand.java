package net.toshimichi.elitebot.commands;

import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.EliteStatus;
import net.toshimichi.elitebot.discord.EliteUser;

import java.util.Collection;

public class StatusCommand implements EliteCommand, CommandDescription{

    private final EliteBot bot;

    public StatusCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "st";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        if (args.length < 1) {
            user.sendMessage("追加するステータスを指定してください");
            return;
        }
        String statusName = link(args, 0, args.length);
        EliteStatus status = bot.getEliteStatusProvider().getEliteStatusByName(statusName);
        if (status == null) {
            user.sendMessage("そのステータスは存在しません: " + statusName);
            return;
        }
        Collection<EliteStatus> changed = user.getEliteStatuses();
        changed.add(status);
        user.setEliteStatuses(changed);
        user.sendMessage("ステータスを変更しました");
    }

    @Override
    public String getDescription() {
        return "ステータスを割り当てます";
    }
}
