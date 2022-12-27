package dev.rvbsm.cubestats.api.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerLoadEvents {
    public static final Event<Connect> CONNECT = EventFactory.createArrayBacked(Connect.class, (listeners) -> (player) -> {
        for (Connect event : listeners)
            event.playerConnect(player);
    });
    public static final Event<Disconnect> DISCONNECT = EventFactory.createArrayBacked(Disconnect.class, (listeners) -> (player) -> {
        for (Disconnect event : listeners)
            event.playerDisconnect(player);
    });

    @FunctionalInterface
    public interface Connect {
        void playerConnect(PlayerEntity player);
    }

    @FunctionalInterface
    public interface Disconnect {
        void playerDisconnect(PlayerEntity player);
    }
}
