package com.aby.roomforall.dto;

import java.io.Serializable;

public class Trip implements Serializable {

    private Planning outbound;
    private Planning inbound;
    private boolean roundtrip = false;

    public Trip() {
    }

    public Trip(Planning outbound, Planning inbound, boolean roundtrip) {
        this.outbound = outbound;
        this.inbound = inbound;
        this.roundtrip = roundtrip;
    }

    public Planning getOutbound() {
        return outbound;
    }

    public void setOutbound(Planning outbound) {
        this.outbound = outbound;
    }

    public Planning getInbound() {
        return inbound;
    }

    public void setInbound(Planning inbound) {
        this.inbound = inbound;
    }

    public boolean isRoundtrip() {
        return roundtrip;
    }

    public void setRoundtrip(boolean roundtrip) {
        this.roundtrip = roundtrip;
    }
}
