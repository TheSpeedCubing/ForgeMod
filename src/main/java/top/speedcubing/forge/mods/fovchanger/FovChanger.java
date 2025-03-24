package top.speedcubing.forge.mods.fovchanger;

import net.minecraft.client.Minecraft;
import top.speedcubing.forge.module.Module;

public class FovChanger extends Module {

    private static FovChanger instance;

    public FovChanger() {
        super("Fov", new CommandFov());
        instance = this;
    }

    public static FovChanger getInstance() {
        return instance;
    }

    @Override
    public String getStateDisplayString() {
        return "§6(§e" + fov + "§6)";
    }

    private float fov;

    public void updateFov(float fov) {
        this.fov = fov;
        Minecraft.getMinecraft().gameSettings.fovSetting = fov;
    }
}
