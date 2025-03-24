package top.speedcubing.forge.mods.gamma;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import top.speedcubing.forge.mods.Module;

public class Gamma extends Module {

    private static Gamma instance;

    public Gamma() {
        super("Gamma", new CommandGamma());
        instance = this;
    }

    public static Gamma getInstance() {
        return instance;
    }

    public float settingGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
    public float commandGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;

    @Override
    public void toggle() {
        super.toggle();
        if(isEnabled()) {
            settingGamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
        } else {
            Minecraft.getMinecraft().gameSettings.gammaSetting = settingGamma;
        }
    }


    public void handleClientTick(TickEvent e) {
        if (isEnabled()) {
            Minecraft.getMinecraft().gameSettings.gammaSetting = commandGamma;
        }
    }
}
