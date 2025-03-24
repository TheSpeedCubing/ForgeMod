package top.speedcubing.forge.mods.antiafk;

import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.IChatComponent;
import top.speedcubing.forge.mods.Module;

public class AntiAFK extends Module {

    private static AntiAFK instance;

    public AntiAFK() {
        super("AntiAFK", new CommandAntiAFK());
        instance = this;
    }

    public static AntiAFK getInstance() {
        return instance;
    }

    private boolean state = false;

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
