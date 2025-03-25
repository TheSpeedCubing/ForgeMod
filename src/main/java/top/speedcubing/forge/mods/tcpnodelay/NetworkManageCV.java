package top.speedcubing.forge.mods.tcpnodelay;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NetworkManageCV extends ClassVisitor implements Opcodes {
    public NetworkManageCV(ClassWriter writer) {
        super(ASM4, writer);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        // net.minecraft.network.NetworkManager$ : protected abstract void initChannel(io.netty.channel.Channel ch) throws java/lang/Exception
        if (name.equals("initChannel")) {
            return new InitChannelMV(mv);
        }
        return mv;
    }
}
