package top.speedcubing.forge.mods.autocastle;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;

public class CommandAutoCastle extends CubingCommandBase {

    public CommandAutoCastle() {
        super("autocastle", "/autocastle");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        AutoCastle.getInstance().toggle();
    }
}