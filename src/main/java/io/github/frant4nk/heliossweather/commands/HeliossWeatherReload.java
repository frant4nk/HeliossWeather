package io.github.frant4nk.heliossweather.commands;

import io.github.frant4nk.heliossweather.HeliossWeather;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class HeliossWeatherReload implements CommandExecutor
{
    private final HeliossWeather plugin = HeliossWeather.instance;

    public static CommandSpec getCommand()
    {
        return CommandSpec.builder()
                .description(Text.of("Reload the plugin config"))
                .permission("heliossweather.admin.reload")
                .executor(new HeliossWeatherReload())
                .build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
    {
        plugin.loadConfig();
        src.sendMessage(Text.of("Loaded config."));
        return CommandResult.success();
    }
}
