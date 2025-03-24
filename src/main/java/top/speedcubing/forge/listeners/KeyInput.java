package top.speedcubing.forge.listeners;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import top.speedcubing.forge.mods.freelook.FreeLook;
import top.speedcubing.forge.mods.toggletab.ToggleTab;

public class KeyInput {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        ToggleTab.getInstance().handleKeyInput(e);
        FreeLook.getInstance().handleKeyInput(e);
    }
}
