package net.toshimichi.elitebot.discord;

import net.toshimichi.elitebot.save.EliteSimpleData;

import java.util.Collection;

public interface EliteUser extends EliteSimpleData {

    String getNickName();

    ElitePower getElitePower();

    boolean setElitePower(ElitePower power);

    Collection<EliteStatus> getEliteStatuses();

    boolean setEliteStatuses(Collection<EliteStatus> statuses);

    void sendMessage(String message);

    boolean isAdmin();
}
