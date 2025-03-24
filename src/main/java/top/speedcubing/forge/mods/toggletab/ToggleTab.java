package top.speedcubing.forge.mods.toggletab;

import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import top.speedcubing.forge.module.ToggleableModule;
import top.speedcubing.forge.util.Helper;
import top.speedcubing.forge.util.KeyUtils;

public class ToggleTab extends ToggleableModule {

    private static ToggleTab instance;

    public ToggleTab() {
        super("ToggleTab", new CommandToggleTab());
        instance = this;
    }

    public static ToggleTab getInstance() {
        return instance;
    }

    private boolean state = false;

    public void handleClientTick(TickEvent.ClientTickEvent e) {
        if (!isEnabled()) {
            return;
        }

        KeyUtils.set(Helper.getGameSettings().keyBindPlayerList.getKeyCode(), state);
    }

    public void handleKeyInput(InputEvent.KeyInputEvent e) {
        if (!isEnabled()) {
            return;
        }

        int tabCode = Helper.getGameSettings().keyBindPlayerList.getKeyCode();
        if (Keyboard.getEventKey() == tabCode && KeyUtils.isKeyDown(tabCode)) {
            state = !state;
        }
    }
}
