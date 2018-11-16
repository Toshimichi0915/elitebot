package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.discord.EliteUser;

public interface EliteCommandParser {
    void parse(EliteCommandProvider provider, EliteUser user, String msg);
}
