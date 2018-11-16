package net.hacbase.elitebot.discord;

public interface EliteReceiver {
    void addListener(EliteListener listener);

    void removeListener(EliteListener listener);
}
