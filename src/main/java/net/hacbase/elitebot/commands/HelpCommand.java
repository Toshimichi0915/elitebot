package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.EliteUser;

public class HelpCommand implements EliteCommand, CommandDescription {

    private final EliteBot bot;

    public HelpCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "help";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        EliteCommandProvider prov = bot.getEliteCommandProvider();
        if (args.length < 1) {
            StringBuilder builder = new StringBuilder("コマンド一覧: \n");
            for (EliteCommand cmd : prov.getCommands()) {
                if(!user.isAdmin() && cmd instanceof AdminCommand)
                    continue;
                String name = cmd.getPrefix();
                String desc = cmd instanceof CommandDescription ? ((CommandDescription) cmd).getDescription() : "詳細なし";
                builder.append('?');
                builder.append(name);
                builder.append(": ");
                builder.append(desc);
                builder.append('\n');
            }
            user.sendMessage(builder.toString());
            return;
        }

        EliteCommand cmd = prov.getCommand(args[0]);
        if (cmd == null || !(cmd instanceof CommandDescription)) {
            user.sendMessage("そのコマンドのヘルプは存在しません: " + args[0]);
            return;
        }

        user.sendMessage("?" + args[0] + ": " + ((CommandDescription)cmd).getDescription());
    }

    @Override
    public String getDescription() {
        return "コマンドの一覧、ヘルプを表示します";
    }
}
