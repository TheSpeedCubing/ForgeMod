package top.speedcubing.forge.mods.chatstacking;

import java.util.List;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import top.speedcubing.forge.module.ToggleableModule;

public class ChatStacking extends ToggleableModule {

    private static ChatStacking instance;

    public ChatStacking() {
        super("ChatStacking", new CommandChatStacking());
        instance = this;
    }

    public static ChatStacking getInstance() {
        return instance;
    }

    private String format = "%s [%d]";

    public void setFormat(String format) {
        this.format = format;
    }

    private String lastText = "";
    private int sameTextCount = 0;

    // for INVOKESTATIC

    public static void setChatLine(List<ChatLine> drawnChatLines, int index, ChatLine chatLine) {
        int updateCounter = chatLine.getUpdatedCounter();
        IChatComponent ichatcomponent = chatLine.getChatComponent();
        int chatLineId = chatLine.getChatLineID();
        if (ChatStacking.getInstance().isEnabled()) {
            if (ChatStacking.getInstance().lastText.equals(ichatcomponent.getFormattedText())) {
                ChatStacking.getInstance().sameTextCount++;
                drawnChatLines.set(index, new ChatLine(updateCounter, new ChatComponentText(String.format(ChatStacking.getInstance().format, ichatcomponent.getFormattedText(), ChatStacking.getInstance().sameTextCount)), chatLineId));
            } else {
                drawnChatLines.add(index, chatLine);
                ChatStacking.getInstance().sameTextCount = 1;
            }
        } else
            drawnChatLines.add(index, chatLine);
        ChatStacking.getInstance().lastText = ichatcomponent.getFormattedText();
    }
}