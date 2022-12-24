package dev.rvbsm.cubestats.event.player.entity.sheep;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DyeColor;

public class DyeEvent {
    public static final Event<Dye> DYE = EventFactory.createArrayBacked(Dye.class, (listeners) -> (player, color) -> {
        for (Dye event : listeners)
            event.sheepDye(player, color);
    });

    @FunctionalInterface
    public interface Dye {
        void sheepDye(PlayerEntity player, DyeColor color);
    }
}
