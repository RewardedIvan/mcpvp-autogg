package me.rewardedivan.mcpvpautogg.config;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtIo;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Config {
    private static final Path confPath = FabricLoader.getInstance().getConfigDir().resolve("mcpvp-autogg.nbt");
    private static NbtCompound conf = new NbtCompound();
    private static final NbtCompound defaultConf = new NbtCompound();

    public static final String ENABLED = "enabled";
    public static final String CHAT_HISTORY = "chat-history";
    public static final String WIN_MSG = "win";
    public static final String LOSE_MSG = "lose";
    public static final String DRAW_MSG = "draw";

    public static void write(String key, String value) {
        conf.putString(key, value);
    }
    public static void write(String key, Boolean value) {
        conf.putBoolean(key, value);
    }

    static {
        defaultConf.putBoolean(ENABLED, true);
        defaultConf.putBoolean(CHAT_HISTORY, false);
        defaultConf.putString(WIN_MSG, "gg");
        defaultConf.putString(LOSE_MSG, "gg");
        defaultConf.putString(DRAW_MSG, "gg");

        try {
            conf = NbtIo.read(confPath.toFile());
        } catch(Exception e) {
            LoggerFactory.getLogger("mcpvp-autogg").info("couldn't read the config file");
        }

        if (conf == null)
            conf = defaultConf;
    }

    public static NbtElement get(String key) {
        return (conf.get(key) == null) ? defaultConf.get(key) : conf.get(key);
    }
    public static NbtElement getDefault(String key) {
        return defaultConf.get(key);
    }

    public static void save() {
        try {
            NbtIo.write(conf, confPath.toFile());
        } catch(Exception e) {
            LoggerFactory.getLogger("mcpvp-autogg").info("couldn't write to config file");
        }
    }
}
