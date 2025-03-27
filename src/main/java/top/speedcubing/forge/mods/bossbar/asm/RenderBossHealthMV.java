package top.speedcubing.forge.mods.bossbar.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class RenderBossHealthMV extends MethodVisitor implements Opcodes {
    public RenderBossHealthMV(MethodVisitor mv) {
        super(ASM4, mv);
    }
    @Override
    public void visitCode() {
        visitVarInsn(Opcodes.ALOAD, 0);
        // (Lnet/minecraft/client/gui/GuiIngame;)V
        visitMethodInsn(INVOKESTATIC, "top/speedcubing/forge/mods/bossbar/Bossbar", "renderBossHealth", "(Lavo;)V", false);
        visitInsn(RETURN);
    }
}
