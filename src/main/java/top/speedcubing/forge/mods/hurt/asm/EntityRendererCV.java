package top.speedcubing.forge.mods.hurt.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
class EntityRendererCV  extends ClassVisitor implements Opcodes {

    public EntityRendererCV(ClassVisitor classVisitor) {
        super(ASM4, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] ex) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, ex);
        // private void hurtCameraEffect(float partialTicks)
        if ((desc.equals("(F)V") && name.equals("d")) || name.equals("hurtCameraEffect")) {
            return new HurtCameraEffectMV(mv);
        }
        return mv;
    }
}
