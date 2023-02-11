package gkappa.cta.mixin;

import betteradvancements.gui.GuiBetterAdvancementTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = GuiBetterAdvancementTab.class, remap = false)
public interface IMixinGuiBetterAdvancementTab {
    @Accessor
    int getScrollX();
    @Accessor
    int getScrollY();
}
