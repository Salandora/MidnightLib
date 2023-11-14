package eu.midnightdust.fabric.core;

import eu.midnightdust.core.MidnightLibClient;

import net.fabricmc.api.ClientModInitializer;

public class MidnightLibClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MidnightLibClient.onInitializeClient();
    }
}
