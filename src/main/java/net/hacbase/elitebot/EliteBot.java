package net.hacbase.elitebot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.hacbase.elitebot.commands.DefaultEliteCommands;
import net.hacbase.elitebot.commands.EliteCommands;
import net.hacbase.elitebot.discord.DefaultElitePowers;
import net.hacbase.elitebot.discord.DefaultEliteStatuses;
import net.hacbase.elitebot.discord.ElitePowers;
import net.hacbase.elitebot.discord.EliteStatuses;
import net.hacbase.elitebot.discord.EliteReceiver;
import net.hacbase.elitebot.discord.JDAEliteReceiver;
import net.hacbase.elitebot.save.EliteSaveSystem;
import net.hacbase.elitebot.save.FileEliteSaveSystem;

import javax.security.auth.login.LoginException;

public class EliteBot {

    private final JDA jda;
    private final TextChannel channel;
    private final ElitePowers powers;
    private final EliteStatuses statuses;
    private final EliteCommands commands;
    private final EliteSaveSystem powerSave;
    private final EliteSaveSystem statusSave;
    private final EliteReceiver receiver;

    public JDA getJda() {
        return jda;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public ElitePowers getPowers() {
        return powers;
    }

    public EliteStatuses getStatuses() {
        return statuses;
    }

    public EliteCommands getCommands() {
        return commands;
    }

    public EliteReceiver getReceiver() {
        return receiver;
    }

    public EliteBot(String accessToken, String channelId) throws LoginException {
        jda = new JDABuilder(accessToken).build();
        channel = jda.getTextChannelById(channelId);
        powers = new DefaultElitePowers();
        statuses = new DefaultEliteStatuses();
        commands = new DefaultEliteCommands();
        powerSave = new FileEliteSaveSystem();
        statusSave = new FileEliteSaveSystem();
        receiver = new JDAEliteReceiver();
    }
}
