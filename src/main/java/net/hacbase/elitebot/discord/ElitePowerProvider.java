package net.hacbase.elitebot.discord;

import java.util.Set;

public interface ElitePowerProvider {
    ElitePower getElitePowerByName(String name);
    ElitePower getElitePowerById(String id);
    Set<ElitePower> getElitePowers();
}
