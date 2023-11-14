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
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class MidnightConfigOverviewScreen extends Screen {

    public MidnightConfigOverviewScreen(Screen parent) {
        super(Component.translatable( "midnightlib.overview.title"));
        this.parent = parent;
    }
    private final Screen parent;
    private MidnightOverviewListWidget list;

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> Objects.requireNonNull(minecraft).setScreen(parent)).bounds(this.width / 2 - 100, this.height - 28, 200, 20).build());

        this.list = new MidnightOverviewListWidget(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        if (this.minecraft != null && this.minecraft.level != null) this.list.setRenderBackground(false);
        this.addWidget(this.list);
        List<String> sortedMods = new ArrayList<>(MidnightConfig.configClass.keySet());
        Collections.sort(sortedMods);
        sortedMods.forEach((modid) -> {
            if (!MidnightLibClient.hiddenMods.contains(modid)) {
                list.addButton(Button.builder(Component.translatable(modid +".midnightconfig.title"), (button) ->
                        Objects.requireNonNull(minecraft).setScreen(MidnightConfig.getScreen(this,modid))).bounds(this.width / 2 - 125, this.height - 28, 250, 20).build());
            }
        });
        super.init();
    }
    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.list.render(context, mouseX, mouseY, delta);
        context.drawCenteredString(font, title, width / 2, 15, 0xFFFFFF);
    }
    @Environment(EnvType.CLIENT)
    public static class MidnightOverviewListWidget extends ContainerObjectSelectionList<OverviewButtonEntry> {
        public MidnightOverviewListWidget(Minecraft minecraftClient, int i, int j, int k, int l, int m) {
            super(minecraftClient, i, j, k, l, m);
            this.centerListVertically = false;
        }
        @Override
        public int getScrollbarPosition() {return this.width-7;}

        public void addButton(AbstractWidget button) {
            this.addEntry(OverviewButtonEntry.create(button));
        }
        @Override
        public int getRowWidth() { return 400; }
    }
    public static class OverviewButtonEntry extends ContainerObjectSelectionList.Entry<OverviewButtonEntry> {
        private final AbstractWidget button;

        private OverviewButtonEntry(AbstractWidget button) {
            this.button = button;
        }
        public static OverviewButtonEntry create(AbstractWidget button) {return new OverviewButtonEntry(button);}
        public void render(GuiGraphics context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            button.setY(y);
            button.render(context, mouseX, mouseY, tickDelta);
        }
        public List<? extends GuiEventListener> children() {return List.of(button);}
        public List<? extends NarratableEntry> narratables() {return List.of(button);}
    }
}