package dev.rvbsm.cubestats.mixin.entity.player;

import dev.rvbsm.cubestats.event.player.EatEvent;
import dev.rvbsm.cubestats.event.player.GainXpEvent;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addScore(I)V"),
            method = "addExperience")
    private void addExperience(PlayerEntity player, int experience) {
        if (experience != 0) {
            GainXpEvent.XP.invoker().addXp(player, experience);
            player.addScore(experience);
        }
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/ConsumeItemCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/item/ItemStack;)V"),
            method = "eatFood")
    private void eatFood(ConsumeItemCriterion criterion, ServerPlayerEntity player, ItemStack stack) {
        EatEvent.EAT.invoker().playerEat(player, stack);
        criterion.trigger(player, stack);
    }
}
