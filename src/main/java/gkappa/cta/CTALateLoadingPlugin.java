package gkappa.cta;

import net.minecraftforge.fml.common.Loader;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Collections;
import java.util.List;

public class CTALateLoadingPlugin implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("ba.mixins.json");
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return mixinConfig.equals("ba.mixins.json") && Loader.isModLoaded("betteradvancements");
    }
}
