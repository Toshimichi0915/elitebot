package net.hacbase.elitebot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.hacbase.elitebot.commands.DefaultEliteCommandParser;
import net.hacbase.elitebot.commands.DefaultEliteCommandProvider;
import net.hacbase.elitebot.commands.EliteCommandParser;
import net.hacbase.elitebot.commands.EliteCommandProvider;
import net.hacbase.elitebot.discord.*;
import net.hacbase.elitebot.save.EliteSaveSystem;
import net.hacbase.elitebot.save.FileEliteSaveSystem;

import javax.security.auth.login.LoginException;
import java.io.File;

public class EliteBot {

    private final JDA jda;
    private final TextChannel channel;
    private final ElitePowerProvider powers;
    private final EliteStatusProvider statuses;
    private final EliteCommandProvider commands;
    private final EliteCommandParser parser;
    private final EliteSaveSystem powerSave;
    private final EliteSaveSystem statusSave;
    private final EliteReceiver receiver;

    public EliteBot(String accessToken, String channelId, File powerFile, File statusFile) throws LoginException {
        jda = new JDABuilder(accessToken).build();
        channel = jda.getTextChannelById(channelId);
        powerSave = new FileEliteSaveSystem(powerFile);
        powerSave.load();
        statusSave = new FileEliteSaveSystem(statusFile);
        statusSave.load();
        powers = new JDAElitePowerProvider(jda, powerSave.getEliteSimpleData());
        statuses = new JDAEliteStatusProvider(jda, statusSave.getEliteSimpleData());
        commands = new DefaultEliteCommandProvider();
        parser = new DefaultEliteCommandParser();
        receiver = new JDAEliteReceiver(this);
    }

    public JDA getJDA() {
        return jda;
    }

    public TextChannel getTextChannel() {
        return channel;
    }

    public ElitePowerProvider getElitePowerProvider() {
        return powers;
    }

    public EliteStatusProvider getEliteStatusProvider() {
        return statuses;
    }

    public EliteCommandProvider getEliteCommandProvider() {
        return commands;
    }

    public EliteCommandParser getParser() {
        return parser;
    }

    public EliteReceiver getEliteReceiver() {
        return receiver;
    }
}
