package top.speedcubing.forge.mods.freelook.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class FreeLookTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (transformedName.equals("net.minecraft.client.renderer.EntityRenderer")) {
            ClassReader reader = new ClassReader(bytes);
            ClassWriter writer = new ClassWriter(2);
            ClassVisitor visitor = new ClassVisitor(262144, writer) {

                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] ex) {
                    MethodVisitor mv = super.visitMethod(access, name, desc, signature, ex);
                    if ((desc.equals("(F)V") && name.equals("f")) || name.equals("orientCamera"))
                        return new OrientCameraVisitor(mv);
                    if ((desc.equals("(FJ)V") && name.equals("a")) || name.equals("updateCameraAndRender")) {
                        System.out.println("Replaced updateCameraAndRender");
                        return new CameraVisitor(mv);
                    }
                    return mv;
                }
            };
            reader.accept(visitor, 0);
            return writer.toByteArray();
        }
        return bytes;
    }
}
