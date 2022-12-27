package dev.rvbsm.cubestats.mixin.server.network;

import dev.rvbsm.cubestats.event.player.ConnectionEvent;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(at = @At(value = "TAIL"), method = "onDisconnect")
    private void onDisconnect(CallbackInfo ci) {
        ConnectionEvent.DISCONNECT.invoker().playerDisconnect((ServerPlayerEntity) (Object) this);
    }
}
