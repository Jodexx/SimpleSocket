package com.jodexindustries.simplesocket.packet;

import io.netty.buffer.ByteBuf;

public interface Decoder {
    void decode(ByteBuf in);
}
