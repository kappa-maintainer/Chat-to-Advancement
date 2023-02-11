package gkappa.cta.mixin;

import betteradvancements.gui.GuiBetterAdvancement;
import betteradvancements.gui.GuiBetterAdvancementTab;
import gkappa.cta.IKeepHover;
import gkappa.cta.IKeepHoverBA;
import net.minecraft.client.gui.advancements.GuiAdvancement;
import net.minecraft.client.gui.advancements.GuiAdvancementTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GuiBetterAdvancementTab.class, remap = false)
public class MixinGuiBetterAdvancementTab implements IKeepHoverBA {
    boolean stay = false;
    GuiBetterAdvancement foucus;
    @Override
    public void toggleStay(GuiBetterAdvancement foucus) {
        this.stay = true;
        this.foucus = foucus;
    }

    @Redirect(method = "drawToolTips", at = @At(value = "INVOKE", target = "Lbetteradvancements/gui/GuiBetterAdvancement;isMouseOver(IIII)Z"))
    private boolean keepHover(GuiBetterAdvancement instance, int p_191816_1_, int p_191816_2_, int p_191816_3_, int p_191816_4_) {
        if(instance != foucus) return instance.isMouseOver(p_191816_1_, p_191816_2_, p_191816_3_, p_191816_4_) && !stay;
        if(instance.isMouseOver(p_191816_1_, p_191816_2_, p_191816_3_, p_191816_4_)) {
            stay = false;
            foucus = null;
            return true;
        } else {
            return stay && foucus != null;
        }
    }
}
