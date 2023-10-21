package ru.mcflightcraft.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;

public class TransferCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("transfer")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4))
                .then(CommandManager.literal("spawn").executes(TransferCommand::runSpawn)));
        dispatcher.register(CommandManager.literal("transfer")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4))
                .then(CommandManager.literal("spawnpoint").executes(TransferCommand::runBed)));
    }

    private static int runSpawn(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        ServerWorld serverWorld = serverCommandSourceCommandContext.getSource().getWorld().getServer().getOverworld();
        serverPlayer.teleport(serverWorld, serverWorld.getSpawnPos().getX(), serverWorld.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, serverWorld.getSpawnPos().getX(), serverWorld.getSpawnPos().getZ()), serverWorld.getSpawnPos().getZ(), 0, 0);
        serverPlayer.sendMessage(Text.literal("Teleported to spawn"));
        return 1;
    }
    private static int runBed(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        ServerWorld overworld = serverCommandSourceCommandContext.getSource().getWorld().getServer().getOverworld();
        BlockPos spawnpoint = serverPlayer.getSpawnPointPosition();
        if(spawnpoint != null){
            serverPlayer.teleport(overworld, spawnpoint.getX() + 0.5d, spawnpoint.getY() + 0.5d, spawnpoint.getZ() + 0.5d, 0, 0);
            serverPlayer.sendMessage(Text.literal("Teleported to spawnpoint"));
        }else {
            serverPlayer.sendMessage(Text.literal("no spawnpoint :("));
        }

        return 1;
    }
}
