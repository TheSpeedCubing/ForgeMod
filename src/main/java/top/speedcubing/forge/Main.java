package top.speedcubing.forge;

import net.minecraft.client.gui.GuiNewChat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.speedcubing.forge.listeners.ClientConnectedToServer;
import top.speedcubing.forge.listeners.ClientTick;
import top.speedcubing.forge.listeners.KeyInput;
import top.speedcubing.forge.mods.antiafk.AntiAFK;
import top.speedcubing.forge.mods.autocastle.AutoCastle;
import top.speedcubing.forge.mods.bossbar.Bossbar;
import top.speedcubing.forge.mods.chatstacking.ChatStacking;
import top.speedcubing.forge.mods.flyspeed.FlySpeed;
import top.speedcubing.forge.mods.fovchanger.FovChanger;
import top.speedcubing.forge.mods.freelook.FreeLook;
import top.speedcubing.forge.mods.gamma.GammaChanger;
import top.speedcubing.forge.mods.help.CommandHelp;
import top.speedcubing.forge.mods.hurt.Hurt;
import top.speedcubing.forge.mods.packetlogger.PacketLogger;
import top.speedcubing.forge.mods.toggletab.ToggleTab;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {
    public static final String MODID = "speedcubing_skyblock_helper";
    public static final String VERSION = "1.0";
    public static Logger log;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        log = LogManager.getLogger(MODID);
        new AntiAFK();
        new AutoCastle();
        new Hurt();
        new Bossbar();
        new GammaChanger();
        new FovChanger();
        new FlySpeed();
        new PacketLogger();
        new ToggleTab();
        new FreeLook();
        new ChatStacking();
        new CommandHelp();
        MinecraftForge.EVENT_BUS.register(new ClientConnectedToServer());
        MinecraftForge.EVENT_BUS.register(new ClientTick());
        MinecraftForge.EVENT_BUS.register(new KeyInput());
    }
}