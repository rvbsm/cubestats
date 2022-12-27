package dev.rvbsm.cubestats.mixin.entity.passive;

import dev.rvbsm.cubestats.api.entity.AnimalBreedCallback;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.UUID;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin {
    @Shadow
    private UUID lovingPlayer;

    @Inject(at = @At("TAIL"), method = "breed", locals = LocalCapture.CAPTURE_FAILHARD)
    private void breedEntity(ServerWorld world, AnimalEntity other, CallbackInfo ci) {
        final PlayerEntity player = (PlayerEntity) world.getEntity(this.lovingPlayer);

        AnimalBreedCallback.EVENT.invoker().animalBreed(player, other);
    }
}
