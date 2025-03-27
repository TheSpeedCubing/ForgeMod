package top.speedcubing.forge.listeners;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import top.speedcubing.forge.mods.autocastle.AutoCastle;
import top.speedcubing.forge.mods.flyspeed.FlySpeed;
import top.speedcubing.forge.mods.toggletab.ToggleTab;

public class ClientTick {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        ToggleTab.getInstance().handleClientTick(e);
        AutoCastle.getInstance().handleClientTick(e);
        FlySpeed.getInstance().handleClientTick(e);
    }
}
