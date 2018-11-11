package net.hacbase.elitebot.discord;

import java.util.Collection;

public interface EliteStatusProvider {
    EliteStatus getEliteStatusByName(String name);

    EliteStatus getEliteStatusById(String id);

    Collection<EliteStatus> getEliteStatuses();
}
