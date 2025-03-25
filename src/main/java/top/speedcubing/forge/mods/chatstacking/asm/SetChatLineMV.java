package top.speedcubing.forge.mods.chatstacking.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SetChatLineMV extends MethodVisitor implements Opcodes{
    public SetChatLineMV(MethodVisitor mv) {
        super(ASM4, mv);
    }

    @Override
    public void visitCode() {
        visitVarInsn(ALOAD, 1);
        visitVarInsn(ILOAD, 2);
        visitVarInsn(ILOAD, 3);
        visitVarInsn(ILOAD, 4);
        visitMethodInsn(INVOKESTATIC, "top/speedcubing/forge/mods/chatstacking/ChatStacking",
                "setChatLine", "(Leu;IIZ)V", false);
        visitInsn(RETURN);
    }
}

