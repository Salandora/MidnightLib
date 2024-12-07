package eu.midnightdust.forge;

import eu.midnightdust.core.MidnightLibClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = "midnightlib", dist = { Dist.CLIENT })
public class MidnightLibClientForge {
	public MidnightLibClientForge() {
		MidnightLibClient.onInitializeClient();
	}
}
