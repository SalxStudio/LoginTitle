package com.imzcc.plugins;

import com.imzcc.plugins.config.Config;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

public class LoginTitle extends Plugin implements Listener {
    static Plugin instance;

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerListener(this, this);
        getLogger().info("LoginTitle has been enabled.");
        instance = this;

        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        try {
            File configFile = new File(this.getDataFolder(), "config.yml");
            if (!configFile.exists()) {
                InputStream configStream = getResourceAsStream("config.yml");
                Files.copy(configStream, configFile.toPath());
            }
            Config.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onServerConnect(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String targetServerName = event.getTarget().getName();
        ProxyServer.getInstance().getScheduler().schedule(this, () -> {
            Title title = ProxyServer.getInstance().createTitle();
            title.title(new TextComponent(Config.prefixChatColor + Config.prefixText + Config.serverNameChatColor + targetServerName));
            title.subTitle(new TextComponent(Config.subTitleChatColor + Config.subTitleText));
            player.sendTitle(title);
        }, Config.waiting, TimeUnit.SECONDS);
    }

    public static Plugin getInstance() {
        return instance;
    }
}
