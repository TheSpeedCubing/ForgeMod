package com.example.examplemod;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static Logger log;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        log = LogManager.getLogger(MODID);
        MinecraftForge.EVENT_BUS.register(this);
        // some example code
    }

    @SubscribeEvent
    public void dwdw(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        e.manager.channel().pipeline().addAfter("decoder", "adwadawwda", new ChannelDuplexHandler() {
            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object byteBuf, ChannelPromise promise) throws Exception {
                super.write(channelHandlerContext, byteBuf, promise);
            }

            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                log.log(Level.INFO,packet);
                super.channelRead(channelHandlerContext, packet);
            }
        });
    }
}