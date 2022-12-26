package dev.rvbsm.cubestats.event.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.dimension.DimensionType;

public class DimensionEvent {
    public static Event<ChangeDimension> DIMENSION = EventFactory.createArrayBacked(ChangeDimension.class, (listeners) -> (player, dimension) -> {
        for (ChangeDimension event : listeners)
            event.playerTeleport(player, dimension);
    });

    @FunctionalInterface
    public interface ChangeDimension {
        void playerTeleport(PlayerEntity player, DimensionType dimension);
    }
}
