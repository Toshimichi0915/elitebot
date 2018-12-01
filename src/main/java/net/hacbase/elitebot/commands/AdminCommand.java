package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.discord.EliteUser;

abstract public class AdminCommand implements EliteCommand {
    @Override
    public void execute(EliteUser user, String[] args) {
        if (!user.isAdmin()) {
            user.sendMessage("管理者以外にはこのコマンドは使用不可能です");
            return;
        }
        conduct(user, args);
    }

    abstract public void conduct(EliteUser admin, String[] args);
}
