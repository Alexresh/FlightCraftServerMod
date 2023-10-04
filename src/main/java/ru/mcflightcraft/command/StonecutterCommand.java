package ru.mcflightcraft.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import ru.mcflightcraft.command.util.screens.StonecutterCommandScreen;

public class StonecutterCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("stonecutter").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).executes(StonecutterCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                new StonecutterCommandScreen(syncId, playerInventory, ScreenHandlerContext.create(player.getEntityWorld(), player.getBlockPos())),
                Text.translatable("block.minecraft.stonecutter")));
        serverPlayer.incrementStat(Stats.INTERACT_WITH_STONECUTTER);
        return 1;
    }
}
