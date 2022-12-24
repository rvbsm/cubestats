package dev.rvbsm.cubestats.mixin.entity;

import dev.rvbsm.cubestats.event.player.PlayerEvent;
import dev.rvbsm.cubestats.event.player.entity.KillEvent;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setPose(Lnet/minecraft/entity/EntityPose;)V"),
            method = "onDeath")
    private void onDeath(LivingEntity entity, EntityPose entityPose) {
        if (entity.getAttacker() instanceof final PlayerEntity player) {
            KillEvent.KILL.invoker().mobKill(player, entity);
            entity.setPose(entityPose);
        }
    }
}
