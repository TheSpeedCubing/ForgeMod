package top.speedcubing.forge.mods.freelook.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class EntityRendererCV extends ClassVisitor implements Opcodes {

    public EntityRendererCV(ClassWriter writer) {
        super(ASM4, writer);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] ex) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, ex);
        // private void orientCamera(float partialTicks) { //(F)V
        if ((desc.equals("(F)V") && name.equals("f")) || name.equals("orientCamera"))
            return new OrientCameraMV(mv);
        // public void updateCameraAndRender(float partialTicks, long nanoTime) { //(FJ)V
        if ((desc.equals("(FJ)V") && name.equals("a")) || name.equals("updateCameraAndRender")) {
            return new updateCameraAndRenderMV(mv);
        }
        return mv;
    }
}
