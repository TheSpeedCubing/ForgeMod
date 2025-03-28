package top.speedcubing.forge.mods.hurt.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class CameraEffectTF implements IClassTransformer, Opcodes {
    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        if (transformedName.equals("net.minecraft.client.renderer.EntityRenderer")) {
            ClassReader reader = new ClassReader(bytes);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
            ClassVisitor visitor = new EntityRendererCV(writer);
            reader.accept(visitor, ClassReader.SKIP_FRAMES);
            return writer.toByteArray();
        }
        return bytes;
    }
}
