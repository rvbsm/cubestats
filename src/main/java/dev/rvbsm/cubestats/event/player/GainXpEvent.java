package dev.rvbsm.cubestats.event.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

public class GainXpEvent {
    public static Event<GainXp> XP = EventFactory.createArrayBacked(GainXp.class, (listeners) -> (player, xp) -> {
        for (GainXp event : listeners)
            event.addXp(player, xp);
    });

    @FunctionalInterface
    public interface GainXp {
        void addXp(PlayerEntity player, int xp);
    }
}
