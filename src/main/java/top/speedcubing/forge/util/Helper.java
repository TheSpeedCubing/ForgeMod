package top.speedcubing.forge.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ChatComponentText;

public class Helper {
    public static EntityPlayerSP getPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public static GameSettings getGameSettings() {
        return Minecraft.getMinecraft().gameSettings;
    }

    public static void sendMessage(String message) {
        try {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
        } catch (NullPointerException ignored) {

        }
    }

    public static void chat(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
    }

    public static void log(String message) {
        System.out.println("[SC] " + message);
    }
}
