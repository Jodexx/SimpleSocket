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
        if (!in.isReadable(1)) return;

        in.markReaderIndex();

        byte magic = in.readByte();
        if (magic != (byte) 0xAB3C0DE) {
            in.resetReaderIndex(); // not this packet
            ctx.fireChannelRead(in.readRetainedSlice(in.readableBytes()));
            return;
        }

        if (in.readableBytes() < 4) {
            in.resetReaderIndex();
            return;
        }

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
