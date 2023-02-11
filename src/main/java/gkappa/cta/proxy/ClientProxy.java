package gkappa.cta.proxy;

import javax.annotation.Nullable;

import gkappa.cta.command.CommandLocateAdv;
import gkappa.cta.command.CommandLocateBAdv;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy implements IProxy {

    private final Minecraft MINECRAFT = Minecraft.getMinecraft();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if(Loader.isModLoaded("betteradvancements")) {
            ClientCommandHandler.instance.registerCommand(new CommandLocateBAdv());
            MinecraftForge.EVENT_BUS.register(CommandLocateBAdv.class);
        } else {
            ClientCommandHandler.instance.registerCommand(new CommandLocateAdv());
            MinecraftForge.EVENT_BUS.register(CommandLocateAdv.class);
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Nullable
    @Override
    public EntityPlayer getClientPlayer() {
        return MINECRAFT.player;
    }

    @Nullable
    @Override
    public World getClientWorld() {
        return MINECRAFT.world;
    }

    @Override
    public IThreadListener getThreadListener(final MessageContext context) {
        if (context.side.isClient()) {
            return MINECRAFT;
        } else {
            return context.getServerHandler().player.server;
        }
    }

    @Override
    public EntityPlayer getPlayer(final MessageContext context) {
        if (context.side.isClient()) {
            return MINECRAFT.player;
        } else {
            return context.getServerHandler().player;
        }
    }
}
