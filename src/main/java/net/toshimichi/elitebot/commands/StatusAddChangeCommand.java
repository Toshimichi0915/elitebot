package net.toshimichi.elitebot.commands;

import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.EliteStatus;
import net.toshimichi.elitebot.discord.EliteUser;

import java.util.Collection;

public class StatusAddChangeCommand implements EliteCommand, CommandDescription{

    private final EliteBot bot;

    public StatusAddChangeCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "st";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        if (args.length < 1) {
            user.sendMessage("追加する状態を指定してください");
            return;
        }
        String statusName = link(args, 0, args.length);
        EliteStatus status = bot.getEliteStatusProvider().getEliteStatusByName(statusName);
        if (status == null) {
            user.sendMessage("その状態は存在しません: " + statusName);
            return;
        }
        Collection<EliteStatus> changed = user.getEliteStatuses();
        changed.add(status);
        user.setEliteStatuses(changed);
        user.sendMessage("状態を変更しました");
    }

    @Override
    public String getDescription() {
        return "指定した状態に変更します 複数の状態が設定可能です";
    }
}
