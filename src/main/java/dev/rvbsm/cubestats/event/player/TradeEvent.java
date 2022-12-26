package dev.rvbsm.cubestats.event.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;

public class TradeEvent {
    public static Event<Trade> TRADE = EventFactory.createArrayBacked(Trade.class, (listeners) -> (player, villager) -> {
        for (Trade event : listeners)
            event.playerTrade(player, villager);
    });

    @FunctionalInterface
    public interface Trade {
        void playerTrade(PlayerEntity player, VillagerEntity villager);
    }
}
