package net.hacbase.elitebot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.hacbase.elitebot.commands.DefaultEliteCommandProvider;
import net.hacbase.elitebot.commands.EliteCommandProvider;
import net.hacbase.elitebot.commands.PowerChangeCommand;
import net.hacbase.elitebot.commands.ShutdownCommand;
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
    private final EliteCommandProvider provider;
    private final EliteSaveSystem powerSave;
    private final EliteSaveSystem statusSave;
    private final EliteReceiver receiver;
    private final Role adminRole;

    public EliteBot(String accessToken, String channelId, File powerFile, File statusFile, String adminRoleName) throws LoginException, InterruptedException {
        jda = new JDABuilder(accessToken).buildBlocking();
        channel = jda.getTextChannelById(channelId);
        powerSave = new FileEliteSaveSystem(powerFile);
        powerSave.load();
        statusSave = new FileEliteSaveSystem(statusFile);
        statusSave.load();
        powers = new JDAElitePowerProvider(jda, powerSave.getEliteSimpleData());
        statuses = new JDAEliteStatusProvider(jda, statusSave.getEliteSimpleData());
        DefaultEliteCommandProvider provider = new DefaultEliteCommandProvider();
        provider.addCommand(new PowerChangeCommand(this));
        provider.addCommand(new ShutdownCommand(this));
        this.provider = provider;
        receiver = new JDAEliteReceiver(this);
        adminRole = jda.getRolesByName(adminRoleName, true).get(0);

        receiver.addListener(new JDACommandEliteListener(this));
        jda.addEventListener(receiver);

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

    public Role getAdminRole() {
        return adminRole;
    }

    public EliteReceiver getEliteReceiver() {
        return receiver;
    }

    public static void main(String[] args) throws Exception {
        File power = new File("./power.txt");
        power.createNewFile();
        File status = new File("./status.txt");
        status.createNewFile();
        File settings = new File("./settings.txt");
        settings.createNewFile();
        EliteSaveSystem s = new FileEliteSaveSystem(settings);
        s.load();
        String accessToken = s.getEliteSimpleDataByName("accessToken").getId();
        String channelId = s.getEliteSimpleDataByName("channelId").getId();
        String adminGroup = s.getEliteSimpleDataByName("adminRole").getId();
        EliteBot bot = new EliteBot(accessToken, channelId, power, status, adminGroup);
    }
}
