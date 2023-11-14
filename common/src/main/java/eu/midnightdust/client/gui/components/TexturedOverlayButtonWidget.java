package eu.midnightdust.client.gui.components;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class TexturedOverlayButtonWidget extends Button {
	protected final ResourceLocation texture;
	protected final int textureWidth;
	protected final int textureHeight;

	public TexturedOverlayButtonWidget(int x, int y, int width, int height, ResourceLocation texture, int textureWidth, int textureHeight, OnPress onPress, Component text) {
		super(x, y, width, height, text, onPress, DEFAULT_NARRATION);
		this.texture = texture;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}

	protected TexturedOverlayButtonWidget(Builder buttonBuilder) {
		this(buttonBuilder.x, buttonBuilder.y, buttonBuilder.width, buttonBuilder.height,
				buttonBuilder.texture, buttonBuilder.textureWidth, buttonBuilder.textureHeight,
				buttonBuilder.onPress, buttonBuilder.message);
	}

	@Override
	public void renderWidget(GuiGraphics context, int mouseX, int mouseY, float delta) {
		super.renderWidget(context, mouseX, mouseY, delta);
		this.renderTexture(context, this.texture, this.getXOffset(), this.getYOffset(), 0, 0, 0, this.textureWidth, this.textureHeight, this.textureWidth, this.textureHeight);
	}

	@Override
	public void renderString(GuiGraphics guiGraphics, Font font, int i) {
	}

	private int getXOffset() {
		return this.getX() + (this.getWidth() / 2 - this.textureWidth / 2);
	}

	private int getYOffset() {
		return this.getY() + (this.getHeight() / 2 - this.textureHeight / 2);
	}

	public static TexturedOverlayButtonWidget.Builder texturedBuilder(Component message, OnPress onPress) {
		return new Builder(message, onPress);
	}

	public static class Builder {
		public final Component message;
		public final OnPress onPress;
		public int x;
		public int y;
		public int width = 150;
		public int height = 20;
		public ResourceLocation texture;
		public int textureWidth = 16;
		public int textureHeight = 16;

		public Builder(Component message, OnPress onPress) {
			this.message = message;
			this.onPress = onPress;
		}

		public Builder position(int x, int y) {
			this.x = x;
			this.y = y;
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

		public Builder texture(ResourceLocation texture, int width, int height) {
			this.texture = texture;
			this.textureWidth = width;
			this.textureHeight = height;
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
