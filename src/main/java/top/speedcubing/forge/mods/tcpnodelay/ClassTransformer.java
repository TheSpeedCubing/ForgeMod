package top.speedcubing.forge.mods.tcpnodelay;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class ClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        if (transformedName.startsWith("net.minecraft.network.NetworkManager$")) {
            try {
                ClassReader reader = new ClassReader(bytes);
                ClassWriter writer = new ClassWriter(reader, 2);
                ClassVisitor visitor = new NetworkManagerInnerVisitor(writer);
                reader.accept(visitor, 4);
                return writer.toByteArray();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return bytes;
    }
}
