package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.discord.EliteUser;

public interface EliteCommand {
    String getPrefix();

    void execute(EliteUser user, String[] args);

    default String link(String[] arr, int start, int end) {
        StringBuilder result = new StringBuilder();
        if(arr.length > end)
            end = arr.length;
        for (int i = start; i < end; i++) {
            if(i != start)
                result.append(' ');
            result.append(arr[i]);
        }
        return result.toString();
    }
}
