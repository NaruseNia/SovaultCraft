package com.nia.sovaultcraft.commands;

import com.nia.sovaultcraft.SovaultCraft;
import org.spongepowered.api.Game;

public class Commands {

    SovaultCraftCommands commands;

    public void registerAll(SovaultCraft plugin, Game game){
        commands = new SovaultCraftCommands();
        game.getCommandManager().register(plugin, commands.sovaultCommand, "sovault", "sv");
    }

}
