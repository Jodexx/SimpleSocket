package com.jodexindustries.simplesocket.packet;

import java.util.concurrent.ConcurrentHashMap;

public class PacketHandlerMap extends ConcurrentHashMap<Class<? extends Packet>, PacketHandler<? extends Packet>> {

    public <T extends Packet> void registerHandler(Class<? extends Packet> packetClass, PacketHandler<T> handler) {
        this.put(packetClass, handler);
    }

    @SuppressWarnings("unchecked")
    public <T extends Packet> PacketHandler<T> getHandler(Class<? extends Packet> packetClass) {
        return (PacketHandler<T>) this.get(packetClass);
    }
}