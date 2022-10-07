package com.example.examplemod;

import io.netty.buffer.Unpooled;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.text.DecimalFormat;

//author: Mineral water#4543

public class ByteSize {
    ItemStack stack;
    DecimalFormat f = new DecimalFormat("##.00");
    @SubscribeEvent
    public void eventRenderTooltip(ItemTooltipEvent event) { //ItemTooltipEvent   RenderTooltipEvent.PostText event
        stack = event.getItemStack();
        event.getToolTip().add(getTag());
    }
    public String getTag() {
        if(stack.isEmpty()) return "invalid tag";
        if(ByteSize.getItemSize(stack)<1024){
            return ByteSize.getItemSize(stack) + " bytes";
        }else{
            return Double.parseDouble(f.format((double) ByteSize.getItemSize(stack)/1024)) + " kilobytes";
        }
    }
    public static int getItemSize(ItemStack stack) {
        PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
        buffer.writeItemStack(stack);
        buffer.release();
        return buffer.writerIndex();
    }
}
