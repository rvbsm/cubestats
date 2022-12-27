package dev.rvbsm.cubestats.mixin.entity.passive;

import dev.rvbsm.cubestats.event.player.entity.TameEvent;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TameableEntity.class)
public class TameableEntityMixin {

    @Inject(at = @At(value = "TAIL"), method = "setOwner", locals = LocalCapture.CAPTURE_FAILHARD)
    private void setOwner(PlayerEntity player, CallbackInfo ci) {
        TameEvent.TAME.invoker().animalTame(player, (TameableEntity) (Object) this);
    }
}
