package net.toshimichi.elitebot.commands;

import net.dv8tion.jda.core.entities.Role;
import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.EliteUser;
import net.toshimichi.elitebot.save.DefaultEliteSimpleData;

import java.util.List;

public class StatusAddCommand extends AdminCommand implements CommandDescription{

    private final EliteBot bot;

    public StatusAddCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public void conduct(EliteUser admin, String[] args) {
        if (args.length < 1) {
            admin.sendMessage("追加する状態を指定してください");
            return;
        }
        String statusName = link(args, 0, args.length);
        List<Role> r = bot.getTextChannel().getGuild().getRolesByName(statusName, true);
        if (r.size() == 0) {
            admin.sendMessage("その状態は存在しません: " + statusName);
            return;
        }
        bot.getEliteStatusProvider().addEliteStatus(new DefaultEliteSimpleData(r.get(0).getName(), r.get(0).getId()));
        admin.sendMessage("状態を追加しました");
    }

    @Override
    public String getPrefix() {
        return "stadd";
    }

    @Override
    public String getDescription() {
        return "状態を追加します";
    }
}
