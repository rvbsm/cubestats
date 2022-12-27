package dev.rvbsm.cubestats.api.block;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;

public class BlockBreakCallback {
    public static final Event<Break> EVENT = EventFactory.createArrayBacked(Break.class, (listeners) -> (player, stack) -> {
        for (Break event : listeners)
            event.blockBreak(player, stack);
    });

    @FunctionalInterface
    public interface Break {
        void blockBreak(PlayerEntity player, Block block);
    }
}
