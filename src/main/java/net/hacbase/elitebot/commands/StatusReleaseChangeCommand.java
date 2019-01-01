package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.EliteStatus;
import net.hacbase.elitebot.discord.EliteUser;

import java.util.Collection;
import java.util.HashSet;

public class StatusReleaseChangeCommand implements EliteCommand, CommandDescription{

    private final EliteBot bot;

    public StatusReleaseChangeCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "strel";
    }

    @Override
    public void execute(EliteUser user, String[] args) {

        if (args.length < 1) {
            user.sendMessage("解除する状態を指定してください");
            return;
        }
        String statusName = link(args, 0, args.length);
        if(statusName.equals("all")) {
            user.setEliteStatuses(new HashSet<>());
            user.sendMessage("状態を全解除しました");
            return;
        }

        EliteStatus status = bot.getEliteStatusProvider().getEliteStatusByName(statusName);
        if (status == null) {
            user.sendMessage("その状態は存在しません: " + statusName);
            return;
        }
        Collection<EliteStatus> changed = user.getEliteStatuses();
        changed.remove(status);
        user.setEliteStatuses(changed);
        user.sendMessage("状態を解除しました");
    }

    @Override
    public String getDescription() {
        return "状態を解除します(allで状態を全解除します";
    }
}
