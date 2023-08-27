package gkappa.cta.mixin;

import gkappa.cta.Chat2Adv;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerAdvancements.class)
public class MixinPlayerAdvancements {

    @Redirect(method = "grantCriterion", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/Advancement;getDisplayText()Lnet/minecraft/util/text/ITextComponent;"))
    private ITextComponent appendStyle(Advancement instance) {
        ITextComponent adv = instance.getDisplayText();
        return adv.setStyle(adv.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/locateadv " + instance.getId())));
    }

}
