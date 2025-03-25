package top.speedcubing.forge.mods.chatstacking;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import top.speedcubing.forge.module.ToggleableModule;
import top.speedcubing.lib.utils.ReflectionUtils;

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

    // net.minecraft.client.gui.GuiNewChat : private void setChatLine(IChatComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly)
    public static void setChatLine(IChatComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly) {
        GuiNewChat inst = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        if (chatLineId != 0) {
            inst.deleteChatLine(chatLineId);
        }

        int i = MathHelper.floor_float((float) inst.getChatWidth() / inst.getChatScale());
        List<IChatComponent> list = GuiUtilRenderComponents.splitText(chatComponent, i, Minecraft.getMinecraft().fontRendererObj, false, false);
        boolean flag = inst.getChatOpen();

        // chatLines
        List<ChatLine> chatLines = (List<ChatLine>) ReflectionUtils.getField(inst, "field_146252_h");
        // drawnChatLines
        List<ChatLine> drawnChatLines = (List<ChatLine>) ReflectionUtils.getField(inst, "field_146253_i");

        for (IChatComponent ichatcomponent : list) {
            if (flag && (int) ReflectionUtils.getField(inst, "field_146250_j") > 0) { // scrollPos
                ReflectionUtils.setField(inst, "field_146251_k", true); // isScrolled
                inst.scroll(1);
            }

            if (ChatStacking.getInstance().isEnabled()) {
                if (ChatStacking.getInstance().lastText.equals(ichatcomponent.getFormattedText())) {
                    ChatStacking.getInstance().sameTextCount++;
                    drawnChatLines.set(0, new ChatLine(updateCounter, new ChatComponentText(String.format(ChatStacking.getInstance().format, ichatcomponent.getFormattedText(), ChatStacking.getInstance().sameTextCount)), chatLineId));
                } else {
                    drawnChatLines.add(0, new ChatLine(updateCounter, ichatcomponent, chatLineId));
                    ChatStacking.getInstance().sameTextCount = 1;
                }
            } else
                drawnChatLines.add(0, new ChatLine(updateCounter, ichatcomponent, chatLineId));
            ChatStacking.getInstance().lastText = ichatcomponent.getFormattedText();
        }

        while (drawnChatLines.size() > 100) {
            drawnChatLines.remove(drawnChatLines.size() - 1);
        }

        if (!displayOnly) {
            chatLines.add(0, new ChatLine(updateCounter, chatComponent, chatLineId));

            while (chatLines.size() > 100) {
                chatLines.remove(chatLines.size() - 1);
            }
        }
    }
}