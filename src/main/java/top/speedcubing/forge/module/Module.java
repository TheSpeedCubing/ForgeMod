package top.speedcubing.forge.module;

import top.speedcubing.forge.mods.CubingCommandBase;
import top.speedcubing.forge.mods.ModuleManager;
import top.speedcubing.forge.util.Helper;

public abstract class Module {
    private final String name;
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

    public void send(String message) {
        Helper.sendMessage("§4[speedcubing] §b" + getName() + " §6" + message);
    }

    public abstract String getStateDisplayString();
}
