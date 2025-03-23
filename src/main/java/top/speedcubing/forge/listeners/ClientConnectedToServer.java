package top.speedcubing.forge.listeners;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import top.speedcubing.forge.mods.antiafk.AntiAFK;
import top.speedcubing.forge.mods.autocastle.AutoCastle;
import top.speedcubing.forge.mods.packetlogger.PacketLogger;

public class ClientConnectedToServer {

    @SubscribeEvent
    public void onClientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        if (e.isLocal) {
            return;
        }
        e.manager.channel().pipeline().addAfter("decoder", "speedcubing", new ChannelDuplexHandler() {
            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object byteBuf, ChannelPromise promise) throws Exception {
                super.write(channelHandlerContext, byteBuf, promise);
            }

            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                PacketLogger.getInstance().handlePacket(packet);
                if (packet instanceof S02PacketChat) {
                    IChatComponent chatComponent = ((S02PacketChat) packet).getChatComponent();
                    AntiAFK.getInstance().handleChatEvent(chatComponent);
                    AutoCastle.getInstance().handleChatEvent(chatComponent);

//                } else if (packet instanceof S3EPacketTeams) {
//                    res = "[TEAMS] " + top.speedcubing.lib.utils.ReflectionUtils.getField(packet, "field_149320_a") +
//                            " " + top.speedcubing.lib.utils.ReflectionUtils.getField(packet, "field_149318_b")
//                            + " " + top.speedcubing.lib.utils.ReflectionUtils.getField(packet, "field_149319_c")
//                            + " " + top.speedcubing.lib.utils.ReflectionUtils.getField(packet, "field_149316_d")
//                            + " " + top.speedcubing.lib.utils.ReflectionUtils.getField(packet, "field_179816_e")
//                            + " " + top.speedcubing.lib.utils.ReflectionUtils.getField(packet, "field_179815_f")
//                            + " " + top.speedcubing.lib.utils.ReflectionUtils.getField(packet, "field_149317_e")
//                            + " " + top.speedcubing.lib.utils.ReflectionUtils.getField(packet, "field_149314_f")
//                            + " " + top.speedcubing.lib.utils.ReflectionUtils.getField(packet, "field_149315_g");
//                } else if (packet instanceof S3CPacketUpdateScore) {
//                    res = "[SCORE] " + getField(packet, "name") + " " + getField(packet, "objective") + " " + getField(packet, "value") + " " + getField(packet, "action");
//                } else if (packet instanceof S3DPacketDisplayScoreboard) {
//                    res = "[DISPL] " + getField(packet, "position") + " " + getField(packet, "scoreName");
//                } else if (packet instanceof S3BPacketScoreboardObjective) {
//                    res = "[OBJEC] " + getField(packet, "objectiveName") + " " + getField(packet, "objectiveValue") + " " + getField(packet, "type") + " " + getField(packet, "field_149342_c");
//                } else if (packet instanceof S08PacketPlayerPosLook) {
//                } else if (packet instanceof S14PacketEntity) {
//                } else if (packet instanceof S02PacketChat) {
//                } else if (packet instanceof S26PacketMapChunkBulk) {
//                } else if (packet instanceof S1CPacketEntityMetadata) {
//                } else if (packet instanceof S03PacketTimeUpdate) {
//                } else if (packet instanceof S04PacketEntityEquipment) {
//                } else if (packet instanceof S21PacketChunkData) {
//                } else if (packet instanceof S0BPacketAnimation) {
//                } else if (packet instanceof S18PacketEntityTeleport) {
//                } else if (packet instanceof S12PacketEntityVelocity) {
//                } else if (packet instanceof S22PacketMultiBlockChange) {
//                } else if (packet instanceof S29PacketSoundEffect) {
//                } else if (packet instanceof S23PacketBlockChange) {
//                } else if (packet instanceof S19PacketEntityHeadLook) {
//                } else if (packet instanceof S39PacketPlayerAbilities) {
//                } else if (packet instanceof S2BPacketChangeGameState) {
//                } else if (packet instanceof S43PacketCamera) {
//                } else if (packet instanceof S2FPacketSetSlot) {
//                } else if (packet instanceof S47PacketPlayerListHeaderFooter) {
//                } else if (packet instanceof S1CPacketEntityMetadata) {
//                } else if (packet instanceof S0CPacketSpawnPlayer) {

//                } else if (packet instanceof S00PacketKeepAlive) {
//                }
//                else {
//                    res = "[PacketUNKNOWN] " + packet;
                }
                super.channelRead(channelHandlerContext, packet);
            }
        });
    }
}
