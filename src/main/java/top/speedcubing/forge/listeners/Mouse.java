package top.speedcubing.forge.listeners;

import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import top.speedcubing.forge.mods.headpositionfix.HeadPositionFix;

public class Mouse {

    @SubscribeEvent
    public void onMouse(MouseEvent e) {
        HeadPositionFix.getInstance().handleMouse(e);
    }
}
