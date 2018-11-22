package net.hacbase.elitebot.discord;

import net.hacbase.elitebot.save.EliteSimpleData;

import java.util.Collection;

public interface EliteStatusProvider {
    EliteStatus getEliteStatusByName(String name);

    EliteStatus getEliteStatusById(String id);

    Collection<EliteStatus> getEliteStatuses();

    boolean addEliteStatus(EliteSimpleData data);

    boolean removeEliteStatus(EliteStatus status);
}
