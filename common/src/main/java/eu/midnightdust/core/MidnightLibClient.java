package eu.midnightdust.core;

import eu.midnightdust.core.config.MidnightLibConfig;
import eu.midnightdust.lib.config.MidnightConfig;

import java.util.ArrayList;
import java.util.List;

public class MidnightLibClient {
    public static List<String> hiddenMods = new ArrayList<>();

    public static void onInitializeClient() {
        MidnightConfig.init("midnightlib", MidnightLibConfig.class);
    }
}
