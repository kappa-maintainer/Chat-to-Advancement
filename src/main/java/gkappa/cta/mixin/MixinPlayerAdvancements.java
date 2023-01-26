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
    /*
    @Redirect(method = "grantCriterion", at = @At(value = "NEW", target = "Lnet/minecraft/util/text/TextComponentTranslation;<init>(Ljava/lang/String;[Ljava/lang/Object;)V"))
    private TextComponentTranslation appendCommand(String translationKey, Object... args) {

        //return (TextComponentTranslation) new TextComponentTranslation(translationKey, args).setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/locateadv " + ((ITextComponent)args[1]).getUnformattedText())));
        ITextComponent adv = ((ITextComponent)args[1]);
        args[1] = adv.setStyle(adv.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/locateadv " + adv.getUnformattedText())));
        Chat2Adv.LOGGER.info(adv.getUnformattedText());
        return  new TextComponentTranslation(translationKey, args);
    }*/
    @Redirect(method = "grantCriterion", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/Advancement;getDisplayText()Lnet/minecraft/util/text/ITextComponent;"))
    private ITextComponent appendStyle(Advancement instance) {
        ITextComponent adv = instance.getDisplayText();
        return adv.setStyle(adv.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/locateadv " + adv.getUnformattedText())));
    }

}
