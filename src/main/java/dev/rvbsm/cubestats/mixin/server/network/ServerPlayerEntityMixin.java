package dev.rvbsm.cubestats.mixin.server.network;

import dev.rvbsm.cubestats.event.player.ConnectionEvent;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;removeAllPassengers()V"),
            method = "onDisconnect")
    private void onDisconnect(ServerPlayerEntity player) {
        ConnectionEvent.DISCONNECT.invoker().playerDisconnect(player);
        player.removeAllPassengers();
    }
}
