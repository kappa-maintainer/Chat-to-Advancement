package gkappa.cta.command;

import betteradvancements.gui.GuiBetterAdvancement;
import betteradvancements.gui.GuiBetterAdvancementTab;
import betteradvancements.gui.GuiScreenBetterAdvancements;
import com.google.common.collect.Lists;
import gkappa.cta.IKeepHoverBA;
import gkappa.cta.ISetCenteredAble;
import gkappa.cta.mixin.IMixinGuiBetterAdvancementTab;
import gkappa.cta.mixin.IMixinGuiScreenBetterAdvancement;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandLocateBAdv extends CommandBase {

    private static int opening = 0;
    private static MinecraftServer server = null;
    private static String[] args;
    @Nonnull
    @Override
    public String getName() {
        return "locateadv";
    }

    @Nonnull
    @Override
    public String getUsage(@Nonnull ICommandSender sender) {
        return "/locateadv";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
    @Nonnull
    @Override
    public List<String> getAliases() {
        return new ArrayList<>();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length != 1) {
            return;
        }
        CommandLocateBAdv.server = server;
        CommandLocateBAdv.args = args;
        opening = 1;


        //gsa.setSelectedTab(server.getAdvancementManager().getAdvancement(new ResourceLocation(args[0])));

    }

    @SubscribeEvent
    public static void delay(TickEvent.ClientTickEvent e) {
        if(opening == 1) {
            opening++;
        }
        else if(opening == 2)
        {

            opening = 0;
            Minecraft mc = Minecraft.getMinecraft();
            GuiScreenBetterAdvancements gsa = new GuiScreenBetterAdvancements(mc.player.connection.getAdvancementManager());

            mc.displayGuiScreen(gsa);
            ResourceLocation advid = new ResourceLocation(args[0]);
            AdvancementManager manager = server.getAdvancementManager();


            Advancement icon = manager.getAdvancement(new ResourceLocation(advid.getNamespace(), advid.getPath().split("/")[0] + "/root"));

            if(icon == null) {
                icon = manager.getAdvancement(new ResourceLocation(advid.getNamespace(), "root"));

                if(icon == null) {
                    icon = manager.getAdvancement(advid);

                    if(icon == null) {
                        return;
                    }
                }
            }

            if(gsa.getAdvancementGui(icon) != null) {
                gsa.setSelectedTab(icon);
                GuiBetterAdvancementTab gat = ((IMixinGuiScreenBetterAdvancement)gsa).getSelectedTab();
                if(gat == null) return;
                GuiBetterAdvancement ga = gsa.getAdvancementGui(manager.getAdvancement(new ResourceLocation(args[0])));
                ((ISetCenteredAble)gsa).setCentered();
                gat.scroll(-ga.getX() - ((IMixinGuiBetterAdvancementTab)gat).getScrollX() + ((ISetCenteredAble) gsa).getWidth()/2 - 13,
                        -ga.getY() - ((IMixinGuiBetterAdvancementTab) gat).getScrollY() + ((ISetCenteredAble) gsa).getHeight()/2 - 13,
                        ((IMixinGuiScreenBetterAdvancement) gsa).getInternalWidth() - 60 - 27, ((IMixinGuiScreenBetterAdvancement) gsa).getInternalHeight() - 40 - 30 - 27);
                ((IKeepHoverBA)gat).toggleStay(ga);


            }


        }
    }



    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return super.checkPermission(server, sender);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if(args.length == 1)
            return getListOfStringsMatchingLastWord(args, this.getAdvancementNames(server));
        return super.getTabCompletions(server, sender, args, targetPos);
    }

    private List<ResourceLocation> getAdvancementNames(MinecraftServer server)
    {
        List<ResourceLocation> list = Lists.newArrayList();

        for (Advancement advancement : server.getAdvancementManager().getAdvancements())
        {
            list.add(advancement.getId());
        }

        return list;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand iCommand) {
        return 0;
    }
}

