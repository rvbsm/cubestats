package dev.rvbsm.cubestats.api.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class EntityLeashCallback {
    public static final Event<Leash> EVENT = EventFactory.createArrayBacked(Leash.class, (listeners) -> (player, entity) -> {
        for (Leash event : listeners)
            event.entityLeash(player, entity);
    });

    @FunctionalInterface
    public interface Leash {
        void entityLeash(PlayerEntity player, Entity entity);
    }
}
