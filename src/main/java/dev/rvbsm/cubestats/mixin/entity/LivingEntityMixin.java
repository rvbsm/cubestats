package dev.rvbsm.cubestats.mixin.entity;

import dev.rvbsm.cubestats.event.player.DeathEvent;
import dev.rvbsm.cubestats.event.player.entity.KillEvent;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
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

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;onDeath(Lnet/minecraft/entity/damage/DamageSource;)V"),
            method = "damage")
    private void damage(LivingEntity instance, DamageSource damageSource) {
        if (instance instanceof PlayerEntity player)
            DeathEvent.DEATH.invoker().playerDeath(player, damageSource);
        instance.onDeath(damageSource);
    }
}
