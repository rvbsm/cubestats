package dev.rvbsm.cubestats.event.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class DeathEvent {
    public static Event<Death> DEATH = EventFactory.createArrayBacked(Death.class, (listeners) -> (player, source) -> {
        for (Death event : listeners)
            event.playerDeath(player, source);
    });

    @FunctionalInterface
    public interface Death {
        void playerDeath(PlayerEntity player, DamageSource source);
    }
}