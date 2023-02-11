package gkappa.cta.mixin;

import betteradvancements.gui.GuiBetterAdvancementTab;
import betteradvancements.gui.GuiScreenBetterAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = GuiScreenBetterAdvancements.class, remap = false)
public interface IMixinGuiScreenBetterAdvancement {
    @Accessor
    GuiBetterAdvancementTab getSelectedTab();
    @Accessor
    int getInternalWidth();
    @Accessor
    int getInternalHeight();
}
