package dev.rvbsm.cubestats.mixin.server.network;

import dev.rvbsm.cubestats.api.player.PlayerLoadEvents;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @Inject(at = @At(value = "TAIL"), method = "<init>")
    private void init(MinecraftServer server, ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        PlayerLoadEvents.CONNECT.invoker().playerConnect(player);
    }
}
