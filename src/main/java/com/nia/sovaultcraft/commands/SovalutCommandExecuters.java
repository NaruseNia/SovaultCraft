package com.nia.sovaultcraft.commands;

import com.nia.sovaultcraft.SovaultCraft;
import com.nia.sovaultcraft.SovaultCraftMeta;
import com.nia.sovaultcraft.utils.ConfigurationManager;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;

class SovaultCommandExecuters{

    public static class SovalutCommandExecuter implements CommandExecutor {
        @Override
        public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
            if (!(src instanceof Player)){
                src.sendMessage(Text.of(TextColors.GREEN, "SovaultCraft v", SovaultCraftMeta.version.getValue(), TextColors.GRAY, " by Naruse Nia\n",
                        TextColors.RED, "!important! This version is developer build. May happen some problems."));
                return CommandResult.success();
            }
            Player playerIn = (Player)src;
            playerIn.sendMessage(Text.of(TextColors.GREEN, "SovaultCraft v", SovaultCraftMeta.version.getValue(), TextColors.GRAY, " by Naruse Nia\n",
                    TextColors.RED, "!important! This version is developer build. May happen some problems."));
            return CommandResult.success();
        }
    }

    static class Childs{
        public class PluginCommandExecuter implements CommandExecutor{
            @Override
            public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
                return CommandResult.success();
            }
        }

        public static class ServerCommandExecuter extends SovaultCraft implements CommandExecutor{
            @Override
            public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
                String serverName = ConfigurationManager.getInstance().getConfig().getNode("server_name").getString();
                src.sendMessage(Text.of(TextColors.GRAY, "This server name is", TextColors.GREEN, serverName));
                return CommandResult.success();
            }
        }

        public static class ServerSetNameExecuter extends SovaultCraft implements CommandExecutor{
            @Override
            public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
                ConfigurationManager.getInstance().getConfig().getNode("server_name").setValue(args.getOne("name"));
                src.sendMessage(Text.of(TextColors.GRAY, "Server name sets ", TextColors.GREEN, args.getOne("name")));
                return CommandResult.success();
            }
        }
    }

}

