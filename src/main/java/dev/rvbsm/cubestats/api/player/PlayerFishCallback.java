package dev.rvbsm.cubestats.api.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class PlayerFishCallback {
    public static final Event<Fish> EVENT = EventFactory.createArrayBacked(Fish.class, (listeners) -> (player, stack) -> {
        for (Fish event : listeners)
            event.playerFish(player, stack);
    });

    @FunctionalInterface
    public interface Fish {
        void playerFish(PlayerEntity player, ItemStack stack);
    }
}
