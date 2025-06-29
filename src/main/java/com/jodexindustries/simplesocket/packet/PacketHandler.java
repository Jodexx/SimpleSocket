package com.jodexindustries.simplesocket.packet;

import io.netty.channel.ChannelHandlerContext;

public interface PacketHandler<T extends Packet> {
    void handle(ChannelHandlerContext ctx, T packet);
}
