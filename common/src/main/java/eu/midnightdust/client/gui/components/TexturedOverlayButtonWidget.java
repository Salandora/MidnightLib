package eu.midnightdust.client.gui.components;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class TexturedOverlayButtonWidget extends ImageButton {
	protected int xOverlayOffset;
	protected int yOverlayOffset;
	protected int hoveredVOverlayOffset;
	public TexturedOverlayButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, int hoveredVOverlayOffset, ResourceLocation texture, int textureWidth, int textureHeight, OnPress onPress, Component text, int xOffset, int yOffset) {
		super(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, onPress, text);
		xOverlayOffset = xOffset;
		yOverlayOffset = yOffset;
		this.hoveredVOverlayOffset = hoveredVOverlayOffset;
	}

	protected TexturedOverlayButtonWidget(Builder buttonBuilder) {
		this(buttonBuilder.x, buttonBuilder.y, buttonBuilder.width, buttonBuilder.height, buttonBuilder.u, buttonBuilder.v, buttonBuilder.hoveredVOffset, buttonBuilder.hoveredVOverlayOffset, buttonBuilder.texture, buttonBuilder.textureWidth, buttonBuilder.textureHeight, buttonBuilder.onPress, buttonBuilder.message, buttonBuilder.xOverlayOffset, buttonBuilder.yOverlayOffset);
		setTooltip(buttonBuilder.tooltip);
	}

	@Override
	public void renderWidget(GuiGraphics context, int mouseX, int mouseY, float delta) {
		int i = 66;
		if (!this.isActive()) {
			i += yDiffTex * 2;
		} else if (this.isHovered()) {
			i += yDiffTex;
		}
		context.blitNineSliced(WIDGETS_LOCATION, this.getX(), this.getY(), this.width, this.height, 4, 200, 20, 0, i);
		this.renderTexture(context, this.resourceLocation, this.getX() + xOverlayOffset, this.getY() + yOverlayOffset, this.xTexStart, this.yTexStart, this.hoveredVOverlayOffset, this.width - xOverlayOffset * 2, this.height - yOverlayOffset * 2, this.textureWidth, this.textureHeight);
	}

	public static TexturedOverlayButtonWidget.Builder texturedBuilder(Component message, OnPress onPress) {
		return new Builder(message, onPress);
	}

	public static class Builder {
		public final Component message;
		public final OnPress onPress;
		@Nullable
		public Tooltip tooltip;
		public int x;
		public int y;
		public int width = 150;
		public int height = 20;
		public ResourceLocation texture;
		public int textureWidth = 16;
		public int textureHeight = 16;
		public int u = 0;
		public int v = 0;
		public int hoveredVOffset = 0;
		public int hoveredVOverlayOffset = 0;
		public int xOverlayOffset = 0;
		public int yOverlayOffset = 0;
		public CreateNarration narrationSupplier;

		public Builder(Component message, OnPress onPress) {
			this.narrationSupplier = Button.DEFAULT_NARRATION;
			this.message = message;
			this.onPress = onPress;
		}

		public Builder position(int x, int y) {
			this.x = x;
			this.y = y;
			return this;
		}

		public Builder width(int width) {
			this.width = width;
			return this;
		}

		public Builder size(int width, int height) {
			this.width = width;
			this.height = height;
			return this;
		}

		public Builder dimensions(int x, int y, int width, int height) {
			return this.position(x, y).size(width, height);
		}

		public Builder tooltip(@Nullable Tooltip tooltip) {
			this.tooltip = tooltip;
			return this;
		}

		public Builder narrationSupplier(CreateNarration narrationSupplier) {
			this.narrationSupplier = narrationSupplier;
			return this;
		}

		public Builder texture(ResourceLocation texture, int width, int height) {
			this.texture = texture;
			this.textureWidth = width;
			this.textureHeight = height;
			return this;
		}

		public Builder uv(int u, int v) {
			this.u = u;
			this.v = v;
			return this;
		}

		public Builder overlayOffset(int x, int y) {
			this.xOverlayOffset = x;
			this.yOverlayOffset = y;
			return this;
		}

		public Builder vOffset(int hoveredVOffset) {
			this.hoveredVOffset = hoveredVOffset;
			return this;
		}

		public Builder vOverlayOffset(int hoveredVOverlayOffset) {
			this.hoveredVOverlayOffset = hoveredVOverlayOffset;
			return this;
		}

		public TexturedOverlayButtonWidget build() {
			return this.build(TexturedOverlayButtonWidget::new);
		}

		public TexturedOverlayButtonWidget build(Function<Builder, TexturedOverlayButtonWidget> builder) {
			return builder.apply(this);
		}
	}
}
