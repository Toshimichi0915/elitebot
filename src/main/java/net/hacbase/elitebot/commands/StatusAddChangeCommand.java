package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.EliteStatus;
import net.hacbase.elitebot.discord.EliteUser;

import java.util.Collection;

public class StatusAddChangeCommand implements EliteCommand {

    private final EliteBot bot;

    public StatusAddChangeCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "?st";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        if (args.length < 1) {
            user.sendMessage("追加する状態を指定してください");
            return;
        }
        EliteStatus status = bot.getEliteStatusProvider().getEliteStatusByName(args[0]);
        if (status == null) {
            user.sendMessage("その状態は存在しません: " + args[0]);
            return;
        }
        Collection<EliteStatus> changed = user.getEliteStatuses();
        changed.add(status);
        user.setEliteStatuses(changed);
        user.sendMessage("状態を追加変更しました");
    }
}
