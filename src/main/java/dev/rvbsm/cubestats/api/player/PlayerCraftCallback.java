package dev.rvbsm.cubestats.api.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class PlayerCraftCallback {
    public static final Event<Craft> EVENT = EventFactory.createArrayBacked(Craft.class, (listeners) -> (player, stack) -> {
        for (Craft event : listeners)
            event.playerCraft(player, stack);
    });

    @FunctionalInterface
    public interface Craft {
        void playerCraft(PlayerEntity player, ItemStack stack);
    }
}
