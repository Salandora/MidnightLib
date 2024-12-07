package eu.midnightdust.forge;

import eu.midnightdust.lib.config.MidnightConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@EventBusSubscriber(modid = "midnightlib", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MidnightLibClientEvents {
    @SubscribeEvent
    public static void onPostInit(FMLClientSetupEvent event) {
        ModList.get().forEachModContainer((modid, modContainer) -> {
            if (MidnightConfig.configClass.containsKey(modid)) {
                modContainer.registerExtensionPoint(IConfigScreenFactory.class, (client, parent) -> MidnightConfig.getScreen(parent, modid));
            }
        });
    }
}
