package dev.rvbsm.cubestats.event.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

public class MobEvent {
    public static final Event<Breed> BREED = EventFactory.createArrayBacked(Breed.class, (listeners) -> (world, player, entity) -> {
        for (Breed event : listeners)
            event.animalBreed(world, player, entity);
    });
    public static final Event<Leash> LEASH = EventFactory.createArrayBacked(Leash.class, (listeners) -> (world, player, entity) -> {
        for (Leash event : listeners)
            event.animalLeash(world, player, entity);
    });
    public static final Event<Tame> TAME = EventFactory.createArrayBacked(Tame.class, (listeners) -> (world, player, entity) -> {
        for (Tame event : listeners)
            event.animalTame(world, player, entity);
    });
    public static final Event<Kill> KILL = EventFactory.createArrayBacked(Kill.class, (listeners) -> (world, player, entity) -> {
        for (Kill event : listeners)
            event.mobKill(world, player, entity);
    });
    public static final Event<Dye> DYE = EventFactory.createArrayBacked(Dye.class, (listeners) -> (world, player, color) -> {
        for (Dye event : listeners)
            event.sheepDye(world, player, color);
    });
    public static final Event<Shear> SHEAR = EventFactory.createArrayBacked(Shear.class, (listeners) -> (world, player, color) -> {
        for (Shear event : listeners)
            event.sheepShear(world, player, color);
    });

    @FunctionalInterface
    public interface Breed {
        void animalBreed(World world, PlayerEntity player, Entity entity);
    }

    @FunctionalInterface
    public interface Leash {
        void animalLeash(World world, PlayerEntity player, Entity entity);
    }

    @FunctionalInterface
    public interface Tame {
        void animalTame(World world, PlayerEntity player, Entity entity);
    }

    @FunctionalInterface
    public interface Kill {
        void mobKill(World world, PlayerEntity player, Entity entity);
    }

    @FunctionalInterface
    public interface Dye {
        void sheepDye(World world, PlayerEntity player, DyeColor color);
    }

    @FunctionalInterface
    public interface Shear {
        void sheepShear(World world, PlayerEntity player, DyeColor color);
    }
}
