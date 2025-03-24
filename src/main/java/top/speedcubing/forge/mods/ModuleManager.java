package top.speedcubing.forge.mods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import top.speedcubing.forge.module.Module;
import top.speedcubing.forge.module.ToggleableModule;

public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();

    public static List<Module> getModules() {
        return modules;
    }

    public static void addModule(Module m) {
        modules.add(m);
        modules.sort(Comparator.comparing(Module::getName));
    }
}
