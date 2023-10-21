package ru.mcflightcraft.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import ru.mcflightcraft.command.util.screens.*;

public class OpenCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("open").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).then(CommandManager.literal("craft").executes(OpenCommand::craftingScreen)));
        dispatcher.register(CommandManager.literal("open").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).then(CommandManager.literal("anvil").executes(OpenCommand::anvilScreen)));
        dispatcher.register(CommandManager.literal("open").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).then(CommandManager.literal("enderchest").executes(OpenCommand::enderchestScreen)));
        dispatcher.register(CommandManager.literal("open").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).then(CommandManager.literal("grindstone").executes(OpenCommand::grindstoneScreen)));
        dispatcher.register(CommandManager.literal("open").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).then(CommandManager.literal("stonecutter").executes(OpenCommand::stonecutterScreen)));
        dispatcher.register(CommandManager.literal("open").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).then(CommandManager.literal("trash").executes(OpenCommand::trashScreen)));
        dispatcher.register(CommandManager.literal("open").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).then(CommandManager.literal("cartography").executes(OpenCommand::cartographyScreen)));
        dispatcher.register(CommandManager.literal("open").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).then(CommandManager.literal("smith").executes(OpenCommand::smithingScreen)));
    }



    private static int craftingScreen(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                new CraftingTableCommandScreen(syncId, playerInventory, ScreenHandlerContext.create(player.getEntityWorld(), player.getBlockPos())),
                Text.translatable("block.minecraft.crafting_table")));
        serverPlayer.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
        return 1;
    }
    private static int anvilScreen(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                new AnvilCommandScreen(syncId, playerInventory, ScreenHandlerContext.create(player.getEntityWorld(), player.getBlockPos())),
                Text.translatable("block.minecraft.anvil")));
        serverPlayer.incrementStat(Stats.INTERACT_WITH_ANVIL);
        return 1;
    }
    private static int enderchestScreen(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, player.getEnderChestInventory()),
                Text.translatable("container.enderchest")));
        serverPlayer.incrementStat(Stats.OPEN_ENDERCHEST);
        return 1;
    }
    private static int grindstoneScreen(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                new GrindstoneCommandScreen(syncId, playerInventory, ScreenHandlerContext.create(player.getEntityWorld(), player.getBlockPos())),
                Text.translatable("block.minecraft.grindstone")));
        serverPlayer.incrementStat(Stats.INTERACT_WITH_GRINDSTONE);
        return 1;
    }
    private static int stonecutterScreen(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                new StonecutterCommandScreen(syncId, playerInventory, ScreenHandlerContext.create(player.getEntityWorld(), player.getBlockPos())),
                Text.translatable("block.minecraft.stonecutter")));
        serverPlayer.incrementStat(Stats.INTERACT_WITH_STONECUTTER);
        return 1;
    }
    private static int trashScreen(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, new SimpleInventory(27)),
                Text.literal("回")));
        return 1;
    }
    private static int cartographyScreen(CommandContext<ServerCommandSource> serverCommandSourceCommandContext)throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                new CartographyTableCommandScreen(syncId, playerInventory, ScreenHandlerContext.create(player.getEntityWorld(), player.getBlockPos())),
                Text.translatable("block.minecraft.cartography_table")));
        serverPlayer.incrementStat(Stats.INTERACT_WITH_CARTOGRAPHY_TABLE);
        return 1;
    }
    private static int smithingScreen(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                new SmithingTableCommandScreen(syncId, playerInventory, ScreenHandlerContext.create(player.getEntityWorld(), player.getBlockPos())),
                Text.translatable("block.minecraft.smithing_table")));
        serverPlayer.incrementStat(Stats.INTERACT_WITH_SMITHING_TABLE);
        return 1;
    }

}
