package dev.rvbsm.cubestats;

import dev.rvbsm.cubestats.api.block.BlockBreakCallback;
import dev.rvbsm.cubestats.api.block.BlockPlaceCallback;
import dev.rvbsm.cubestats.api.entity.*;
import dev.rvbsm.cubestats.api.player.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBException;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CubeStatsMod implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cubestats");
    protected Properties config = new Properties();
    protected InfluxDB influxDB;

    @Override
    public void onInitialize() {
        initializeConfig();

        initializeInfluxDB();

        initializeEvents();

        LOGGER.info("Initialized mod");
    }

    private void initializeConfig() {
        final File configPath = FabricLoader.getInstance().getConfigDir().resolve("cubestats.properties").toFile();

        try {
            if (configPath.exists()) {
                InputStream input = new FileInputStream(configPath);
                config.load(input);
            } else {
                config.setProperty("db.host", "http://0.0.0.0:8086");
                config.setProperty("db.user", "root");
                config.setProperty("db.pass", "root");
                config.setProperty("db.name", "cubestats");

                OutputStream output = new FileOutputStream(configPath);
                config.store(output, null);
            }
        } catch (IOException e) {
            LOGGER.warn("Error while initializing config");
            e.printStackTrace();
        }
    }

    private void initializeInfluxDB() {
        final String host = config.getProperty("db.host");
        final String user = config.getProperty("db.user");
        final String pass = config.getProperty("db.pass");
        final String name = config.getProperty("db.name");

        influxDB = InfluxDBFactory.connect(host, user, pass);
        if (!influxDB.ping().isGood()) throw new InfluxDBException("Can't connect to a database, check your credentials.");

        influxDB.query(new Query("CREATE DATABASE " + name));
        influxDB.setDatabase(name);

        influxDB.enableBatch(
                BatchOptions.DEFAULTS
                        .threadFactory(runnable -> {
                            Thread thread = new Thread(runnable);
                            thread.setDaemon(true);
                            return thread;
                        })
        );

        Runtime.getRuntime().addShutdownHook(new Thread(influxDB::close));
    }

    private void initializeEvents() {
        BlockBreakCallback.EVENT.register((player, block) ->
                addPoint(Point.measurement("block_break")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("block", block.getName().getString())
                        .addField("block", block.getName().getString())
                        .build()));
        BlockPlaceCallback.EVENT.register((player, block) ->
                addPoint(Point.measurement("block_place")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("block", block.getName().getString())
                        .addField("block", block.getName().getString())
                        .build()));

        AnimalBreedCallback.EVENT.register((player, entity) ->
                addPoint(Point.measurement("animal_breed")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("entity", entity.getName().getString())
                        .addField("entity", entity.getName().getString())
                        .build()));
        AnimalTameCallback.EVENT.register((player, entity) ->
                addPoint(Point.measurement("animal_tame")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("entity", entity.getName().getString())
                        .addField("entity", entity.getName().getString())
                        .build()));
        EntityKillCallback.EVENT.register((player, entity) ->
                addPoint(Point.measurement("entity_kill")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("entity", entity.getName().getString())
                        .addField("entity", entity.getName().getString())
                        .build()));
        EntityLeashCallback.EVENT.register((player, entity) ->
                addPoint(Point.measurement("entity_leash")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("entity", entity.getName().getString())
                        .addField("entity", entity.getName().getString())
                        .build()));
        SheepDyeCallback.EVENT.register((player, color) ->
                addPoint(Point.measurement("sheep_dye")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("entity", StringUtils.capitalize(color.getName()))
                        .addField("entity", StringUtils.capitalize(color.getName()))
                        .build()));
        SheepShearCallback.EVENT.register((player, color) ->
                addPoint(Point.measurement("sheep_shear")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("entity", StringUtils.capitalize(color.getName()))
                        .addField("entity", StringUtils.capitalize(color.getName()))
                        .build()));

        PlayerCraftCallback.EVENT.register((player, stack) -> {
            for (int i = stack.getCount(); i > 0; i--)
                addPoint(Point.measurement("player_craft")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("item", stack.getName().getString())
                        .addField("item", stack.getName().getString())
                        .build());
        });
        PlayerDeathCallback.EVENT.register((player, source) ->
                addPoint(Point.measurement("player_death")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("source", source.getName())
                        .addField("source", source.getName())
                        .build()));
        PlayerEatCallback.EVENT.register((player, stack) ->
                addPoint(Point.measurement("player_eat")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("food", stack.getName().getString())
                        .addField("food", stack.getName().getString())
                        .build()));
        PlayerFishCallback.EVENT.register((player, stack) ->
                addPoint(Point.measurement("player_fish")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("item", stack.getName().getString())
                        .addField("item", stack.getName().getString())
                        .build()));
        PlayerGainXpCallback.EVENT.register((player, xp) ->
                addPoint(Point.measurement("player_xp")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .addField("amount", xp)
                        .build()));
        PlayerLoadEvents.CONNECT.register((player) ->
                addPoint(Point.measurement("player_activity")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("activity", "connected")
                        .addField("activity", "connected")
                        .build()));
        PlayerLoadEvents.DISCONNECT.register((player) ->
                addPoint(Point.measurement("player_activity")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("activity", "disconnected")
                        .addField("activity", "disconnected")
                        .build()));
        PlayerWorldChangeCallback.EVENT.register((player, destination) ->
                addPoint(Point.measurement("player_dim")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("world", destination.effects().toString())
                        .addField("world", destination.effects().toString())
                        .build()));
        RaidEvents.DEFEAT.register((player) ->
                addPoint(Point.measurement("player_raid")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("status", "defeat")
                        .addField("status", "defeat")
                        .build()));
        RaidEvents.VICTORY.register((player) ->
                addPoint(Point.measurement("block_place")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("status", "victory")
                        .addField("status", "victory")
                        .build()));
        VillagerTradeCallback.EVENT.register((player, villager) ->
                addPoint(Point.measurement("block_place")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("player", player.getEntityName())
                        .tag("uuid", player.getUuidAsString())
                        .tag("villager", villager.getVillagerData().getProfession().toString())
                        .addField("villager", villager.getVillagerData().getProfession().toString())
                        .build()));
    }

    protected void addPoint(Point point) {
        influxDB.write(point);
    }
}
