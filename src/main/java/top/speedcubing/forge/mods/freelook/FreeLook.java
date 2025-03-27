package top.speedcubing.forge.mods.freelook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import top.speedcubing.forge.module.ToggleableModule;
import top.speedcubing.forge.util.Helper;

public class FreeLook extends ToggleableModule {

    private static FreeLook instance;

    private final KeyBinding keyBinding = new KeyBinding("Toggle Perspective", 33, "speedcubing");
    private float cameraYaw = 0.0F;

    private float cameraPitch = 0.0F;
    private int previousPerspective = 0;
    private boolean holdMode = true;

    public FreeLook() {
        super("FreeLook", new CommandFreeLook());
        ClientRegistry.registerKeyBinding(keyBinding);
        instance = this;
    }

    public static FreeLook getInstance() {
        return instance;
    }

    @Override
    public String getStateDisplayString() {
        return "";
    }

    public void holdMode() {
        this.holdMode = true;
        send(" is now hold mode");
    }

    public void toggleMode() {
        this.holdMode = false;
        send(" is now toggle mode");
    }

    public void handleKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKey() == keyBinding.getKeyCode()) {
            if (Keyboard.getEventKeyState()) {
                toggleSilent();
                cameraYaw = Helper.getPlayer().rotationYaw;
                cameraPitch = Helper.getPlayer().rotationPitch;
                if (isEnabled()) {
                    previousPerspective = Helper.getGameSettings().thirdPersonView;
                    Helper.getGameSettings().thirdPersonView = 1;
                } else {
                    Helper.getGameSettings().thirdPersonView = previousPerspective;
                }
            } else if (holdMode) {
                disable();
                Helper.getGameSettings().thirdPersonView = previousPerspective;
            }
        }
        if (Keyboard.getEventKey() == Helper.getGameSettings().keyBindTogglePerspective.getKeyCode()) {
            disable();
        }
    }

    // for INVOKESTATIC
    public static boolean overrideMouse() {
        if (Minecraft.getMinecraft().inGameHasFocus && Display.isActive()) {
            if (!FreeLook.getInstance().isEnabled())
                return true;
            Minecraft.getMinecraft().mouseHelper.mouseXYChange();
            float f1 = Helper.getGameSettings().mouseSensitivity * 0.6F + 0.2F;
            float f2 = f1 * f1 * f1 * 8.0F;
            float f3 = Minecraft.getMinecraft().mouseHelper.deltaX * f2;
            float f4 = Minecraft.getMinecraft().mouseHelper.deltaY * f2;
            FreeLook.getInstance().cameraYaw += f3 * 0.15F;
            FreeLook.getInstance().cameraPitch += f4 * 0.15F;
            if (FreeLook.getInstance().cameraPitch > 90.0F)
                FreeLook.getInstance().cameraPitch = 90.0F;
            if (FreeLook.getInstance().cameraPitch < -90.0F)
                FreeLook.getInstance().cameraPitch = -90.0F;
        }
        return false;
    }

    public static float getCameraYaw() {
        return FreeLook.getInstance().isEnabled() ? FreeLook.getInstance().cameraYaw : Helper.getPlayer().rotationYaw;
    }

    public static float getCameraPitch() {
        return FreeLook.getInstance().isEnabled() ? FreeLook.getInstance().cameraPitch : Helper.getPlayer().rotationPitch;
    }
}
