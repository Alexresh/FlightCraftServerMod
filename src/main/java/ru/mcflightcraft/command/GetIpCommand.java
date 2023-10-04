package ru.mcflightcraft.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

public class GetIpCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("getip")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4))
                .then(CommandManager.argument("target", EntityArgumentType.player()).executes(GetIpCommand::run)));
    }

    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = serverCommandSourceCommandContext.getSource().getPlayerOrThrow();
        ServerWorld serverWorld = serverCommandSourceCommandContext.getSource().getWorld();
        Entity entity = EntityArgumentType.getEntity(serverCommandSourceCommandContext, "target");
        if(entity instanceof ServerPlayerEntity target){
            serverPlayer.sendMessage(Text.literal(target.getIp()));
        }
        return 1;
    }
}
