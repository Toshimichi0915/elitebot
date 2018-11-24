package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.discord.EliteUser;

public class ShutdownCommand extends AdminCommand{

    private final EliteBot bot;

    public ShutdownCommand(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public String getPrefix() {
        return "?shutdown";
    }

    @Override
    public void conduct(EliteUser admin, String[] args) {
        bot.getJDA().shutdown();
    }
}
