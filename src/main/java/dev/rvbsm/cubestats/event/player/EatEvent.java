package dev.rvbsm.cubestats.event.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class EatEvent {
    public static Event<Eat> EAT = EventFactory.createArrayBacked(Eat.class, (listeners) -> (player, stack) -> {
        for (Eat event : listeners)
            event.playerEat(player, stack);
    });

    @FunctionalInterface
    public interface Eat {
        void playerEat(PlayerEntity player, ItemStack stack);
    }
}
