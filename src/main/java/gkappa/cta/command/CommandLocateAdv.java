package gkappa.cta.command;

import com.google.common.collect.Lists;
import gkappa.cta.Chat2Adv;
import gkappa.cta.IKeepHover;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.GuiAdvancement;
import net.minecraft.client.gui.advancements.GuiAdvancementTab;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
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

public class CommandLocateAdv extends CommandBase {

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
        CommandLocateAdv.server = server;
        CommandLocateAdv.args = args;
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
            GuiScreenAdvancements gsa = new GuiScreenAdvancements(mc.player.connection.getAdvancementManager());

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
                GuiAdvancementTab gat = gsa.selectedTab;
                GuiAdvancement ga = gsa.getAdvancementGui(manager.getAdvancement(new ResourceLocation(args[0])));
                gat.drawContents();// make it centered
                gat.scroll(-ga.getX() - gat.scrollX + 117 + 13,  -ga.getY() - gat.scrollY + 56 + 13);
                ((IKeepHover)gat).toggleStay(ga);


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
