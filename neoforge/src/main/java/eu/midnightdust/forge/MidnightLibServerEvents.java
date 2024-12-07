package eu.midnightdust.forge;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import eu.midnightdust.lib.config.AutoCommand;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import net.minecraft.commands.CommandSourceStack;

@EventBusSubscriber(modid = "midnightlib", value = Dist.DEDICATED_SERVER)
public class MidnightLibServerEvents {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        for (LiteralArgumentBuilder<CommandSourceStack> command : AutoCommand.commands){
            event.getDispatcher().register(command);
        }
    }
}
