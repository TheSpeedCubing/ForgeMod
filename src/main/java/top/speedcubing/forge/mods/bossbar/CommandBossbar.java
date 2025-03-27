package top.speedcubing.forge.mods.bossbar;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;

public class CommandBossbar extends CubingCommandBase {
    public CommandBossbar() {
        super("bossbar", "/bossbar bar\n/bossbar text");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        if (s.length == 1) {
            if (s[0].equalsIgnoreCase("bar")) {
                Bossbar.getInstance().toggleBar();
                Bossbar.getInstance().send(" bar is now " + (Bossbar.getInstance().isBarOn() ? "§aon" : "§coff"));
                return;
            }
            if (s[0].equalsIgnoreCase("text")) {
                Bossbar.getInstance().toggleText();
                Bossbar.getInstance().send(" text is now " + (Bossbar.getInstance().isTextOn() ? "§aon" : "§coff"));
                return;
            }
        }
        help();
    }
}