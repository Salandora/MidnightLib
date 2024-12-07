package eu.midnightdust.lib.util.neoforge;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import eu.midnightdust.lib.util.PlatformFunctions;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.commands.CommandSourceStack;

import java.nio.file.Path;

public class PlatformFunctionsImpl {
    /**
     * This is our actual method to {@link PlatformFunctions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
    public static boolean isClientEnv() {
        return FMLEnvironment.dist.isClient();
    }
    public static boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }
    public static void registerCommand(LiteralArgumentBuilder<CommandSourceStack> command) {
        // Ignored here, see MidnightLibServerEvents#registerCommands
    }
}
