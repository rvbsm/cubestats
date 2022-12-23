package dev.rvbsm.blockstats.mixin;

import dev.rvbsm.blockstats.event.player.MobEvent;
import net.minecraft.advancement.criterion.TameAnimalCriterion;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TameableEntity.class)
public class TameableEntityMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/TameAnimalCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/entity/passive/AnimalEntity;)V"),
            method = "setOwner")
    private void setOwner(TameAnimalCriterion instance, ServerPlayerEntity player, AnimalEntity entity) {
        final World world = player.getWorld();
        MobEvent.TAME.invoker().animalTame(world, player, entity);
        instance.trigger(player, entity);
    }
}
