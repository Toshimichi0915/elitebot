package net.hacbase.elitebot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.hacbase.elitebot.commands.DefaultEliteCommandProvider;
import net.hacbase.elitebot.commands.EliteCommandProvider;
import net.hacbase.elitebot.discord.*;
import net.hacbase.elitebot.save.FileEliteSaveSystem;

import javax.security.auth.login.LoginException;
import java.io.File;

public class EliteBot {

    private final JDA jda;
    private final TextChannel channel;
    private final JDAElitePowerProvider powers;
    private final JDAEliteStatusProvider statuses;
    private final DefaultEliteCommandProvider provider;
    private final FileEliteSaveSystem powerSave;
    private final FileEliteSaveSystem statusSave;
    private final JDAEliteReceiver receiver;

    public EliteBot(String accessToken, String channelId, File powerFile, File statusFile) throws LoginException {
        jda = new JDABuilder(accessToken).build();
        channel = jda.getTextChannelById(channelId);
        powerSave = new FileEliteSaveSystem(powerFile);
        powerSave.load();
        statusSave = new FileEliteSaveSystem(statusFile);
        statusSave.load();
        powers = new JDAElitePowerProvider(jda, powerSave.getEliteSimpleData());
        statuses = new JDAEliteStatusProvider(jda, statusSave.getEliteSimpleData());
        provider = new DefaultEliteCommandProvider();
        receiver = new JDAEliteReceiver(this);

        receiver.addListener(new JDACommandEliteListener(this));
        jda.addEventListener(receiver);

    }

    public static void main(String[] args) throws Exception {
        File power = new File("./power.txt");
        power.createNewFile();
        File status = new File("./status.txt");
        status.createNewFile();
        EliteBot bot = new EliteBot(args[0], args[1], power, status);
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
        return provider;
    }

    public EliteReceiver getEliteReceiver() {
        return receiver;
    }
}
