package top.speedcubing.forge.mods.antiafk;

import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.IChatComponent;
import top.speedcubing.forge.util.Helper;

public class AntiAFK {

    private static AntiAFK instance;

    public AntiAFK() {
        instance = this;
    }

    public static AntiAFK getInstance() {
        return instance;
    }

    private boolean enabled = false;
    private boolean state = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        enabled = !enabled;
        Helper.sendMessage("[SC] AntiAFK is now " + (AntiAFK.getInstance().isEnabled() ? "on" : "off"));
    }


    public void handleChatEvent(IChatComponent chatComponent) {
        String s = chatComponent.getUnformattedText();
        if (!s.toLowerCase().contains("you will be afk-ed in 10 seconds!")) {
            return;
        }
        KeyBinding keyCode = state ? Minecraft.getMinecraft().gameSettings.keyBindForward : Minecraft.getMinecraft().gameSettings.keyBindBack;
        KeyBinding.setKeyBindState(keyCode.getKeyCode(), true);
        KeyBinding.onTick(keyCode.getKeyCode());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                KeyBinding.setKeyBindState(keyCode.getKeyCode(), false);
                KeyBinding.onTick(keyCode.getKeyCode());
            }
        }, 600);
        state = !state;
    }
}
