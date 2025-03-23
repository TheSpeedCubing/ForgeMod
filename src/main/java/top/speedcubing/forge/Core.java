package top.speedcubing.forge;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import top.speedcubing.forge.mods.tcpnodelay.ClassTransformer;

@IFMLLoadingPlugin.MCVersion("1.8.9")
@IFMLLoadingPlugin.TransformerExclusions({"top.speedcubing.forge"})
public class Core implements IFMLLoadingPlugin {
    public String[] getASMTransformerClass() {
        return new String[]{ClassTransformer.class.getName()};
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
    }

    public String getAccessTransformerClass() {
        return null;
    }
}
