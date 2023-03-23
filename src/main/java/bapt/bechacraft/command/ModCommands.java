package bapt.bechacraft.command;

import com.mojang.brigadier.Command;

import bapt.bechacraft.Bechacraft;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ModCommands {

    public static final VocationCommand VOCATION = new VocationCommand();
    
    public static <C extends Command<ServerCommandSource>> void registerCommand(C command, String cmd) {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal(cmd)
        .executes(command)));
    }

    public static void registerCommands() {

        registerCommand(VOCATION, "vocation");

        Bechacraft.LOGGER.info("Commands have been initialized");
    }
}
