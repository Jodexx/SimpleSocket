package com.jodexindustries.simplesocket.io;

import com.jodexindustries.simplesocket.packet.Packet;
import com.jodexindustries.simplesocket.packet.PacketRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        out.writeByte(0xAB3C0DE); // magic byte
        int startIndex = out.writerIndex();
        out.writeInt(0);

        out.writeLong(packet.getSessionId());
        int packetId = PacketRegistry.getPacketId(packet.getClass());
        out.writeInt(packetId);
        packet.encode(out);

        int endIndex = out.writerIndex();
        out.setInt(startIndex, endIndex - startIndex - 4);
    }

}
