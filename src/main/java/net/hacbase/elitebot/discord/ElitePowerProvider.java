package net.hacbase.elitebot.discord;

import net.hacbase.elitebot.save.EliteSimpleData;

import java.util.Collection;

public interface ElitePowerProvider {
    ElitePower getElitePowerByName(String name);

    ElitePower getElitePowerById(String id);

    Collection<ElitePower> getElitePowers();

    boolean addElitePower(EliteSimpleData data);
}
