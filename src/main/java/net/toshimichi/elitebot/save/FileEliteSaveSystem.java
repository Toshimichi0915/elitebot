package net.toshimichi.elitebot.save;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileEliteSaveSystem implements EliteSaveSystem {

    private final File file;
    private final Set<EliteSimpleData> data;

    public FileEliteSaveSystem(File file) {
        this.file = file;
        this.data = new HashSet<>();
    }

    private String unescape(String str) {
        return str.replace("\\:", ":");
    }

    @Override
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (EliteSimpleData element : data) {
                writer.write(unescape(element.getName()) + "[^\\\\]:" + unescape(element.getId()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        data.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] split = line.split("[^\\\\]:");
                data.add(new DefaultEliteSimpleData(unescape(split[0]), unescape(split[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
