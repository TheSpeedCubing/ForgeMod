package top.speedcubing.forge.mods;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import top.speedcubing.forge.util.Helper;

public abstract class CubingCommandBase extends CommandBase {

    private final String commandName;
    private final String commandUsage;
    private final boolean help;

    public CubingCommandBase(String commandName, String commandUsage) {
        this(commandName, commandUsage, true);
    }

    public CubingCommandBase(String commandName, String commandUsage, boolean help) {
        this.commandName = commandName;
        this.commandUsage = commandUsage;
        this.help = help;
        ClientCommandHandler.instance.registerCommand(this);
    }

    public final boolean hasHelp() {
        return help;
    }

    public final void help() {
        Helper.sendMessage(commandUsage);
    }

    @Override
    public final String getCommandName() {
        return commandName;
    }

    @Override
    public final boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public final String getCommandUsage(ICommandSender sender) {
        return commandUsage;
    }

    @Override
    public final void processCommand(ICommandSender sender, String[] s) {
        if (help && s.length != 0 && s[0].equalsIgnoreCase("help")) {
            Helper.sendMessage(commandUsage);
            return;
        }
        execute(sender, s);
    }

    public abstract void execute(ICommandSender sender, String[] s);
}
