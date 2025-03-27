package top.speedcubing.forge.mods.flyspeed;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import top.speedcubing.forge.module.ToggleableModule;
import top.speedcubing.forge.util.Helper;

public class FlySpeed extends ToggleableModule {

    private static FlySpeed instance;

    public FlySpeed() {
        super("FlySpeed", new CommandFlySpeed());
        instance = this;
    }

    public static FlySpeed getInstance() {
        return instance;
    }

    @Override
    public String getStateDisplayString() {
        return super.getStateDisplayString() + " (§e" + flySpeed + "§6)";
    }

    private float flySpeed = 0.05F;

    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
    }

    public void handleClientTick(TickEvent e) {
        if (!isEnabled()) {
            return;
        }

        try {
            Helper.getPlayer().capabilities.setFlySpeed(flySpeed);
        } catch (Exception ignored) {

        }
    }
}
