package dev.rvbsm.cubestats.mixin.entity.passive;

import dev.rvbsm.cubestats.event.player.entity.TameEvent;
import net.minecraft.advancement.criterion.TameAnimalCriterion;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TameableEntity.class)
public class TameableEntityMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/TameAnimalCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/entity/passive/AnimalEntity;)V"),
            method = "setOwner")
    private void setOwner(TameAnimalCriterion instance, ServerPlayerEntity player, AnimalEntity entity) {
        TameEvent.TAME.invoker().animalTame(player, entity);
        instance.trigger(player, entity);
    }
}
