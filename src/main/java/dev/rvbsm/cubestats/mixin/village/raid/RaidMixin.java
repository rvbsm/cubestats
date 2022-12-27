package dev.rvbsm.cubestats.mixin.village.raid;

import dev.rvbsm.cubestats.event.player.RaidEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.village.raid.Raid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Predicate;

@Mixin(Raid.class)
public abstract class RaidMixin {

    @Shadow
    @Final
    private ServerWorld world;

    @Shadow
    private int finishCooldown;

    @Shadow
    public abstract boolean isFinished();

    @Shadow
    public abstract boolean hasWon();

    @Shadow
    protected abstract Predicate<ServerPlayerEntity> isInRaidDistance();

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/ServerBossBar;setName(Lnet/minecraft/text/Text;)V"), method = "tick")
    private void tick(CallbackInfo ci) {
        if (this.isFinished() && this.finishCooldown == 20) {
            List<ServerPlayerEntity> list = this.world.getPlayers(this.isInRaidDistance());
            for (PlayerEntity player : list) {
                if (this.hasWon()) RaidEvent.VICTORY.invoker().raidVictory(player);
                else RaidEvent.DEFEAT.invoker().raidDefeat(player);
            }
        }
    }
}
