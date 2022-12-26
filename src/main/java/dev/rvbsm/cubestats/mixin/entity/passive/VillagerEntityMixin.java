package dev.rvbsm.cubestats.mixin.entity.passive;

import dev.rvbsm.cubestats.event.player.TradeEvent;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {

    @Shadow
    private @Nullable PlayerEntity lastCustomer;

    @Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/entity/passive/VillagerEntity;lastCustomer:Lnet/minecraft/entity/player/PlayerEntity;", opcode = Opcodes.PUTFIELD),
            method = "afterUsing")
    private void afterUsing(VillagerEntity villager, PlayerEntity player) {
        TradeEvent.TRADE.invoker().playerTrade(player, villager);
        this.lastCustomer = player;
    }
}
