package top.speedcubing.forge.mods.antiafk;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandAntiAFK extends CommandBase {

    public String getCommandName() {
        return "antiafk";
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/antiafk";
    }

    public void processCommand(ICommandSender sender, String[] s) {
        AntiAFK.getInstance().toggle();
    }
}
