package com.imzcc.plugins.config;

import com.imzcc.plugins.LoginTitle;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public static String prefixText;
    public static String prefixColor;
    public static ChatColor prefixChatColor;
    public static String serverNameColor;
    public static ChatColor serverNameChatColor;
    public static String subTitleText;
    public static String subTitleColor;
    public static ChatColor subTitleChatColor;
    public static long waiting;

    public static void load() {
        try {
            File configFile = new File(LoginTitle.getInstance().getDataFolder(), "config.yml");
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
            waiting = config.getLong("welcome.waiting");
            prefixText = config.getString("welcome.prefix.text");
            prefixColor = config.getString("welcome.prefix.color");
            prefixChatColor = prefixColor.length() > 0 ? ChatColor.of(prefixColor) : ChatColor.WHITE;
            subTitleText = config.getString("welcome.subTitle.text");
            subTitleColor = config.getString("welcome.subTitle.color");
            subTitleChatColor = subTitleColor.length() > 0 ? ChatColor.of(subTitleColor) : ChatColor.WHITE;
            serverNameColor = config.getString("welcome.serverName.color");
            serverNameChatColor = serverNameColor.length() > 0 ? ChatColor.of(serverNameColor) : ChatColor.WHITE;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
