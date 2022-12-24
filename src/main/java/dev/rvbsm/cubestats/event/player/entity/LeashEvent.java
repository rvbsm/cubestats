package dev.rvbsm.cubestats.event.player.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class LeashEvent {
    public static final Event<Leash> LEASH = EventFactory.createArrayBacked(Leash.class, (listeners) -> (player, entity) -> {
        for (Leash event : listeners)
            event.animalLeash(player, entity);
    });

    @FunctionalInterface
    public interface Leash {
        void animalLeash(PlayerEntity player, Entity entity);
    }
}
