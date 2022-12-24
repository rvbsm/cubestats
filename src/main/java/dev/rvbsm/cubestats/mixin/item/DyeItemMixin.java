package dev.rvbsm.cubestats.mixin.item;

import dev.rvbsm.cubestats.event.player.MobEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DyeItem.class)
public class DyeItemMixin {

    @Shadow
    @Final
    private DyeColor color;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/SheepEntity;setColor(Lnet/minecraft/util/DyeColor;)V"),
            method = "useOnEntity")
    void useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        final World world = player.getWorld();

        if (!world.isClient()) MobEvent.DYE.invoker().sheepDye(world, player, this.color);
    }
}
