package top.speedcubing.forge.mods.packetlogger;

import net.minecraft.network.play.server.S02PacketChat;
import top.speedcubing.forge.mods.antiafk.AntiAFK;
import top.speedcubing.forge.util.Helper;

public class PacketLogger {

    private static PacketLogger instance;

    public PacketLogger() {
        instance = this;
    }

    public static PacketLogger getInstance() {
        return instance;
    }

    private boolean enabled = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        if (!AntiAFK.getInstance().isEnabled()) {
            AntiAFK.getInstance().toggle();
        }
        enabled = !enabled;
        Helper.sendMessage("[SC] PacketLogger is now " + (PacketLogger.getInstance().isEnabled() ? "on" : "off"));
    }

    public void handlePacket(Object packet) {
        if (packet instanceof S02PacketChat) {

        }
    }
}
