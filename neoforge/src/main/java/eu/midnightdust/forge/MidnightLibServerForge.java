package eu.midnightdust.forge;

import eu.midnightdust.core.MidnightLibServer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = "midnightlib", dist = { Dist.DEDICATED_SERVER })
public class MidnightLibServerForge {
    public MidnightLibServerForge() {
        MidnightLibServer.onInitializeServer();
    }
}
