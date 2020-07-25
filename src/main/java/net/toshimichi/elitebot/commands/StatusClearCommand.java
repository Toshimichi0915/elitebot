package net.toshimichi.elitebot.commands;

import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.EliteUser;

import java.util.HashSet;

public class StatusClearCommand implements EliteCommand, CommandDescription {

    private final EliteBot bot;

    public StatusClearCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "sc";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        user.setEliteStatuses(new HashSet<>());
        user.sendMessage("ステータスを全解除しました");
    }

    @Override
    public String getDescription() {
        return "ステータスを解除します(allでステータスを全解除します)";
    }
}
