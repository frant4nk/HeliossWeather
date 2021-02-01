package io.github.frant4nk.heliossweather.commands;

import io.github.frant4nk.heliossweather.HeliossWeather;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class HeliossWeatherVote implements CommandExecutor
{
    private final HeliossWeather plugin = HeliossWeather.instance;

    public static CommandSpec getCommand()
    {
        return CommandSpec.builder()
                .description(Text.of("Vote for some option"))
                .permission("heliossweather.base.vote")
                .executor(new HeliossWeatherVote())
                .build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
    {
        return CommandResult.success();
    }
}
