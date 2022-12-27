package dev.rvbsm.cubestats.api.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class AnimalBreedCallback {
    public static final Event<Breed> EVENT = EventFactory.createArrayBacked(Breed.class, (listeners) -> (player, entity) -> {
        for (Breed event : listeners)
            event.animalBreed(player, entity);
    });

    @FunctionalInterface
    public interface Breed {
        void animalBreed(PlayerEntity player, Entity entity);
    }
}
