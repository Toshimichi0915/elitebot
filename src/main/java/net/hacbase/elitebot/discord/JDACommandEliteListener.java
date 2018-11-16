package net.hacbase.elitebot.discord;

import net.hacbase.elitebot.EliteBot;
import net.hacbase.elitebot.commands.EliteCommand;

import java.util.Arrays;

public class JDACommandEliteListener implements EliteListener {

    private final EliteBot bot;

    public JDACommandEliteListener(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public void onReceived(EliteUser user, String msg) {
        String[] arr = msg.split("[\\s]+");
        String prefix = arr[0];
        String[] args = Arrays.copyOfRange(arr, 1, arr.length);
        EliteCommand cmd = bot.getEliteCommandProvider().getCommand(prefix);
        if (cmd == null) return;
        cmd.execute(user, args);
    }
}
