package top.speedcubing.forge.module;

import top.speedcubing.forge.mods.CubingCommandBase;

public class ToggleableModule extends Module {

    private boolean enabled;

    public ToggleableModule(String name, CubingCommandBase command) {
        super(name, command);
    }

    @Override
    public String getStateDisplayString() {
        return "§6(" + (isEnabled() ? "§aon" : "§coff") + "§6)";
    }

    public void toggle() {
        this.enabled = !this.enabled;
        send("is now " + (isEnabled() ? "§aon" : "§coff"));
    }

    public void disable() {
        this.enabled = false;
    }

    public void toggleSilent() {
        this.enabled = !this.enabled;
    }


    public boolean isEnabled() {
        return enabled;
    }
}
