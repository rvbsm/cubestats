package dev.rvbsm.cubestats.mixin.entity.player;

import dev.rvbsm.cubestats.event.player.EatEvent;
import dev.rvbsm.cubestats.event.player.GainXpEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(at = @At(value = "TAIL"), method = "addExperience", locals = LocalCapture.CAPTURE_FAILHARD)
    private void addExperience(int experience, CallbackInfo ci) {
        if (experience != 0) GainXpEvent.XP.invoker().addXp((PlayerEntity) (Object) this, experience);
    }

    @Inject(at = @At(value = "TAIL"), method = "eatFood", locals = LocalCapture.CAPTURE_FAILHARD)
    private void eatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        EatEvent.EAT.invoker().playerEat((PlayerEntity) (Object) this, stack);
    }
}
