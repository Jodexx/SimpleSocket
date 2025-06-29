package com.jodexindustries.simplesocket.packet;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Packet implements Encoder, Decoder {

    private long sessionId = ThreadLocalRandom.current().nextLong();

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public long getSessionId() {
        return sessionId;
    }
}