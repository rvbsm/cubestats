package dev.rvbsm.cubestats.event.player.block;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;

public class BlockBreakEvent {
    public static final Event<Break> BREAK = EventFactory.createArrayBacked(Break.class, (listeners) -> (player, stack) -> {
        for (Break event : listeners)
            event.blockBreak(player, stack);
    });

    @FunctionalInterface
    public interface Break {
        void blockBreak(PlayerEntity player, Block block);
    }
}
