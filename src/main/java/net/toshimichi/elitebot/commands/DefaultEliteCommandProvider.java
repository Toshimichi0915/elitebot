package net.toshimichi.elitebot.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DefaultEliteCommandProvider implements EliteCommandProvider {

    private final Set<EliteCommand> cmds = new HashSet<>();

    public void addCommand(EliteCommand cmd) {
        cmds.add(cmd);
    }

    @Override
    public EliteCommand getCommand(String prefix) {
        for (EliteCommand cmd : cmds) {
            if (cmd.getPrefix().equals(prefix))
                return cmd;
        }
        return null;
    }

    @Override
    public Collection<EliteCommand> getCommands() {
        return new HashSet<>(cmds);
    }
}
