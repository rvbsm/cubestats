package dev.rvbsm.cubestats.api.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;

public class VillagerTradeCallback {
    public static final Event<Trade> EVENT = EventFactory.createArrayBacked(Trade.class, (listeners) -> (player, villager) -> {
        for (Trade event : listeners)
            event.villagerTrade(player, villager);
    });

    @FunctionalInterface
    public interface Trade {
        void villagerTrade(PlayerEntity player, VillagerEntity villager);
    }
}
