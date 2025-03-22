package top.speedcubing.forge.mods.toggletab;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.util.Helper;

public class CommandToggleTab extends CommandBase {

    public String getCommandName() {
        return "toggletab";
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/toggletab";
    }

    public void processCommand(ICommandSender sender, String[] s) {
        ToggleTab.getInstance().toggle();
        Helper.sendMessage("[SC] ToggleTab is now " + (ToggleTab.getInstance().isEnabled() ? "on" : "off"));
    }
}
