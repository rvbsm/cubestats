package dev.rvbsm.blockstats.event.player.block;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;

public class BlockPlaceEvent {
    public static final Event<Place> PLACE = EventFactory.createArrayBacked(Place.class, (listeners) -> (player, stack) -> {
        for (Place event : listeners)
            event.blockPlace(player, stack);
    });
    @FunctionalInterface
    public interface Place {
        void blockPlace(PlayerEntity player, Block block);
    }
}
