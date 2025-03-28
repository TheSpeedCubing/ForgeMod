package top.speedcubing.forge.mods.chatstacking.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class SetChatLineMV extends MethodVisitor implements Opcodes {
    public SetChatLineMV(MethodVisitor mv) {
        super(ASM4, mv);
    }

    boolean meetOnce = false;

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        // replace first
        // this.drawnChatLines.add(0, new ChatLine(updateCounter, ichatcomponent, chatLineId));
        if (opcode == Opcodes.INVOKEINTERFACE
                && owner.equals("java/util/List")
                && name.equals("add")
                && descriptor.equals("(ILjava/lang/Object;)V") && !meetOnce) {
            meetOnce = true;
            // (Ljava/util/List;ILnet/minecraft/client/gui/ChatLine;)V
            mv.visitMethodInsn(INVOKESTATIC, "top/speedcubing/forge/mods/chatstacking/ChatStacking", "setChatLine", "(Ljava/util/List;ILava;)V", false);
            return;
        }
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }
}

