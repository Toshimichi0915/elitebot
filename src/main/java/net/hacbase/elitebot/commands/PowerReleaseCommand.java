package net.hacbase.elitebot.commands;

import net.hacbase.elitebot.discord.EliteUser;

public class PowerReleaseCommand implements EliteCommand, CommandDescription{
    @Override
    public String getPrefix() {
        return "pprel";
    }

    @Override
    public void execute(EliteUser user, String[] args) {
        if(user.getElitePower() == null) {
            user.sendMessage("勢力に入っていないため使用できません");
            return;
        }
        user.setElitePower(null);
        user.sendMessage("勢力から脱退しました");
    }

    @Override
    public String getDescription() {
        return "勢力から脱退します";
    }
}
