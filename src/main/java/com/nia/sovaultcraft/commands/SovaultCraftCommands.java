package com.nia.sovaultcraft.commands;

import com.nia.sovaultcraft.SovaultCraft;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class SovaultCraftCommands {

    CommandSpec childServerNameSet =
            CommandSpec.builder()
                    .executor(new SovaultCommandExecuters.Childs.ServerSetNameExecuter())
                    .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
                    .description(Text.of("サーバーの名前設定。"))
                    .build();

    CommandSpec childServer =
            CommandSpec.builder()
                    .executor(new SovaultCommandExecuters.Childs.ServerCommandExecuter())
                    .child(childServerNameSet, "nameset")
                    .description(Text.of("このサーバーに関するコマンド。"))
                    .build();

    CommandSpec sovaultCommand =
            CommandSpec.builder()
                    .executor(new SovaultCommandExecuters.SovalutCommandExecuter())
                    .child(childServer, "server")
                    .description(Text.of("SovaultCraft全般に関するコマンド。"))
                    .build();


}
