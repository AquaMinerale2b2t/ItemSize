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

import java.text.DecimalFormat;
import java.util.List;


public class ByteSize {
    ItemStack stack;
    String result;
    DecimalFormat f = new DecimalFormat("##.00");
    @SubscribeEvent
    public void eventRenderTooltip(ItemTooltipEvent event) { //ItemTooltipEvent   RenderTooltipEvent.PostText event
        stack = event.getItemStack();
        result=execute();
        event.getToolTip().add(execute());
    }

    public String execute() {
        if(stack.equals(null)){
            return "null";
        }
        if(stack.isEmpty()) return "invalid tag";
        if(ByteSize.getItemSize(stack)<1024){
            return Integer.toString(ByteSize.getItemSize(stack)) + " bytes";
        }else{
            return Double.toString(
                    Double.parseDouble(f.format((double) ByteSize.getItemSize(stack)/1024))
            ) + " kilobytes";
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
