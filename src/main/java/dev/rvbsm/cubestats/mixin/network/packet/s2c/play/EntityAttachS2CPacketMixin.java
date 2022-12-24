package dev.rvbsm.cubestats.mixin.network.packet.s2c.play;

import dev.rvbsm.cubestats.event.player.entity.LeashEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityAttachS2CPacket.class)
public class EntityAttachS2CPacketMixin {

    @Inject(method = "<init>(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"))
    private static void init(Entity entity, Entity player, CallbackInfo ci) {
        if (player != null) {
            final World world = player.getWorld();

            if (!world.isClient()) LeashEvent.LEASH.invoker().animalLeash((PlayerEntity) player, entity);
        }
    }
}
