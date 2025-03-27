package top.speedcubing.forge.mods.flyspeed;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;

public class CommandFlySpeed extends CubingCommandBase {

    public CommandFlySpeed() {
        super("flyspeed", "/flyspeed\n/flyspeed <float>");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        if (s.length == 0) {
            FlySpeed.getInstance().toggle();
            return;
        }

        if (s.length == 1) {
            try {
                float f = Float.parseFloat(s[0]);
                FlySpeed.getInstance().setFlySpeed(f);
                FlySpeed.getInstance().send("flyspeed has been set to §e" + f);
            } catch (NumberFormatException e) {
                FlySpeed.getInstance().send("§cinvalid floating point!");
            }
            return;
        }

        help();
    }
}
