package io.github.frant4nk.heliossweather.vote;

import io.github.frant4nk.heliossweather.HeliossWeather;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.world.World;

import javax.swing.text.TextAction;

public class WeatherVote
{
    private final HeliossWeather plugin = HeliossWeather.instance;

    public WeatherVote(World over_world)
    {
        MessageChannel overworldChannel = over_world.getMessageChannel();
        MessageChannel globalChannel = Sponge.getServer().getBroadcastChannel();

        Text prefix = Text.builder("[WEATHER]").color(TextColors.DARK_RED).style(TextStyles.BOLD).build();
        Text title = prefix.concat(Text.builder(" Rain has started in overworld, do you want clear skies?")
                                .color(TextColors.BLUE).style(TextStyles.RESET).build());
        Text options = title.concat(Text.builder("\n\n    - Clear Skies")
                                .color(TextColors.RESET).style(TextStyles.RESET)
                                .onClick(TextActions.runCommand("command")).build());
        Text options2 = options.concat(Text.builder("\n    - Rain")
                                .color(TextColors.RESET).style(TextStyles.RESET)
                                .onClick(TextActions.runCommand("command")).build());

        plugin.getLogger().info("Vote started in overworld");

        boolean broadcastAll = plugin.getConfig().getNode("broadcast", "broadcast_in_all_worlds").getBoolean();
        if(broadcastAll)
        {
            globalChannel.send(options2);
        }
        else
        {
            overworldChannel.send(options2);
        }
    }
}
