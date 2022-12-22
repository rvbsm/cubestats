package dev.rvbsm.blockstats.mixin;

import dev.rvbsm.blockstats.event.player.MobEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityAttachS2CPacket.class)
public class EntityAttachS2CPacketMixin {

    @Inject(method = "<init>(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;)V", at = @At("TAIL"))
    private void init(Entity entity, Entity player, CallbackInfo ci) {
        if (player != null)
            MobEvent.LEASH.invoker().animalLeash(entity.getWorld(), (PlayerEntity) player, entity);
    }
}
