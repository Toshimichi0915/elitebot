package net.toshimichi.elitebot.discord;

import net.toshimichi.elitebot.save.EliteSimpleData;

import java.util.Collection;

public interface ElitePowerProvider {
    ElitePower getElitePowerByName(String name);

    ElitePower getElitePowerById(String id);

    Collection<ElitePower> getElitePowers();

    boolean addElitePower(EliteSimpleData data);

    boolean removeElitePower(ElitePower power);
}
