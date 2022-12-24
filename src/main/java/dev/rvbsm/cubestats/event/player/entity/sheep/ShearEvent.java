package dev.rvbsm.cubestats.event.player.entity.sheep;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DyeColor;

public class ShearEvent {
    public static final Event<Shear> SHEAR = EventFactory.createArrayBacked(Shear.class, (listeners) -> (player, color) -> {
        for (Shear event : listeners)
            event.sheepShear(player, color);
    });

    @FunctionalInterface
    public interface Shear {
        void sheepShear(PlayerEntity player, DyeColor color);
    }
}
