package top.speedcubing.forge.mods.fovchanger;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;

class CommandFov extends CubingCommandBase {
    public CommandFov() {
        super("fov", "/fov <float>");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        if (s.length == 1) {
            try {
                float f = Float.parseFloat(s[0]);
                FovChanger.getInstance().updateFov(f);
                FovChanger.getInstance().send("fov has been set to §e" + f);
            } catch (NumberFormatException e) {
                FovChanger.getInstance().send("§cinvalid floating point!");
            }
            return;
        }

        help();
    }
}