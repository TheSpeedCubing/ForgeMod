package top.speedcubing.forge.mods.headpositionfix;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.MouseEvent;
import top.speedcubing.forge.util.Helper;

public class HeadPositionFix {

    private static HeadPositionFix instance;

    public HeadPositionFix() {
        instance = this;
    }

    public static HeadPositionFix getInstance() {
        return instance;
    }

    public void handleMouse(MouseEvent e) {
        if (e.dx != 0 || e.dy != 0) {
            EntityPlayerSP player = Helper.getPlayer();
            if (player == null) {
                return;
            }

            player.prevRenderYawOffset = player.renderYawOffset;
            player.prevRotationYawHead = player.rotationYawHead;
            player.prevRotationYaw = player.rotationYaw;
            player.prevRotationPitch = player.rotationPitch;
        }
    }
}
