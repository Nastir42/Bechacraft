package bapt.bechacraft.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import bapt.bechacraft.Bechacraft;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ModCommands {

    public static final VocationCommand VOCATION = new VocationCommand();
    
    public static <C extends Command<ServerCommandSource>> void registerCommand(C command, String cmd) {
        registerCommand(CommandManager.literal(cmd).executes(command));
    }
    
    public static <C extends Command<ServerCommandSource>> void registerCommand(C command, String ... cmd) {
        if(cmd.length == 0) {
            throw new IllegalArgumentException("Arguments expected for command building");
        }
        LiteralArgumentBuilder<ServerCommandSource> literal = CommandManager.literal(cmd[cmd.length - 1]).executes(command);
        for(int i = cmd.length - 2; i >= 0; i--) {
            literal = CommandManager.literal(cmd[i]).then(literal);
        }
        
        registerCommand(literal);
    }

    public static <C extends Command<ServerCommandSource>> void registerCommand(LiteralArgumentBuilder<ServerCommandSource> literal) {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal));
    }

    public static void registerCommands() {

        registerCommand(VOCATION, "vocation");
        registerCommand(VOCATION, "vocation", "info");

        Bechacraft.LOGGER.info("Commands have been initialized");
    }
}
