package dev.rvbsm.blockstats.mixin;

import dev.rvbsm.blockstats.event.player.BlockEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Shadow
    protected ServerWorld world;
    @Final
    @Shadow
    protected ServerPlayerEntity player;

    @Inject(method = "tryBreakBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void breakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir, BlockState state, BlockEntity entity, Block block) {
        if (!this.world.isClient())
            BlockEvent.BREAK.invoker().blockBreak(this.world, this.player, block.asItem().getDefaultStack());
    }
}
