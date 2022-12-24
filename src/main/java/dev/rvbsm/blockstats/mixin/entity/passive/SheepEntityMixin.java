package dev.rvbsm.blockstats.mixin.entity.passive;

import dev.rvbsm.blockstats.event.player.MobEvent;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin {

    @Shadow
    public abstract DyeColor getColor();


    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/SheepEntity;sheared(Lnet/minecraft/sound/SoundCategory;)V"),
            method = "interactMob")
    void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        final World world = player.getWorld();
        final DyeColor color = this.getColor();

        if (!world.isClient()) MobEvent.SHEAR.invoker().sheepShear(world, (PlayerEntity) player, color);
    }
}
