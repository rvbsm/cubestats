package dev.rvbsm.blockstats.event.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class BlockEvent {
    public static final Event<Break> BREAK = EventFactory.createArrayBacked(Break.class, (listeners) -> (world, player, stack) -> {
        for (Break event : listeners)
            event.blockBreak(world, player, stack);
    });

    public static final Event<Place> PLACE = EventFactory.createArrayBacked(Place.class, (listeners) -> (world, player, stack) -> {
        for (Place event : listeners)
            event.blockPlace(world, player, stack);
    });

    @FunctionalInterface
    public interface Break {
        void blockBreak(World world, PlayerEntity player, ItemStack stack);
    }

    @FunctionalInterface
    public interface Place {
        void blockPlace(World world, PlayerEntity player, ItemStack stack);
    }
}
