package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private final HashMap<String, String> fileMap;

    private final File file;

    public DiskMap(File file) {
        this.fileMap = new HashMap<>();
        this.file = file;
    }

    private void write() {
        try (var fileWriter = new BufferedWriter(new FileWriter(file))) {
            var entrySet = fileMap.entrySet();
            for (var entry : entrySet) {
                fileWriter.write(entry.getKey() + ":" + entry.getValue());
                fileWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void read() {
        try (var fileReader = new BufferedReader(new FileReader(file))) {
            var readLine = fileReader.readLine();
            while (readLine != null) {
                var splitReadLine = readLine.split(":");
                fileMap.put(splitReadLine[0], splitReadLine[1]);
                readLine = fileReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        read();
        return fileMap.size();
    }

    @Override
    public boolean isEmpty() {
        read();
        return fileMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        read();
        return fileMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        read();
        return fileMap.containsKey((String) value);
    }

    @Override
    public String get(Object key) {
        read();
        return fileMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        var putReturn = fileMap.put(key, value);
        write();
        return putReturn;
    }

    @Override
    public String remove(Object key) {
        var removeReturn = fileMap.remove(key);
        write();
        return removeReturn;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        fileMap.putAll(m);
        write();
    }

    @Override
    public void clear() {
        fileMap.clear();
        write();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        read();
        return fileMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        read();
        return fileMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        read();
        return fileMap.entrySet();
    }
}
