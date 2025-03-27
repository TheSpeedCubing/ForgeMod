package top.speedcubing.forge.mods.bossbar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;
import top.speedcubing.forge.module.Module;

public class Bossbar extends Module {

    private static Bossbar instance;

    public Bossbar() {
        super("Bossbar", new CommandBossbar());

        instance = this;
    }

    public static Bossbar getInstance() {
        return instance;
    }

    @Override
    public String getStateDisplayString() {
        return "§6bar: (" + (bar ? "§aon" : "§coff") + "§6), text: §6(" + (text ? "§aon" : "§coff") + "§6)";
    }

    private boolean text = true;
    private boolean bar = true;

    public void toggleText() {
        this.text = !this.text;
    }

    public boolean isTextOn() {
        return this.text;
    }

    public void toggleBar() {
        this.bar = !this.bar;
    }

    public boolean isBarOn() {
        return this.bar;
    }

    // for INVOKESTATIC

    public static void renderBossHealth(GuiIngame inst) {
        System.out.println("RENDER");
        if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
            --BossStatus.statusBarTime;
            ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
            int i = scaledresolution.getScaledWidth();
            int j = 182;
            int k = i / 2 - j / 2;
            int l = (int) (BossStatus.healthScale * (float) (j + 1));
            int i1 = 12;
            if (Bossbar.getInstance().bar) {
                inst.drawTexturedModalRect(k, i1, 0, 74, j, 5);
                inst.drawTexturedModalRect(k, i1, 0, 74, j, 5);

                if (l > 0) {
                    inst.drawTexturedModalRect(k, i1, 0, 79, l, 5);
                }

            }
            if (Bossbar.getInstance().text) {
                String s = BossStatus.bossName;
                inst.getFontRenderer().drawStringWithShadow(s, (float) (i / 2 - inst.getFontRenderer().getStringWidth(s) / 2), (float) (i1 - 10), 16777215);
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
        }
    }
}
