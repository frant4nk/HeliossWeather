package io.github.frant4nk.heliossweather.events;

import io.github.frant4nk.heliossweather.HeliossWeather;
import io.github.frant4nk.heliossweather.vote.WeatherVote;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.world.ChangeWorldWeatherEvent;
import org.spongepowered.api.world.weather.Weathers;

public class ChangeWeatherHandler
{
    private final HeliossWeather plugin = HeliossWeather.instance;

    @Listener
    public void onChangeWorldWeather(ChangeWorldWeatherEvent event)
    {
        if(event.getWeather() == Weathers.CLEAR)
        {
            return;
        }

        if(event.getTargetWorld().getName().equals("world"))
        {
            new WeatherVote(event.getTargetWorld());
        }
    }
}
