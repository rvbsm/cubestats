package dev.rvbsm.cubestats.api.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class PlayerEatCallback {
    public static final Event<Eat> EVENT = EventFactory.createArrayBacked(Eat.class, (listeners) -> (player, stack) -> {
        for (Eat event : listeners)
            event.playerEat(player, stack);
    });

    @FunctionalInterface
    public interface Eat {
        void playerEat(PlayerEntity player, ItemStack stack);
    }
}
