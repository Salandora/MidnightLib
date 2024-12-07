package eu.midnightdust.client.screen;

import eu.midnightdust.core.MidnightLibClient;
import eu.midnightdust.lib.config.MidnightConfig;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static eu.midnightdust.lib.config.MidnightConfig.MidnightConfigListWidget;

@Environment(EnvType.CLIENT)
public class MidnightConfigOverviewScreen extends Screen {

    public MidnightConfigOverviewScreen(Screen parent) {
        super(Component.translatable( "midnightlib.overview.title"));
        this.parent = parent;
    }
    private final Screen parent;
    private MidnightConfigListWidget list;

    @Override
    protected void init() {
        //noinspection DataFlowIssue
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> this.minecraft.setScreen(parent))
                                .bounds(this.width / 2 - 100, this.height - 28, 200, 20)
                                .build());

        //noinspection DataFlowIssue
        this.list = this.addWidget(new MidnightConfigListWidget(this.minecraft, this.width, this.height - 57, 24, 25));
        List<String> sortedMods = new ArrayList<>(MidnightConfig.configClass.keySet());
        Collections.sort(sortedMods);
        sortedMods.forEach((modid) -> {
            if (!MidnightLibClient.hiddenMods.contains(modid)) {
                list.addButton(List.of(Button.builder(Component.translatable(modid + ".midnightconfig.title"), (button) ->
                        minecraft.setScreen(MidnightConfig.getScreen(this, modid))).bounds(this.width / 2 - 125, this.height - 28, 250, 20).build()), null, null);
            }
        });
        super.init();
    }
    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.renderBackground(context, mouseX, mouseY, delta);
        this.list.render(context, mouseX, mouseY, delta);

        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredString(font, title, width / 2, 15, 0xFFFFFF);
    }
}