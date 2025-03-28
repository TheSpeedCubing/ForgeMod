package top.speedcubing.forge.mods.bossbar.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class BossbarTF implements IClassTransformer, Opcodes {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        if (transformedName.equals("net.minecraft.client.gui.GuiIngame")) {
            ClassReader reader = new ClassReader(bytes);
            ClassWriter writer = new ClassWriter(reader, 0);
            ClassVisitor visitor = new GuiIngameCV(writer);
            reader.accept(visitor, 0);
            return writer.toByteArray();
        }
        return bytes;
    }
}
