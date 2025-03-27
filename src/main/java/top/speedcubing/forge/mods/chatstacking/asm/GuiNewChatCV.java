package top.speedcubing.forge.mods.chatstacking.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class GuiNewChatCV extends ClassVisitor implements Opcodes {

    public GuiNewChatCV(ClassVisitor classVisitor) {
        super(ASM4, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] ex) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, ex);
        // private void setChatLine(IChatComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly)
        if ((desc.equals("(Leu;IIZ)V") && name.equals("a")) || name.equals("setChatLine")) {
            return new SetChatLineMV(mv);
        }
        return mv;
    }
}
