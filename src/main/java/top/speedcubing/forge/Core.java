package top.speedcubing.forge;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import top.speedcubing.forge.mods.bossbar.asm.BossbarTF;
import top.speedcubing.forge.mods.chatstacking.asm.ChatStackingTF;
import top.speedcubing.forge.mods.freelook.asm.FreeLookTF;
import top.speedcubing.forge.mods.hurt.asm.CameraEffectTF;
import top.speedcubing.forge.mods.tcpnodelay.TFOTF;

@IFMLLoadingPlugin.Name("Speedcubing")
@IFMLLoadingPlugin.MCVersion("1.8.9")
@IFMLLoadingPlugin.TransformerExclusions({"top.speedcubing.forge"})
public class Core implements IFMLLoadingPlugin {
    public String[] getASMTransformerClass() {
        return new String[]{
                TFOTF.class.getName(),
                FreeLookTF.class.getName(),
                ChatStackingTF.class.getName(),
                BossbarTF.class.getName(),
                CameraEffectTF.class.getName()
        };
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
