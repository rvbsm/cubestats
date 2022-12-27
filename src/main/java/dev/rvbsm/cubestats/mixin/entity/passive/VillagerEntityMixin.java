package dev.rvbsm.cubestats.mixin.entity.passive;

import dev.rvbsm.cubestats.api.player.VillagerTradeCallback;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {

    @Shadow
    private PlayerEntity lastCustomer;

    @Inject(at = @At(value = "TAIL"), method = "afterUsing", locals = LocalCapture.CAPTURE_FAILHARD)
    private void afterUsing(TradeOffer offer, CallbackInfo ci) {
        VillagerTradeCallback.EVENT.invoker().villagerTrade(this.lastCustomer, (VillagerEntity) (Object) this);
    }
}
