package top.speedcubing.forge.util;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyUtils {
    public static void keyDown(int key) {
        set(key, true);
    }

    public static void keyUp(int key) {
        set(key, false);
    }

    public static void set(int key, boolean state) {
        KeyBinding.setKeyBindState(key, state);
        KeyBinding.onTick(key);
    }

    public static boolean isKeyDown(int key) {
        return Keyboard.isKeyDown(key);
    }
}
