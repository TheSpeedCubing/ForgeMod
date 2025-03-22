package top.speedcubing.forge.mods.autocastle;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandAutoCastle extends CommandBase {

    public String getCommandName() {
        return "autocastle";
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/autocastle";
    }

    public void processCommand(ICommandSender sender, String[] s) {
        AutoCastle.getInstance().toggle();
    }
}