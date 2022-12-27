package dev.rvbsm.cubestats.mixin.entity.mob;

import dev.rvbsm.cubestats.api.entity.EntityLeashCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MobEntity.class)
public class MobEntityMixin {

    @Inject(at = @At(value = "TAIL"), method = "attachLeash", locals = LocalCapture.CAPTURE_FAILHARD)
    private void attachLeash(Entity entity, boolean sendPacket, CallbackInfo ci) {
        if (entity instanceof final PlayerEntity player)
            EntityLeashCallback.EVENT.invoker().entityLeash(player, (MobEntity) (Object) this);
    }
}
