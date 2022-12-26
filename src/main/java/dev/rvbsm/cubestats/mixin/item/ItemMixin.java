package dev.rvbsm.cubestats.mixin.item;

import dev.rvbsm.cubestats.event.player.CraftEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "onCraft", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onCraft(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {
        if (!world.isClient()) CraftEvent.CRAFT.invoker().playerCraft(player, stack);
    }
}
