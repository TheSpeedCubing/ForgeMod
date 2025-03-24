package top.speedcubing.forge.mods.freelook.asm;

import com.google.common.collect.Maps;
import java.util.HashMap;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OrientCameraVisitor extends MethodVisitor implements Opcodes {
    private final HashMap<String, String> obfList = Maps.newHashMap();

    public OrientCameraVisitor(MethodVisitor mv) {
        super(262144, mv);
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
        if ((opcode == 180 && desc.equals("F") && owner.equals("pk")) || owner.equals("net/minecraft/entity/Entity"))
            if (this.obfList.containsKey(name)) {
                visitInsn(11);
                visitInsn(106);
                visitMethodInsn(184, "top/speedcubing/forge/mods/freelook/FreeLook", this.obfList.get(name), "()F", false);
                visitInsn(98);
            }
    }
}
