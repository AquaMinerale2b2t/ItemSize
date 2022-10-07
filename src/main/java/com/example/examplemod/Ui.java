package com.example.examplemod;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class Ui {
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e){
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer ft = mc.fontRenderer;
        ByteSize bslol = new ByteSize();
        ScaledResolution sr = new ScaledResolution(mc);
        final int[] counter = {1};
        if (mc.gameSettings.showDebugInfo==true){
            return;
        }

        switch (e.getType()){
            case TEXT:
                //ft.drawStringWithShadow("Item size: "+ bslol.result, sr.getScaledWidth()/3, 7, rainbow(counter[0]*300));

                default:
                    break;
        }



    }
    public static int rainbow(int delay){
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20);
        rainbowState %=360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
    }
}
