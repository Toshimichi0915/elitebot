package net.hacbase.elitebot.commands;

import net.dv8tion.jda.core.entities.Role;
import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.EliteUser;
import net.hacbase.elitebot.save.DefaultEliteSimpleData;

import java.util.List;

public class PowerAddCommand extends AdminCommand implements CommandDescription{

    private final EliteBot bot;

    public PowerAddCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public void conduct(EliteUser admin, String[] args) {
        if (args.length < 1) {
            admin.sendMessage("追加する勢力を指定してください");
            return;
        }
        String powerName = link(args, 0, args.length);
        List<Role> r = bot.getJDA().getRolesByName(powerName, true);
        if (r.size() == 0) {
            admin.sendMessage("その勢力は存在しません: " + powerName);
            return;
        }
        bot.getElitePowerProvider().addElitePower(new DefaultEliteSimpleData(r.get(0).getName(), r.get(0).getId()));
        admin.sendMessage("勢力を追加しました");
    }

    @Override
    public String getPrefix() {
        return "ppadd";
    }

    @Override
    public String getDescription() {
        return "勢力を追加します";
    }
}
