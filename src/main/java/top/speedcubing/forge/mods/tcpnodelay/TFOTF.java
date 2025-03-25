package top.speedcubing.forge.mods.tcpnodelay;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class TFOTF implements IClassTransformer, Opcodes {
    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        if (transformedName.startsWith("net.minecraft.network.NetworkManager$")) {
            ClassReader reader = new ClassReader(bytes);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
            ClassVisitor visitor = new NetworkManageCV(writer);
            reader.accept(visitor, ClassReader.SKIP_FRAMES);
            return writer.toByteArray();
        }
        return bytes;
    }
}
