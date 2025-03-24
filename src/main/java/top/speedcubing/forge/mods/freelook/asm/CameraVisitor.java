package top.speedcubing.forge.mods.freelook.asm;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CameraVisitor extends MethodVisitor implements Opcodes {
    private boolean secondTimeMet = false;

    public CameraVisitor(MethodVisitor mv) {
        super(262144, mv);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if ((opcode == 180 && desc.equals("Z") && name.equals("w")) || name.equals("inGameHasFocus")) {
            if (this.secondTimeMet) {
                visitMethodInsn(184, "top/speedcubing/forge/mods/freelook/FreeLook", "overrideMouse", "()Z", false);
            } else {
                this.secondTimeMet = true;
                super.visitFieldInsn(opcode, owner, name, desc);
            }
        } else {
            super.visitFieldInsn(opcode, owner, name, desc);
        }
    }
}
