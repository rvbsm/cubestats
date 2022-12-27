package dev.rvbsm.cubestats.api.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerDeathCallback {
    public static final Event<Death> EVENT = EventFactory.createArrayBacked(Death.class, (listeners) -> (player, source) -> {
        for (Death event : listeners)
            event.playerDeath(player, source);
    });

    @FunctionalInterface
    public interface Death {
        void playerDeath(PlayerEntity player, DamageSource source);
    }
}