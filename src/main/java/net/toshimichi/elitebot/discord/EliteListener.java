package net.toshimichi.elitebot.discord;

@FunctionalInterface
public interface EliteListener {
    void onReceived(EliteUser user, String msg);
}
