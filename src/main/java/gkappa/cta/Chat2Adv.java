package gkappa.cta;


import gkappa.cta.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        //clientSideOnly = true,
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        useMetadata = true,
        version = Reference.MOD_VERSION,
        dependencies = Reference.MOD_DEPENDENCIES
)
@Mod.EventBusSubscriber
public class Chat2Adv {
    @SidedProxy(clientSide = "gkappa.cta.proxy.ClientProxy", serverSide = "gkappa.cta.proxy.CommonProxy")
    public static IProxy proxy;
        
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

    }
}
