package net.hacbase.elitebot.commands;

import net.dv8tion.jda.core.entities.Role;
import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.ElitePower;
import net.hacbase.elitebot.discord.EliteUser;

import java.util.ArrayList;
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
        Collection<ElitePower> powers = bot.getElitePowerProvider().getElitePowers();
        if (powers.size() == 0) {
            user.sendMessage("現在有効な勢力は存在しません");
            return;
        }

        ArrayList<PowerCount> power_list = new ArrayList<>();
        for (ElitePower power : bot.getElitePowerProvider().getElitePowers()) {
            String name = power.getName();
            Role r = bot.getJDA().getRoleById(power.getId());
            int count = r.getGuild().getMembersWithRoles(r).size();
            power_list.add(new PowerCount(name, count));
        }

        StringBuilder builder = new StringBuilder("現在の勢力一覧:\n");
        for (PowerCount count : power_list) {
            count.format(builder);
        }
        user.sendMessage(builder.toString());
    }

    @Override
    public String getDescription() {
        return "現在の勢力一覧を表示します";
    }
}

class PowerCount implements Comparable<PowerCount> {
    private String name;
    private int count;

    PowerCount(String name, int count) {
        this.name = name;
        this.count = count;
    }

    void format(StringBuilder builder) {
        builder.append(this.count);
        builder.append(" :  ");
        builder.append(this.name);
    }

    String name() {
        return this.name;
    }

    int count() {
        return this.count;
    }

    public int compareTo(PowerCount o) {
        int d = this.count - o.count();
        if (d != 0) {
            return d;
        }

        return this.name.compareTo(o.name());
    }
}