package top.speedcubing.forge.mods.hurt;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;
import top.speedcubing.forge.mods.bossbar.Bossbar;

class CommandHurt extends CubingCommandBase {
    public CommandHurt() {
        super("hurt", "/hurt shake");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        if (s.length == 1) {
            if (s[0].equalsIgnoreCase("shake")) {
                Hurt.getInstance().toggleShake();
                Hurt.getInstance().send(" shake is now " + ( Hurt.getInstance().isShake() ? "§aon" : "§coff"));
                return;
            }
        }
        help();
    }
}