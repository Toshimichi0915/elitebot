package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.discord.EliteUser;

import java.util.Arrays;

public class DefaultEliteCommandParser implements EliteCommandParser {
    @Override
    public void parse(EliteCommandProvider provider, EliteUser user, String msg) {
        String[] arr = msg.split("[\\s]+");
        String prefix = arr[0];
        String[] args = Arrays.copyOfRange(arr, 1, arr.length);
        EliteCommand cmd = provider.getCommand(prefix);
        if (cmd == null) return;
        cmd.execute(user, args);
    }
}
