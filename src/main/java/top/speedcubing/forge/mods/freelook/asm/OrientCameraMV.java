package top.speedcubing.forge.mods.freelook.asm;

import com.google.common.collect.Maps;
import java.util.HashMap;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class OrientCameraMV extends MethodVisitor implements Opcodes {
    private final HashMap<String, String> obfList = Maps.newHashMap();

    public OrientCameraMV(MethodVisitor mv) {
        super(ASM4, mv);
        this.obfList.put("y", "getCameraYaw");
        this.obfList.put("z", "getCameraPitch");
        this.obfList.put("A", "getCameraYaw");
        this.obfList.put("B", "getCameraPitch");
        this.obfList.put("rotationYaw", "getCameraYaw");
        this.obfList.put("rotationPitch", "getCameraPitch");
        this.obfList.put("prevRotationYaw", "getCameraYaw");
        this.obfList.put("prevRotationPitch", "getCameraPitch");
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        super.visitFieldInsn(opcode, owner, name, desc);
        if ((opcode == GETFIELD && desc.equals("F") && owner.equals("pk")) || owner.equals("net/minecraft/entity/Entity"))
            if (this.obfList.containsKey(name)) {
                visitInsn(FCONST_0);
                visitInsn(FMUL);
                visitMethodInsn(INVOKESTATIC, "top/speedcubing/forge/mods/freelook/FreeLook", this.obfList.get(name), "()F", false);
                visitInsn(FADD);
            }
    }
}
