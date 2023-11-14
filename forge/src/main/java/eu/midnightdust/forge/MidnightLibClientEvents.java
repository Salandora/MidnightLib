package eu.midnightdust.forge;

import eu.midnightdust.lib.config.MidnightConfig;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "midnightlib", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MidnightLibClientEvents {
    @SubscribeEvent
    public static void onPostInit(FMLClientSetupEvent event) {
        ModList.get().forEachModContainer((modid, modContainer) -> {
            if (MidnightConfig.configClass.containsKey(modid)) {
                modContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
                        new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> MidnightConfig.getScreen(parent, modid)));
            }
        });
    }
}
