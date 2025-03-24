package top.speedcubing.forge.mods.toggletab;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;
import top.speedcubing.forge.util.Helper;

public class CommandToggleTab extends CubingCommandBase {

    public CommandToggleTab() {
        super("toggletab", "/toggletab");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        ToggleTab.getInstance().toggle();
    }
}
