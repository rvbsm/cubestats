package dev.rvbsm.cubestats.mixin.entity;

import dev.rvbsm.cubestats.event.player.DeathEvent;
import dev.rvbsm.cubestats.event.player.entity.KillEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {LivingEntity.class, ServerPlayerEntity.class})
public abstract class LivingEntityMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageTracker;update()V"),
            method = "onDeath")
    private void onDeath(DamageSource source, CallbackInfo ci) {
        if (source.getSource() instanceof final PlayerEntity player) {
            KillEvent.KILL.invoker().mobKill(player, (LivingEntity) (Object) this);
        }

        if ((LivingEntity) (Object) this instanceof final PlayerEntity player) {
            DeathEvent.DEATH.invoker().playerDeath(player, source);
        }
    }
}
