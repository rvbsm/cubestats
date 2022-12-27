package dev.rvbsm.cubestats.api.block;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;

public class BlockPlaceCallback {
    public static final Event<Place> EVENT = EventFactory.createArrayBacked(Place.class, (listeners) -> (player, stack) -> {
        for (Place event : listeners)
            event.blockPlace(player, stack);
    });

    @FunctionalInterface
    public interface Place {
        void blockPlace(PlayerEntity player, Block block);
    }
}
