package top.speedcubing.forge.mods.freelook.asm;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class updateCameraAndRenderMV extends MethodVisitor implements Opcodes {
    private boolean secondTimeMet = false;

    public updateCameraAndRenderMV(MethodVisitor mv) {
        super(ASM4, mv);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if ((opcode == GETFIELD && desc.equals("Z") && name.equals("w")) || name.equals("inGameHasFocus")) {
            if (this.secondTimeMet) {
                visitMethodInsn(INVOKESTATIC, "top/speedcubing/forge/mods/freelook/FreeLook", "overrideMouse", "()Z", false);
            } else {
                this.secondTimeMet = true;
                super.visitFieldInsn(opcode, owner, name, desc);
            }
        } else {
            super.visitFieldInsn(opcode, owner, name, desc);
        }
    }
}
