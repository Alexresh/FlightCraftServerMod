package ru.mcflightcraft.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

public class GodCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("god").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).executes(GodCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        if(serverPlayer.interactionManager.getGameMode() == GameMode.SURVIVAL){
            serverPlayer.getAbilities().invulnerable = !serverPlayer.getAbilities().invulnerable;
            serverPlayer.sendAbilitiesUpdate();
            serverPlayer.sendMessage(Text.literal("God: " + serverPlayer.getAbilities().invulnerable));
        }else{
            serverPlayer.sendMessage(Text.literal("Survival mode required"));

        }
        return 1;
    }
}
