package dev.rvbsm.cubestats.mixin.server.world;

import dev.rvbsm.cubestats.api.player.PlayerWorldChangeCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Inject(at = @At(value = "TAIL"), method = "onPlayerChangeDimension")
    private void onDimensionChanged(ServerPlayerEntity player, CallbackInfo ci) {
        PlayerWorldChangeCallback.EVENT.invoker().playerWorldChange(player, player.getWorld().getDimension());
    }
}
