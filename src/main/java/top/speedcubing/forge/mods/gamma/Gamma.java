package top.speedcubing.forge.mods.gamma;

import top.speedcubing.forge.module.Module;

public class Gamma extends Module {

    private static Gamma instance;

    public Gamma() {
        super("Gamma", new CommandGamma());
        instance = this;
    }

    public static Gamma getInstance() {
        return instance;
    }

    public float gamma;

    @Override
    public String getStateDisplayString() {
        return "§6(§e" + gamma + "§6)";
    }
}
