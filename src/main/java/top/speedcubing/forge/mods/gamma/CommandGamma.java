package top.speedcubing.forge.mods.gamma;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;

public class CommandGamma extends CubingCommandBase {
    public CommandGamma() {
        super("gamma", "/gamma <float>");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        if (s.length == 1) {
            try {
                float f = Float.parseFloat(s[0]);
                GammaChanger.getInstance().updateGamma(f);
                GammaChanger.getInstance().send("gamma has been set to §e" + f);
            } catch (NumberFormatException e) {
                GammaChanger.getInstance().send("§cinvalid floating point!");
            }
            return;
        }

        help();
    }
}