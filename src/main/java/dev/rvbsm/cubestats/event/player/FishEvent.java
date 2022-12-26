package dev.rvbsm.cubestats.event.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class FishEvent {
    public static Event<Fish> FISH = EventFactory.createArrayBacked(Fish.class, (listeners) -> (player, stack) -> {
        for (Fish event : listeners)
            event.catchItem(player, stack);
    });

    @FunctionalInterface
    public interface Fish {
        void catchItem(PlayerEntity player, ItemStack stack);
    }
}
