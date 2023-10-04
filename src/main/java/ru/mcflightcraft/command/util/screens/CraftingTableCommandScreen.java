package ru.mcflightcraft.command.util.screens;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class CraftingTableCommandScreen extends CraftingScreenHandler {
    public CraftingTableCommandScreen(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(syncId, playerInventory, context);
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

}
