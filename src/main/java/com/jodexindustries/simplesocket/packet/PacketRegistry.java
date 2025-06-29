package com.jodexindustries.simplesocket.packet;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PacketRegistry {
    private static final Map<Integer, Class<? extends Packet>> packetMap = new HashMap<>();
    private static final Map<Class<? extends Packet>, Integer> packetIdMap = new HashMap<>();

    public static void registerPacket(int id, Class<? extends Packet> packetClass) {
        packetMap.put(id, packetClass);
        packetIdMap.put(packetClass, id);
    }

    public static Packet createPacket(int id) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<? extends Packet> packetClass = packetMap.get(id);
        if (packetClass != null) {
            return packetClass.getDeclaredConstructor().newInstance();
        }
        throw new IllegalArgumentException("Unknown packet ID: " + id);
    }

    public static int getPacketId(Class<? extends Packet> packetClass) {
        return packetIdMap.get(packetClass);
    }

}
