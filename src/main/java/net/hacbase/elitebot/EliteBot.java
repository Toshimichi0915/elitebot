package net.hacbase.elitebot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.hacbase.elitebot.commands.DefaultEliteCommandProvider;
import net.hacbase.elitebot.commands.EliteCommandProvider;
import net.hacbase.elitebot.discord.DefaultElitePowerProvider;
import net.hacbase.elitebot.discord.DefaultEliteStatusProvider;
import net.hacbase.elitebot.discord.ElitePowerProvider;
import net.hacbase.elitebot.discord.EliteStatusProvider;
import net.hacbase.elitebot.discord.EliteReceiver;
import net.hacbase.elitebot.discord.JDAEliteReceiver;
import net.hacbase.elitebot.save.EliteSaveSystem;
import net.hacbase.elitebot.save.FileEliteSaveSystem;

import javax.security.auth.login.LoginException;

public class EliteBot {

    private final JDA jda;
    private final TextChannel channel;
    private final ElitePowerProvider powers;
    private final EliteStatusProvider statuses;
    private final EliteCommandProvider commands;
    private final EliteSaveSystem powerSave;
    private final EliteSaveSystem statusSave;
    private final EliteReceiver receiver;

    public JDA getJda() {
        return jda;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public ElitePowerProvider getPowers() {
        return powers;
    }

    public EliteStatusProvider getStatuses() {
        return statuses;
    }

    public EliteCommandProvider getCommands() {
        return commands;
    }

    public EliteReceiver getReceiver() {
        return receiver;
    }

    public EliteBot(String accessToken, String channelId) throws LoginException {
        jda = new JDABuilder(accessToken).build();
        channel = jda.getTextChannelById(channelId);
        powers = new DefaultElitePowerProvider();
        statuses = new DefaultEliteStatusProvider();
        commands = new DefaultEliteCommandProvider();
        powerSave = new FileEliteSaveSystem();
        statusSave = new FileEliteSaveSystem();
        receiver = new JDAEliteReceiver();
    }
}
