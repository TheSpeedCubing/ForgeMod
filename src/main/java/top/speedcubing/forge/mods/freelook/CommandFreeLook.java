package top.speedcubing.forge.mods.freelook;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;

class CommandFreeLook extends CubingCommandBase {
    public CommandFreeLook() {
        super("freelook", "/freelook hold\n/freelook toggle");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        if (s.length == 1) {
            if (s[0].equalsIgnoreCase("hold")) {
                FreeLook.getInstance().holdMode();
                return;
            }
            if (s[0].equalsIgnoreCase("toggle")) {
                FreeLook.getInstance().toggleMode();
                return;
            }
        }
        help();
    }
}
