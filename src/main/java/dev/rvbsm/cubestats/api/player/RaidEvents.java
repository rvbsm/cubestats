package dev.rvbsm.cubestats.api.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

public class RaidEvents {
    public static final Event<Victory> VICTORY = EventFactory.createArrayBacked(Victory.class, (listeners) -> (player) -> {
        for (Victory event : listeners)
            event.raidVictory(player);
    });
    public static final Event<Defeat> DEFEAT = EventFactory.createArrayBacked(Defeat.class, (listeners) -> (player) -> {
        for (Defeat event : listeners)
            event.raidDefeat(player);
    });

    @FunctionalInterface
    public interface Victory {
        void raidVictory(PlayerEntity player);
    }

    @FunctionalInterface
    public interface Defeat {
        void raidDefeat(PlayerEntity player);
    }
}
