package net.toshimichi.elitebot.commands;

import net.dv8tion.jda.core.entities.Role;
import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.ElitePower;
import net.toshimichi.elitebot.discord.EliteUser;

import java.util.ArrayList;
import java.util.List;

public class PowerListCommand implements EliteCommand, CommandDescription {

    private final EliteBot bot;

    public PowerListCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "pplist";
    }

    private int countMembers(ElitePower power) {
        Role r = bot.getJDA().getRoleById(power.getId());
        return r.getGuild().getMembersWithRoles(r).size();
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        List<ElitePower> powers = new ArrayList<>(bot.getElitePowerProvider().getElitePowers());
        if (powers.size() == 0) {
            user.sendMessage("現在有効な勢力は存在しません");
            return;
        }
        powers.sort((e1, e2) -> Integer.compare(countMembers(e2), countMembers(e1)));
        StringBuilder builder = new StringBuilder("現在の勢力一覧:\n");
        for (ElitePower power : powers) {
            builder.append(countMembers(power));
            builder.append("人: ");
            builder.append(power.getName());
            builder.append('\n');
        }
        user.sendMessage(builder.toString());
    }

    @Override
    public String getDescription() {
        return "現在の勢力一覧を表示します";
    }
}
