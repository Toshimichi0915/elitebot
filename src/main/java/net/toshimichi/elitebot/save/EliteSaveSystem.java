package net.toshimichi.elitebot.save;

import java.util.Collection;

public interface EliteSaveSystem {
    void save();

    void load();

    void addEliteSimpleData(EliteSimpleData data);

    void removeEliteSimpleData(EliteSimpleData data);

    EliteSimpleData getEliteSimpleDataByName(String name);

    EliteSimpleData getEliteSimpleDataById(String id);

    Collection<EliteSimpleData> getEliteSimpleData();
}
