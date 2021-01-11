package raidClicker.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import raidClicker.contentPayloads.ComponentManager;
import raidClicker.contentPayloads.PayloadUpdateSettings;
import raidClicker.contentPayloads.PayloadUpdateUiSettings;
import raidClicker.models.SettingsModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static raidClicker.models.SettingsModel.DEFAULT_SETTINGS;

public final class SettingsHelper {

    private static final String SETTINGS_FILE_LOCATION = "./Settings.json";
    private static final Path SETTINGS_FILE = Paths.get(SETTINGS_FILE_LOCATION);
    public static SettingsModel SETTINGS;
    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    public static boolean createSettingsFileIfNotExists() {

        if (isSettingsFilePresent()) {
            return false;
        }

        SETTINGS = DEFAULT_SETTINGS;
        try {
            File settingsFile = SETTINGS_FILE.toFile();
            final boolean isCreated = settingsFile.createNewFile();
            System.out.printf("File was %screated%n", isCreated ? "" : "not ");
            writeSettingsToFile(settingsFile);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return true;
    }

    private static void writeSettingsToFile(File settingsFile) {
        final FileWriter writer;
        try {
            writer = new FileWriter(settingsFile);
            GSON.toJson(SETTINGS, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void writeSettingsToFile() {
        writeSettingsToFile(SETTINGS_FILE.toFile());
    }

    public static void loadSettingsFromFile() {
        try {
            Reader reader = Files.newBufferedReader(SETTINGS_FILE);
            SETTINGS = GSON.fromJson(reader, SettingsModel.class);
            reader.close();
            ComponentManager.addPayloadToConsume(new PayloadUpdateUiSettings());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Boolean isSettingsFilePresent() {
        return SETTINGS_FILE.toFile().exists();
    }

    public static void saveSettingsToFile() {
        ComponentManager.addPayloadToConsume(new PayloadUpdateSettings());
    }
}
