package top.speedcubing.forge.mods.autocastle;

import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import top.speedcubing.forge.mods.antiafk.AntiAFK;
import top.speedcubing.forge.util.Helper;
import top.speedcubing.forge.util.KeyUtils;

public class AutoCastle {

    private static AutoCastle instance;

    public AutoCastle() {
        instance = this;
    }

    public static AutoCastle getInstance() {
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
        Helper.sendMessage("[SC] AutoCastle is now " + (AutoCastle.getInstance().isEnabled() ? "on" : "off"));
    }

    private boolean moving1 = false;
    private boolean moving2 = false;
    private boolean moving3 = false;

    public void handleClientTick(TickEvent e) {
        if (moving1) {
            EntityPlayerSP player = Helper.getPlayer();
            if (Math.abs(player.posZ) <= 157) {
                KeyUtils.keyUp(Helper.getGameSettings().keyBindForward.getKeyCode());
                KeyUtils.keyDown(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode());
                moving1 = false;
                moving2 = true;
            }
        }
        if (moving2) {
            EntityPlayerSP player = Helper.getPlayer();
            if (Math.abs(player.posX) >= 20) {
                KeyUtils.keyUp(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode());
                moving2 = false;
            }
        }
        if (moving3) {
            EntityPlayerSP player = Helper.getPlayer();
            int forward = Helper.getGameSettings().keyBindForward.getKeyCode();
            if (Math.abs(player.posZ) < 118) {
                KeyUtils.keyUp(forward);
                moving3 = false;
            }
        }
    }

    public void handleChatEvent(IChatComponent chatComponent) {
        if (!enabled) {
            return;
        }

        String s = chatComponent.getUnformattedText();
        if (s.toLowerCase().contains("reward summary") ||
                s.toLowerCase().contains("you have been eliminated!") ||
                s.toLowerCase().contains("joined the lobby!") ||
                s.toLowerCase().contains("something went wrong trying to send you to that server! if this keeps happening please report it! (") ||
                s.toLowerCase().contains("exception connecting:readtimeoutexception : null") ||
                s.toLowerCase().contains("/limbo for more information")) {

            if (s.toLowerCase().contains("reward summary")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("gg");
            }

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Helper.chat("/play bedwars_castle");
                }
            }, 2000);
            return;
        }

        if (s.toLowerCase().contains("protect your bed and destroy the enemy beds") || s.toLowerCase().contains("you have respawned!")) {

            int delay = 30000;
            if (s.toLowerCase().contains("you have respawned!")) {
                delay = 2000;
            }

            int forward = Helper.getGameSettings().keyBindForward.getKeyCode();
            EntityPlayerSP player = Helper.getPlayer();
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    float yaw = player.posZ < 0 ? 0 : 180;
                    player.setPositionAndRotation(player.posX, player.posY, player.posZ, yaw, player.rotationPitch);

                    // move forward
                    KeyUtils.keyDown(forward);

                    if (Math.abs(player.posX) < 10) { // heart
                        moving1 = true;
                    } else { // square, star
                        moving3 = true;
                    }
                }
            }, delay);
        }
    }
}
