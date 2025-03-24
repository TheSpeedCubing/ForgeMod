package top.speedcubing.forge.mods;

import java.util.HashMap;
import java.util.Map;
import top.speedcubing.forge.util.Helper;

public class Module {

    public final String name;
    private boolean enabled;
    private final CubingCommandBase command;

    public Module(String name, CubingCommandBase command) {
        this.name = name;
        this.command = command;
        ModuleManager.addModule(this);
    }

    public CubingCommandBase getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }

    public void toggle() {
        this.enabled = !this.enabled;
        send("is now " + (isEnabled() ? "§aon" : "§coff"));
    }

    public void send(String message) {
        Helper.sendMessage("§4[speedcubing] §b" + name + " §6" + message);
    }

    public boolean isEnabled() {
        return enabled;
    }
}
