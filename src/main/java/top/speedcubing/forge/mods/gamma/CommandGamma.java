package top.speedcubing.forge.mods.gamma;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;

public class CommandGamma extends CubingCommandBase {
    public CommandGamma() {
        super("gamma", "/gamma\n/gamma <float>");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        if (s.length == 0) {
            Gamma.getInstance().toggle();
            return;
        }

        if (s.length == 1) {
            try {
                float f = Float.parseFloat(s[0]);
                Gamma.getInstance().commandGamma = f;
                if(!Gamma.getInstance().isEnabled()) {
                    Gamma.getInstance().toggle();
                }
                Gamma.getInstance().send("gamma has been set to §e" + f);
            } catch (NumberFormatException e) {
                Gamma.getInstance().send("§cinvalid floating point!");
            }
        }
    }
}