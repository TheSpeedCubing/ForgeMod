package top.speedcubing.forge.mods.chatstacking;

import net.minecraft.command.ICommandSender;
import top.speedcubing.forge.mods.CubingCommandBase;

class CommandChatStacking extends CubingCommandBase {

    public CommandChatStacking() {
        super("chatstacking", "/chatstacking\n/chatstacking <format...>");
    }

    @Override
    public void execute(ICommandSender sender, String[] s) {
        if(s.length == 0) {
            ChatStacking.getInstance().toggle();
            return;
        }
        StringBuilder builder = new StringBuilder();
        for(String str : s) {
            builder.append(" ").append(str);
        }
        String result = builder.toString();
        if(result.length() != 0) {
            result = result.substring(1);
        }
        ChatStacking.getInstance().setFormat(result);
    }
}