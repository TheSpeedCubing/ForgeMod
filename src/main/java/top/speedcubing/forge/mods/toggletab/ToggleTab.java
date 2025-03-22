package top.speedcubing.forge.mods.toggletab;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import top.speedcubing.forge.util.KeyUtils;

public class ToggleTab {

    private static ToggleTab instance;

    public ToggleTab() {
        instance = this;
    }

    public static ToggleTab getInstance() {
        return instance;
    }

    private boolean enabled = false;
    private boolean state = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        enabled = !enabled;
    }

    public void handleClientTick(TickEvent.ClientTickEvent e) {
        if (!enabled) {
            return;
        }

        KeyUtils.set(Minecraft.getMinecraft().gameSettings.keyBindPlayerList.getKeyCode(), state);
    }

    public void handleKeyInput(InputEvent.KeyInputEvent e) {
        if (!enabled) {
            return;
        }

        int tabCode = Minecraft.getMinecraft().gameSettings.keyBindPlayerList.getKeyCode();
        if (Keyboard.getEventKey() == tabCode && KeyUtils.isKeyDown(tabCode)) {
            state = !state;
        }
    }
}
