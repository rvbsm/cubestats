package dev.rvbsm.cubestats.mixin.item;

import dev.rvbsm.cubestats.event.player.block.BlockPlaceEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.world.World;
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
        final World world = context.getWorld();
        final PlayerEntity player = context.getPlayer();
        final Block block = Block.getBlockFromItem(context.getStack().getItem());

        if (!world.isClient()) BlockPlaceEvent.PLACE.invoker().blockPlace(player, block);
    }
}
