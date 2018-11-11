package net.hacbase.elitebot.discord;

import java.util.Collection;

public interface ElitePowerProvider {
    ElitePower getElitePowerByName(String name);

    ElitePower getElitePowerById(String id);

    Collection<ElitePower> getElitePowers();
}
