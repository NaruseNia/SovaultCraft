package com.nia.sovaultcraft;

import com.google.inject.Inject;
import com.nia.sovaultcraft.commands.Commands;
import com.nia.sovaultcraft.utils.ConfigurationManager;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.text.title.Title;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

@Plugin(
        id = "sovaultcraft",
        name = "SovaultCraft"
)
public class SovaultCraft {

    @Inject
    Game game;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private File defaultConfig;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path configDir;

    @Inject
    private Logger logger;

    public ConfigurationNode config;
    private Commands commands;

    private static final SovaultCraft instance = new SovaultCraft();

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        Sponge.getServer().getBroadcastChannel().send(Text.of(TextColors.GREEN, "SovaultCraft v", SovaultCraftMeta.version.getValue()));
    }

    @Listener
    public void preInit(GamePreInitializationEvent event){

    }

    @Listener
    public void init(GameInitializationEvent event){
        ConfigurationManager.getInstance().setup(defaultConfig, loader);
        commands = new Commands();
        commands.registerAll(this, game);
    }

    @Listener
    public void postInit(GamePostInitializationEvent event){
        logger.debug("SovaultCraft has loaded.");
    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event){
        Player playerIn = event.getTargetEntity();
        playerIn.sendTitle(Title.of(Text.of(TextColors.GREEN, TextStyles.BOLD, Objects.requireNonNull(ConfigurationManager.getInstance().getConfig().getNode("server_name").getString()))));
    }

    public static SovaultCraft getInstance() {
        return instance;
    }
}
