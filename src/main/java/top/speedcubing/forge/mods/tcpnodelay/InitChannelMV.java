package top.speedcubing.forge.mods.tcpnodelay;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import top.speedcubing.forge.util.Helper;

public class InitChannelMV extends MethodVisitor implements Opcodes {
    public InitChannelMV(MethodVisitor mv) {
        super(ASM4, mv);
    }

    private String option;

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if (opcode == GETSTATIC && owner
                .equals("io/netty/channel/ChannelOption") && desc
                .equals("Lio/netty/channel/ChannelOption;"))
            this.option = name;
        this.mv.visitFieldInsn(opcode, owner, name, desc);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        if (opcode == INVOKEINTERFACE &&
                // io.netty.channel.ChannelConfig : boolean setOption(ChannelOption<T> option, T value);
                owner.equals("io/netty/channel/ChannelConfig") &&
                name.equals("setOption") &&
                desc.equals("(Lio/netty/channel/ChannelOption;Ljava/lang/Object;)Z") &&
                this.option.equals("TCP_NODELAY")) {

            Helper.log("Setting TCP_NODELAY to true");
            this.mv.visitInsn(POP);
            this.mv.visitInsn(ICONST_1);
            this.mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
            this.mv.visitMethodInsn(opcode, owner, name, desc);
            return;
        }
        this.mv.visitMethodInsn(opcode, owner, name, desc);
    }
}
