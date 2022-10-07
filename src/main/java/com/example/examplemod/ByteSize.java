package com.example.examplemod;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.util.List;


public class ByteSize {
    ItemStack stack;
    String result;
    @SubscribeEvent
    public void eventRenderTooltip(RenderTooltipEvent.PostText event) { //ItemTooltipEvent
        stack = event.getStack();
        result=execute();
        if(Keyboard.isKeyDown(Keyboard.KEY_V) && Minecraft.getMinecraft().player.ticksExisted%3==0 && (Minecraft.getMinecraft().currentScreen != null)){
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(result));
        }
    }
    //@SubscribeEvent
    //public void eventItemTooltip(ItemTooltipEvent event){
    //    List tooltip = event.getToolTip();
    //    tooltip.add(2, result);
    //    Minecraft.getMinecraft().player.sendChatMessage(tooltip.toString());
    //}
    public String execute() {
        //ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
        if(stack.equals(null)){
            return "null";
        }
        if(stack.isEmpty()) return "invalid tag";
        if(ByteSize.getItemSize(stack)<1024){
            return Integer.toString(ByteSize.getItemSize(stack)) + " bytes";
        }else{
            return Double.toString((double)ByteSize.getItemSize(stack) /1024) + " kilobytes";
        }
    }
    public static int getItemSize(ItemStack stack) {
        PacketBuffer buff = new PacketBuffer(Unpooled.buffer());
        buff.writeItemStack(stack);
        int size = buff.writerIndex();
        buff.release();
        return size;
    }
}
