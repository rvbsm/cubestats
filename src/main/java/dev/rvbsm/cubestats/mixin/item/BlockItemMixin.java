package dev.rvbsm.cubestats.mixin.item;

import dev.rvbsm.cubestats.api.block.BlockPlaceCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BlockItem.class)
public class BlockItemMixin {
    @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z",
            at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void placeBlock(ItemPlacementContext context, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        final PlayerEntity player = context.getPlayer();
        final Block block = Block.getBlockFromItem(context.getStack().getItem());

        BlockPlaceCallback.EVENT.invoker().blockPlace(player, block);
    }
}
