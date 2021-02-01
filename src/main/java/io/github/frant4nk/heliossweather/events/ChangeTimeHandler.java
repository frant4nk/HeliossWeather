package io.github.frant4nk.heliossweather.events;

import io.github.frant4nk.heliossweather.HeliossWeather;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.world.storage.WorldProperties;

import java.util.Optional;
import java.util.function.Consumer;

public class ChangeTimeHandler implements Consumer<Task>
{
    private final HeliossWeather plugin = HeliossWeather.instance;

    private static long normalizeWorldTime(long ticks)
    {
        if(ticks < 0 || ticks > 23999)
        {
            ticks = ticks % 24000;
        }
        return ticks;
    }

    @Override
    public void accept(Task task)
    {
         Optional<WorldProperties> over_world = Sponge.getGame().getServer().getWorldProperties("world");
         long currentTime = normalizeWorldTime(over_world.get().getWorldTime());

         //night 13000 day 1000
    }
}
