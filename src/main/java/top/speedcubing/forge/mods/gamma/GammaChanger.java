package top.speedcubing.forge.mods.gamma;

import top.speedcubing.forge.module.Module;
import top.speedcubing.forge.util.Helper;

public class GammaChanger extends Module {

    private static GammaChanger instance;

    public GammaChanger() {
        super("Gamma", new CommandGamma());
        instance = this;
    }

    public static GammaChanger getInstance() {
        return instance;
    }

    @Override
    public String getStateDisplayString() {
        return "§6(§e" + gamma + "§6)";
    }

    private float gamma;

    public void updateGamma(float fov) {
        this.gamma = fov;
        Helper.getGameSettings().gammaSetting = gamma;
    }
}
