package ru.mcflightcraft.command.util.screens;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class GrindstoneCommandScreen extends GrindstoneScreenHandler {
    public GrindstoneCommandScreen(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(syncId, playerInventory, context);
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
