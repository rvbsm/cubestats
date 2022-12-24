package dev.rvbsm.cubestats.event.player.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class BreedEvent {
    public static final Event<Breed> BREED = EventFactory.createArrayBacked(Breed.class, (listeners) -> (player, entity) -> {
        for (Breed event : listeners)
            event.animalBreed(player, entity);
    });

    @FunctionalInterface
    public interface Breed {
        void animalBreed(PlayerEntity player, Entity entity);
    }
}
