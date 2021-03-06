package net.toshimichi.elitebot.commands;

import net.dv8tion.jda.core.entities.Role;
import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.EliteStatus;
import net.toshimichi.elitebot.discord.EliteUser;

import java.util.ArrayList;
import java.util.List;

public class StatusListCommand implements EliteCommand, CommandDescription {

    private final EliteBot bot;

    public StatusListCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "sl";
    }

    private int countMembers(EliteStatus status) {
        Role r = bot.getJDA().getRoleById(status.getId());
        return r.getGuild().getMembersWithRoles(r).size();
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        List<EliteStatus> statuses = new ArrayList<>(bot.getEliteStatusProvider().getEliteStatuses());
        if (statuses.size() == 0) {
            user.sendMessage("現在有効なステータスは存在しません");
            return;
        }
        statuses.sort((e1, e2) -> Integer.compare(countMembers(e2), countMembers(e1)));
        StringBuilder builder = new StringBuilder("現在のステータス一覧:\n");
        for (EliteStatus status : statuses) {
            builder.append(countMembers(status));
            builder.append("人: ");
            builder.append(status.getName());
            builder.append('\n');
        }
        user.sendMessage(builder.toString());
    }

    @Override
    public String getDescription() {
        return "ステータスの一覧を表示します";
    }
}
