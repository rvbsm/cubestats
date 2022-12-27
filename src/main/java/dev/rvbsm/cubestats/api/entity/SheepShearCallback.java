package dev.rvbsm.cubestats.api.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DyeColor;

public class SheepShearCallback {
    public static final Event<Shear> EVENT = EventFactory.createArrayBacked(Shear.class, (listeners) -> (player, color) -> {
        for (Shear event : listeners)
            event.sheepShear(player, color);
    });

    @FunctionalInterface
    public interface Shear {
        void sheepShear(PlayerEntity player, DyeColor color);
    }
}
