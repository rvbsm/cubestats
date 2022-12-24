package dev.rvbsm.cubestats.event.player.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class TameEvent {
    public static final Event<Tame> TAME = EventFactory.createArrayBacked(Tame.class, (listeners) -> (player, entity) -> {
        for (Tame event : listeners)
            event.animalTame(player, entity);
    });

    @FunctionalInterface
    public interface Tame {
        void animalTame(PlayerEntity player, Entity entity);
    }
}
