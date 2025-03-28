package top.speedcubing.forge.mods.hurt;

import top.speedcubing.forge.module.Module;

public class Hurt extends Module {

    private static Hurt instance;

    public Hurt() {
        super("Hurt", new CommandHurt());

        instance = this;
    }

    public static Hurt getInstance() {
        return instance;
    }

    @Override
    public String getStateDisplayString() {
        return "§6shake: (" + (shake ? "§aon" : "§coff") + "§6)";
    }

    private boolean shake = true;

    public void toggleShake() {
        this.shake = !this.shake;
    }

    public boolean isShake() {
        return this.shake;
    }
}
