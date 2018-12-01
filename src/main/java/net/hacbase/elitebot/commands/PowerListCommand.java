package net.hacbase.elitebot.commands;

import net.dv8tion.jda.core.entities.Role;
import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.ElitePower;
import net.hacbase.elitebot.discord.EliteUser;

import java.util.Collection;

public class PowerListCommand implements EliteCommand, CommandDescription {

    private final EliteBot bot;

    public PowerListCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "pplist";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        StringBuilder builder = new StringBuilder("現在の勢力一覧:\n");
        Collection<ElitePower> powers = bot.getElitePowerProvider().getElitePowers();
        if (powers.size() == 0) {
            user.sendMessage("現在有効な勢力は存在しません");
            return;
        }

        for (ElitePower power : bot.getElitePowerProvider().getElitePowers()) {
            builder.append(power.getName());
            builder.append('(');
            Role r = bot.getJDA().getRoleById(power.getId());
            builder.append(r.getGuild().getMembersWithRoles(r).size());
            builder.append("人)\n");
        }
        user.sendMessage(builder.toString());
    }

    @Override
    public String getDescription() {
        return "現在の勢力一覧を表示します";
    }
}
