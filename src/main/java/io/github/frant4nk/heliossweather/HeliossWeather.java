package io.github.frant4nk.heliossweather;

import com.google.inject.Inject;
import io.github.frant4nk.heliossweather.commands.HeliossWeatherReload;
import io.github.frant4nk.heliossweather.events.ChangeTimeHandler;
import io.github.frant4nk.heliossweather.events.ChangeWeatherHandler;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Plugin(
        id = "heliossweather",
        name = "HeliossWeather",
        description = "Plugin to let players manage the weather",
        authors = {
                "FranT4NK"
        }
)
public class HeliossWeather {

    public static HeliossWeather instance;

    private final Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path configPath;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    ConfigurationNode rootNode;


    @Inject
    public HeliossWeather(Logger logger)
    {
        this.logger = logger;
        instance = this;
    }

    @Listener
    public void preInit(GamePreInitializationEvent e) throws IOException
    {
        createConfig();
    }

    @Listener
    public void onInit(GameInitializationEvent e)
    {
        registerCommands();
        registerEvents();
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event)
    {

    }

    @Listener
    public void onGameInit(GameInitializationEvent event)
    {
        Task timeTask = Task.builder()
                .execute(new ChangeTimeHandler())
                .interval(1, TimeUnit.SECONDS)
                .submit(this);
    }

    private void registerCommands()
    {
        getLogger().info("Registering commands...");
        Sponge.getCommandManager().register(this, HeliossWeatherReload.getCommand(), "hwreload");
    }

    private void registerEvents()
    {
        getLogger().info("Registering events...");
        Sponge.getEventManager().registerListeners(this, new ChangeWeatherHandler());
    }

    public Logger getLogger()
    {
        return logger;
    }

    private void createConfig() throws IOException
    {
        Sponge.getAssetManager().getAsset(this, "heliossweather.conf").get().copyToFile(configPath, false, true);
        loader = HoconConfigurationLoader.builder().setPath(configPath).build();
        rootNode = loader.load();
    }

    public void loadConfig()
    {
        try
        {
            rootNode = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveConfig()
    {
        try
        {
            loader.save(rootNode);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ConfigurationNode getConfig()
    {
        return rootNode;
    }
}
