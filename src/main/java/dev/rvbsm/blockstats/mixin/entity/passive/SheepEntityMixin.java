package dev.rvbsm.blockstats.mixin.entity.passive;

import dev.rvbsm.blockstats.event.player.MobEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SheepEntity.class)
public class SheepEntityMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/SheepEntity;emitGameEvent(Lnet/minecraft/world/event/GameEvent;Lnet/minecraft/entity/Entity;)V"),
            method = "interactMob")
    void interactMob(SheepEntity instance, GameEvent gameEvent, Entity player) {
        final World world = player.getWorld();
        final DyeColor color = instance.getColor();

        if (!world.isClient()) MobEvent.SHEAR.invoker().sheepShear(world, (PlayerEntity) player, color);
    }
}
