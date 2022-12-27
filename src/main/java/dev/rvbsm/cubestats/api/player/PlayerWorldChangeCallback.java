package dev.rvbsm.cubestats.api.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.dimension.DimensionType;

public class PlayerWorldChangeCallback {
    public static final Event<ChangeDimension> EVENT = EventFactory.createArrayBacked(ChangeDimension.class, (listeners) -> (player, dimension) -> {
        for (ChangeDimension event : listeners)
            event.playerWorldChange(player, dimension);
    });

    @FunctionalInterface
    public interface ChangeDimension {
        void playerWorldChange(PlayerEntity player, DimensionType dimension);
    }
}
