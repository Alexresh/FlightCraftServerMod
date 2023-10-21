package ru.mcflightcraft;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mcflightcraft.command.*;


public class FlightCraftMain implements ModInitializer {

	public static final Identifier gamemodePacket = new Identifier("flightcraft", "gamemode_change_packet");
    public static final Logger LOGGER = LoggerFactory.getLogger("flightcraft");

	//public static final
	
	@Override
	public void onInitialize() {

		registerCommands();

		ServerPlayNetworking.registerGlobalReceiver(gamemodePacket,(server, player, handler, buf, responseSender) -> {
			int gamemode = buf.readInt();
			if(!player.hasPermissionLevel(2)){
				player.sendMessage(Text.literal("No permission!").formatted(Formatting.BOLD).formatted(Formatting.RED), true);
				return;
			}
			server.execute(()->{
				switch (gamemode) {
					case 0 -> player.changeGameMode(GameMode.SURVIVAL);
					case 1 -> player.changeGameMode(GameMode.CREATIVE);
					case 2 -> player.changeGameMode(GameMode.ADVENTURE);
					case 3 -> player.changeGameMode(GameMode.SPECTATOR);
					default -> {}
				}
			});
		});


	}

	private static void registerCommands(){
		CommandRegistrationCallback.EVENT.register(OpenCommand::register);

		CommandRegistrationCallback.EVENT.register(FlyCommand::register);
		CommandRegistrationCallback.EVENT.register(GodCommand::register);
		CommandRegistrationCallback.EVENT.register(TransferCommand::register);
		CommandRegistrationCallback.EVENT.register(GetIpCommand::register);

	}
}