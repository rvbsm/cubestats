package dev.rvbsm.blockstats.mixin;

import dev.rvbsm.blockstats.event.player.MobEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AllayEntity.class)
public class AllayEntityMixin {

    // TODO: 12/23/2022 double invoke
    // TODO: 12/23/2022 called from new allay too?
    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AllayEntity;duplicate()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void breedAllay(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, ItemStack itemStack, ItemStack itemStack2) {
        final World world = player.getWorld();
        final AllayEntity dummy = EntityType.ALLAY.create(world);

        MobEvent.BREED.invoker().animalBreed(world, player, dummy);
    }
}
