package net.toshimichi.elitebot.commands;

import net.toshimichi.elitebot.EliteBot;
import net.toshimichi.elitebot.discord.EliteUser;

public class ShutdownCommand extends AdminCommand implements CommandDescription{

    private final EliteBot bot;

    public ShutdownCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "shutdown";
    }

    @Override
    public void conduct(EliteUser admin, String[] args) {
        admin.sendMessage("EliteBotを終了させます");
        bot.save();
        bot.shutdown();
    }

    @Override
    public String getDescription() {
        return "EliteBotを終了させます";
    }
}
