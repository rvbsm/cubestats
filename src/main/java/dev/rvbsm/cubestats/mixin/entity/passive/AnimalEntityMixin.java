package dev.rvbsm.cubestats.mixin.entity.passive;

import dev.rvbsm.cubestats.event.player.MobEvent;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin {
    @Shadow
    private UUID lovingPlayer;

    @Inject(method = "breed", at = @At("TAIL"))
    private void breedEntity(ServerWorld world, AnimalEntity other, CallbackInfo ci) {
        final PlayerEntity player = (PlayerEntity) world.getEntity(this.lovingPlayer);

        if (!world.isClient()) MobEvent.BREED.invoker().animalBreed(world, player, other);
    }
}
