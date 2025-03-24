package top.speedcubing.forge.mods.antiafk;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;

public class CommandAntiAFK extends CubingCommandBase {

    public CommandAntiAFK() {
        super("antiafk", "/antiafk");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        AntiAFK.getInstance().toggle();
    }
}