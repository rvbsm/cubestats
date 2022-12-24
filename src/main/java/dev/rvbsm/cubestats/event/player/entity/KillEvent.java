package dev.rvbsm.cubestats.event.player.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class KillEvent {
    public static final Event<Kill> KILL = EventFactory.createArrayBacked(Kill.class, (listeners) -> (player, entity) -> {
        for (Kill event : listeners)
            event.mobKill(player, entity);
    });

    @FunctionalInterface
    public interface Kill {
        void mobKill(PlayerEntity player, Entity entity);
    }
}
