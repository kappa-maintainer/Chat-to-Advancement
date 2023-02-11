package gkappa.cta;


import gkappa.cta.proxy.IProxy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        useMetadata = true,
        version = Reference.MOD_VERSION,
        dependencies = Reference.MOD_DEPENDENCIES
)
public class Chat2Adv {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);
    public static GuiScreen baGui = null;
    @SidedProxy(clientSide = "gkappa.cta.proxy.ClientProxy", serverSide = "gkappa.cta.proxy.CommonProxy")
    public static IProxy proxy;

	@Instance(Reference.MOD_ID)
	public static Chat2Adv _instance;
    public Chat2Adv() {}
        
    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
    }
    
    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
    }
    
    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    @EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
    }
}
