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
        if (!arr[0].startsWith("?")) return;
        EliteCommand cmd = bot.getEliteCommandProvider().getCommand(prefix.substring(1));
        if(cmd == null) {
            user.sendMessage("コマンドが存在しません ヘルプは ?help で表示できます");
            return;
        }
        cmd.execute(user, args);
    }
}
