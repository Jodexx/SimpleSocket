package com.jodexindustries.simplesocket.packet;

import io.netty.buffer.ByteBuf;

public interface Encoder {
    void encode(ByteBuf out);
}
