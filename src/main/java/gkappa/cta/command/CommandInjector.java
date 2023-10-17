package gkappa.cta.command;

import gkappa.cta.Chat2Adv;
import net.minecraft.util.ITabCompleter;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class CommandInjector {
    @SubscribeEvent
    public static void onReceiveMessage(ClientChatReceivedEvent event) {
        if (event.getType() == ChatType.SYSTEM) {
            ITextComponent textComponent= event.getMessage();
            if (textComponent instanceof TextComponentTranslation) {
                TextComponentTranslation textComponentTranslation = (TextComponentTranslation) textComponent;
                Chat2Adv.LOGGER.info(textComponentTranslation.getKey());
                List<ITextComponent> list = textComponentTranslation.getSiblings();
                Chat2Adv.LOGGER.info(textComponentTranslation.getSiblings());
                I18n.canTranslate()
                textComponentTranslation.forEach(child -> {
                    if (child instanceof TextComponentTranslation) {
                        child.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/locateadv " +)
                    }
                });
                /*if (textComponentTranslation.getKey().startsWith("chat.type.advancement.")) {
                    textComponentTranslation.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/locateadv " + instance.getId()));
                }*/
            }
        }
    }
}
