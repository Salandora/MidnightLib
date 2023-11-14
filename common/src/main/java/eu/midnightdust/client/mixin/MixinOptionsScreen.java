package eu.midnightdust.client.mixin;

import eu.midnightdust.client.screen.MidnightConfigOverviewScreen;
import eu.midnightdust.core.config.MidnightLibConfig;
import eu.midnightdust.lib.util.PlatformFunctions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

@Mixin(OptionsScreen.class)
public class MixinOptionsScreen extends Screen {
    protected MixinOptionsScreen(Component title) {
        super(title);
    }

    @Inject(at = @At("HEAD"),method = "init")
    private void midnightlib$init(CallbackInfo ci) {
        //noinspection ConstantValue
        if (MidnightLibConfig.config_screen_list.equals(MidnightLibConfig.ConfigButton.TRUE) || (MidnightLibConfig.config_screen_list.equals(MidnightLibConfig.ConfigButton.MODMENU) && !PlatformFunctions.isModLoaded("modmenu"))) {
            SpriteIconButton button = this.addRenderableWidget(SpriteIconButton.builder(Component.translatable("midnightlib.overview.title"),
                            (buttonWidget) -> Objects.requireNonNull(minecraft).setScreen(new MidnightConfigOverviewScreen(this)), true)
                    .sprite(new ResourceLocation("midnightlib", "icon/midnightlib"), 16, 16)
                    .size(20, 20)
                    .build());
            button.setPosition(this.width / 2 + 158, this.height / 6 - 12);
        }
    }
}
