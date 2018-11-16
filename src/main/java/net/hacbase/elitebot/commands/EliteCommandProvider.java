package net.hacbase.elitebot.commands;

import java.util.Collection;

public interface EliteCommandProvider {
    EliteCommand getCommand(String prefix);

    Collection<EliteCommand> getCommands();
}
