package dev.rvbsm.cubestats.mixin.entity.passive;

import dev.rvbsm.cubestats.event.player.entity.sheep.ShearEvent;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin {

    @Shadow
    public abstract DyeColor getColor();

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/SheepEntity;sheared(Lnet/minecraft/sound/SoundCategory;)V"), method = "interactMob", locals = LocalCapture.CAPTURE_FAILHARD)
    private void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ShearEvent.SHEAR.invoker().sheepShear(player, this.getColor());
    }
}
