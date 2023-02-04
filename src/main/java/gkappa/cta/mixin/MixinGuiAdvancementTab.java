package gkappa.cta.mixin;

import gkappa.cta.IKeepHover;
import net.minecraft.client.gui.advancements.GuiAdvancement;
import net.minecraft.client.gui.advancements.GuiAdvancementTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiAdvancementTab.class)
public class MixinGuiAdvancementTab implements IKeepHover {
    boolean stay = false;
    GuiAdvancement foucus;
    @Override
    public void toggleStay(GuiAdvancement foucus) {
        this.stay = true;
        this.foucus = foucus;
    }

    @Redirect(method = "drawToolTips", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/advancements/GuiAdvancement;isMouseOver(IIII)Z"))
    private boolean keepHover(GuiAdvancement instance, int p_191816_1_, int p_191816_2_, int p_191816_3_, int p_191816_4_) {
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
