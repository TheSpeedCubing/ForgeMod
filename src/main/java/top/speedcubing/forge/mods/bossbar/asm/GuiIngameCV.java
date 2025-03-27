package top.speedcubing.forge.mods.bossbar.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class GuiIngameCV extends ClassVisitor implements Opcodes {

    public GuiIngameCV(ClassVisitor classVisitor) {
        super(ASM4, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] ex) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, ex);
        // private void renderBossHealth()
        if ((desc.equals("()V") && name.equals("j")) || name.equals("renderBossHealth")) {
            return new RenderBossHealthMV(mv);
        }
        return mv;
    }
}
