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
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }

    public static void chat(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
    }
}
