package com.nia.sovaultcraft.utils;

import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

public class ConfigurationManager {

    private static final ConfigurationManager instance = new ConfigurationManager();

    @Inject
    private Logger logger;

    public static ConfigurationManager getInstance() {
        return instance;
    }

    private CommentedConfigurationNode config;
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    public void setup(File configFile, ConfigurationLoader<CommentedConfigurationNode> loader){
        this.loader = loader;
        if(!configFile.exists()){
            try{
                configFile.createNewFile();
                loadConfig();
                config.getNode("server_name").setValue("Minecraft Server");
                saveConfig();
            }catch (IOException e){
                logger.warn("Error! Could not initialize config file!");
            }
        }else{
            loadConfig();
        }
    }

    public CommentedConfigurationNode getConfig(){
        return config;
    }

    public void saveConfig(){
        try{
            loader.save(config);
        }catch (IOException e){
            logger.warn("Error! Could not save config file!");
        }
    }

    public void loadConfig(){
        try{
            loader.load();
        }catch (IOException e){
            logger.warn("Error! Could not load config file!");
        }
    }

}
