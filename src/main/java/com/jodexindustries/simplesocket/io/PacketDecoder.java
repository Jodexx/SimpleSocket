package com.jodexindustries.simplesocket.io;

import com.jodexindustries.simplesocket.packet.Packet;
import com.jodexindustries.simplesocket.packet.PacketRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) return;

        in.markReaderIndex();

        int packetLength = in.readInt();
        if (in.readableBytes() < packetLength) {
            in.resetReaderIndex();
            return;
        }

        long sessionId = in.readLong();
        int packetId = in.readInt();

        Packet packet = PacketRegistry.createPacket(packetId);
        packet.setSessionId(sessionId);

        packet.decode(in);
        out.add(packet);
    }
}
