package gkappa.cta.mixin;

import betteradvancements.gui.GuiBetterAdvancementTab;
import betteradvancements.gui.GuiScreenBetterAdvancements;
import gkappa.cta.ISetCenteredAble;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = GuiScreenBetterAdvancements.class, remap = false)
public class MixinGuiScreenBetterAdvancement extends GuiScreen implements ISetCenteredAble {

    @Shadow
    protected int internalWidth;
    @Shadow
    protected int internalHeight;

    @Shadow private GuiBetterAdvancementTab selectedTab;

    @Override
    public void setCentered() {
        int left = 30 + (this.width - this.internalWidth) / 2;
        int top = 40 + (this.height - this.internalHeight) / 2;
        int right = this.internalWidth - 30 + (this.width - this.internalWidth) / 2;
        int bottom = this.internalHeight - 30 + (this.height - this.internalHeight) / 2;

        int width = right - left;
        int height = bottom - top;
        this.selectedTab.drawContents(width, height);
    }

    @Override
    public int getWidth() {
        int left = 30 + (this.width - this.internalWidth) / 2;
        int right = this.internalWidth - 30 + (this.width - this.internalWidth) / 2;
        return right - left;
    }

    @Override
    public int getHeight() {
        int top = 40 + (this.height - this.internalHeight) / 2;
        int bottom = this.internalHeight - 30 + (this.height - this.internalHeight) / 2;
        return bottom - top;
    }
}
