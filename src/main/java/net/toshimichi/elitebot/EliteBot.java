package net.toshimichi.elitebot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.toshimichi.elitebot.commands.*;
import net.toshimichi.elitebot.discord.*;
import net.toshimichi.elitebot.save.EliteSaveSystem;
import net.toshimichi.elitebot.save.FileEliteSaveSystem;

import javax.security.auth.login.LoginException;
import java.io.File;

public class EliteBot {

    private final JDA jda;
    private final String channelId;
    private final ElitePowerProvider powers;
    private final EliteStatusProvider statuses;
    private final EliteCommandProvider provider;
    private final EliteReceiver receiver;
    private final Role adminRole;
    private final File powerFile;
    private final File statusFile;

    public EliteBot(String accessToken, String channelId, File powerFile, File statusFile, String adminRoleName) throws LoginException, InterruptedException {
        jda = new JDABuilder(accessToken).buildBlocking();
        this.channelId = channelId;
        this.powerFile = powerFile;
        this.statusFile = statusFile;
        EliteSaveSystem powerSave = new FileEliteSaveSystem(powerFile);
        powerSave.load();
        EliteSaveSystem statusSave = new FileEliteSaveSystem(statusFile);
        statusSave.load();
        powers = new JDAElitePowerProvider(jda, powerSave.getEliteSimpleData());
        statuses = new JDAEliteStatusProvider(jda, statusSave.getEliteSimpleData());
        DefaultEliteCommandProvider provider = new DefaultEliteCommandProvider();
        provider.addCommand(new PowerCommand(this));
        provider.addCommand(new ShutdownCommand(this));
        provider.addCommand(new PowerAddCommand(this));
        provider.addCommand(new PowerRemoveCommand(this));
        provider.addCommand(new PowerListCommand(this));
        provider.addCommand(new StatusCommand(this));
        provider.addCommand(new StatusClearCommand(this));
        provider.addCommand(new StatusAddCommand(this));
        provider.addCommand(new StatusListCommand(this));
        provider.addCommand(new StatusRemoveCommand(this));
        provider.addCommand(new HelpCommand(this));
        this.provider = provider;
        receiver = new JDAEliteReceiver(this);
        adminRole = jda.getRolesByName(adminRoleName, true).get(0);

        receiver.addListener(new JDACommandEliteListener(this));
        jda.addEventListener(receiver);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            save();
            shutdown();
        }));
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
        new EliteBot(accessToken, channelId, power, status, adminGroup);
    }

    public JDA getJDA() {
        return jda;
    }

    public TextChannel getTextChannel() {
        return jda.getTextChannelById(channelId);
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

    public void save() {
        EliteSaveSystem powerSave = new FileEliteSaveSystem(powerFile);
        powers.getElitePowers().forEach(powerSave::addEliteSimpleData);
        powerSave.save();
        EliteSaveSystem statusSave = new FileEliteSaveSystem(statusFile);
        statuses.getEliteStatuses().forEach(statusSave::addEliteSimpleData);
        statusSave.save();
    }

    public void shutdown() {
        jda.shutdown();
    }
}
