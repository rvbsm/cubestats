package dev.rvbsm.cubestats.mixin.entity.mob;

import dev.rvbsm.cubestats.event.player.entity.LeashEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobEntity.class)
public class MobEntityMixin {

    @Shadow
    private @Nullable Entity holdingEntity;

    @Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/MobEntity;holdingEntity:Lnet/minecraft/entity/Entity;", opcode = Opcodes.PUTFIELD),
            method = "attachLeash")
    private void attachLeash(MobEntity mobEntity, Entity entity) {
        if (entity instanceof final PlayerEntity player) LeashEvent.LEASH.invoker().animalLeash(player, mobEntity);
        this.holdingEntity = entity;
    }
}
