package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.discord.EliteUser;

public interface EliteCommand {
    String getPrefix();

    void execute(EliteUser user, String[] args);
}
