package dev.rvbsm.cubestats.mixin.entity.passive;

import dev.rvbsm.cubestats.api.entity.AnimalBreedCallback;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AllayEntity.class)
public class AllayEntityMixin {

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AllayEntity;duplicate()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void breedAllay(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, ItemStack itemStack, ItemStack itemStack2) {
        AnimalBreedCallback.EVENT.invoker().animalBreed(player, (AllayEntity) (Object) this);
    }
}
