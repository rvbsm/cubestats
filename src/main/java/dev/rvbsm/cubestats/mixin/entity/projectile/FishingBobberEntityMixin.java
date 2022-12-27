package dev.rvbsm.cubestats.mixin.entity.projectile;

import dev.rvbsm.cubestats.api.player.PlayerFishCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin {

    @Shadow
    @Nullable
    public abstract PlayerEntity getPlayerOwner();

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"),
            method = "use")
    private boolean use(World world, Entity entity) {
        final PlayerEntity player = this.getPlayerOwner();


        if (player != null && entity instanceof final ItemEntity itemEntity)
            PlayerFishCallback.EVENT.invoker().playerFish(player, itemEntity.getStack());

        return world.spawnEntity(entity);
    }
}
