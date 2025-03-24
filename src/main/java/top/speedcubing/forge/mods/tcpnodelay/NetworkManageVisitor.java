package top.speedcubing.forge.mods.tcpnodelay;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import top.speedcubing.forge.util.Helper;

public class NetworkManageVisitor extends ClassVisitor {
    public NetworkManageVisitor(ClassVisitor classVisitor) {
        super(262144, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals("initChannel"))
            return new MethodVisitor(262144, mv) {
                private String option;

                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                    if (opcode == 178 && owner
                            .equals("io/netty/channel/ChannelOption") && desc
                            .equals("Lio/netty/channel/ChannelOption;"))
                        this.option = name;
                    this.mv.visitFieldInsn(opcode, owner, name, desc);
                }

                @Override
                public void visitMethodInsn(int opcode, String owner, String name, String desc) {
                    if (opcode == 185 && owner
                            .equals("io/netty/channel/ChannelConfig") && name
                            .equals("setOption") && desc
                            .equals("(Lio/netty/channel/ChannelOption;Ljava/lang/Object;)Z") &&
                            this.option.equals("TCP_NODELAY")) {
                        Helper.log("Setting TCP_NODELAY to true");
                        this.mv.visitInsn(87);
                        this.mv.visitInsn(4);
                        this.mv.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
                        this.mv.visitMethodInsn(opcode, owner, name, desc);
                        return;
                    }
                    this.mv.visitMethodInsn(opcode, owner, name, desc);
                }
            };
        return mv;
    }
}
