package net.hacbase.elitebot.discord;

import java.util.Set;

public interface EliteStatusProvider {
    EliteStatus getEliteStatusByName(String name);
    EliteStatus getEliteStatusById(String id);
    Set<EliteStatus> getEliteStatuses();
}
