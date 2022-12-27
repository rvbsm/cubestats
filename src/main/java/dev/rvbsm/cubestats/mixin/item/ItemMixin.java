package dev.rvbsm.cubestats.mixin.item;

import dev.rvbsm.cubestats.api.player.PlayerCraftCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AirBlockItem;
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

    // TODO: 12/28/2022 12:45 AM double call at shift-click on item
    @Inject(method = "onCraft", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onCraft(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {
        if (!(stack.getItem() instanceof AirBlockItem))
            PlayerCraftCallback.EVENT.invoker().playerCraft(player, stack);
    }
}
