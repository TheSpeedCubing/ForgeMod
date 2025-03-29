package top.speedcubing.forge.mods.hurt.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import top.speedcubing.forge.mods.hurt.Hurt;

public class HurtCameraEffectMV extends MethodVisitor implements Opcodes {
    public HurtCameraEffectMV(MethodVisitor mv) {
        super(ASM4, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        // add after
        // f = MathHelper.sin(f * f * f * f * (float)Math.PI);
        // var3 = ns.a(var3 * var3 * var3 * var3 * 3.1415927F);
        if (opcode == Opcodes.INVOKESTATIC
                && owner.equals("ns")
                && name.equals("a")
                && descriptor.equals("(F)F")) {
            /*
              if (top.speedcubing.forge.mods.hurt.Hurt.getInstance().isShake()) {
                  return;
              }
            */
            mv.visitMethodInsn(INVOKESTATIC, "top/speedcubing/forge/mods/hurt/Hurt", "getInstance", "()Ltop/speedcubing/forge/mods/hurt/Hurt;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "top/speedcubing/forge/mods/hurt/Hurt", "isShake", "()Z", false);
            Label label1 = new Label();
            mv.visitJumpInsn(IFNE, label1);
            mv.visitInsn(RETURN);
            mv.visitLabel(label1);
        }
    }
}

