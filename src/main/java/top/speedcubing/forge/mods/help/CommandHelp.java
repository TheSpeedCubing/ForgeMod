package top.speedcubing.forge.mods.help;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;
import top.speedcubing.forge.mods.ModuleManager;
import top.speedcubing.forge.module.Module;
import top.speedcubing.forge.module.ToggleableModule;
import top.speedcubing.forge.util.Helper;

public class CommandHelp extends CubingCommandBase {
    public CommandHelp() {
        super("cubing", "/cubing");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        String str = "";
        str += "\n";
        str += "§6-----------------------------\n";
        for (Module m : ModuleManager.getModules()) {
            str += ("§b" + m.getName() + " ");
            str += m.getStateDisplayString();
            str += (" : §a/" + m.getCommand().getCommandName());

            if (m.getCommand().hasHelp()) {
                str += " help";
            }
            str += "\n";
        }
        str += "§6-----------------------------\n";
        Helper.sendMessage(str);
    }
}
