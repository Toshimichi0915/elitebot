package net.hacbase.elitebot.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.hacbase.elitebot.EliteBot;

import java.util.HashSet;
import java.util.Set;

public class JDAEliteReceiver extends ListenerAdapter implements EliteReceiver {

    private final Set<EliteListener> listeners = new HashSet<>();
    private final EliteBot bot;

    public JDAEliteReceiver(EliteBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        EliteUser user = new JDAEliteUser(bot, event.getMember());
        listeners.forEach(l->l.onReceived(user, event.getMessage().getContentDisplay()));
    }

    @Override
    public void addListener(EliteListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(EliteListener listener) {
        listeners.remove(listener);
    }
}
