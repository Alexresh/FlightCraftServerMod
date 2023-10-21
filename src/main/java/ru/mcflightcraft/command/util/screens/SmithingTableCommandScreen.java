package ru.mcflightcraft.command.util.screens;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SmithingScreenHandler;

public class SmithingTableCommandScreen extends SmithingScreenHandler {
    public SmithingTableCommandScreen(int syncId, PlayerInventory inventory, ScreenHandlerContext context) {
        super(syncId, inventory, context);
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
