package top.speedcubing.forge.mods.chatstacking.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ChatSpamStackTS implements IClassTransformer, Opcodes {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        if (transformedName.equals("net.minecraft.client.gui.GuiNewChat")) {
            ClassReader reader = new ClassReader(bytes);
            ClassWriter writer = new ClassWriter(reader, 0);
            ClassVisitor visitor = new GuiNewChatCV(writer);
            reader.accept(visitor, 0);
            return writer.toByteArray();
        }
        return bytes;
    }
}
