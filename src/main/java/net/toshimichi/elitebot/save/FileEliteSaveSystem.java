package net.toshimichi.elitebot.save;

import java.io.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class FileEliteSaveSystem implements EliteSaveSystem {

    private final File file;
    private final Set<EliteSimpleData> data;

    public FileEliteSaveSystem(File file) {
        this.file = file;
        this.data = new HashSet<>();
    }

    @Override
    public void save() {
        Properties properties = new Properties();
        for(EliteSimpleData element : data)
            properties.setProperty(element.getName(), element.getId());
        try(FileWriter writer = new FileWriter(file)) {
            properties.store(writer, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        data.clear();
        Properties properties = new Properties();
        try(FileReader reader = new FileReader(file)) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<String> keys = properties.stringPropertyNames();
        for(String key : keys)
            data.add(new DefaultEliteSimpleData(key, properties.getProperty(key)));
    }

    @Override
    public void addEliteSimpleData(EliteSimpleData data) {
        this.data.add(data);
    }

    @Override
    public void removeEliteSimpleData(EliteSimpleData data) {
        this.data.remove(data);
    }

    @Override
    public EliteSimpleData getEliteSimpleDataByName(String name) {
        for (EliteSimpleData element : data) {
            if (element.getName().equals(name))
                return element;
        }
        return null;
    }

    @Override
    public EliteSimpleData getEliteSimpleDataById(String id) {
        for (EliteSimpleData element : data) {
            if (element.getId().equals(id))
                return element;
        }
        return null;
    }

    @Override
    public Set<EliteSimpleData> getEliteSimpleData() {
        return new HashSet<>(data);
    }
}
