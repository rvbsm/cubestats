package dev.rvbsm.cubestats.api.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class EntityKillCallback {
    public static final Event<Kill> EVENT = EventFactory.createArrayBacked(Kill.class, (listeners) -> (player, entity) -> {
        for (Kill event : listeners)
            event.entityKill(player, entity);
    });

    @FunctionalInterface
    public interface Kill {
        void entityKill(PlayerEntity player, Entity entity);
    }
}
