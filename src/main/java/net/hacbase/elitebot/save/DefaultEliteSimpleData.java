package net.hacbase.elitebot.save;

public class DefaultEliteSimpleData implements EliteSimpleData{

    private final String name;
    private final String id;

    public DefaultEliteSimpleData(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }
}
