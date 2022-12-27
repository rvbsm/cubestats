package dev.rvbsm.cubestats.api.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerGainXpCallback {
    public static final Event<GainXp> EVENT = EventFactory.createArrayBacked(GainXp.class, (listeners) -> (player, xp) -> {
        for (GainXp event : listeners)
            event.playerGainXp(player, xp);
    });

    @FunctionalInterface
    public interface GainXp {
        void playerGainXp(PlayerEntity player, int xp);
    }
}
