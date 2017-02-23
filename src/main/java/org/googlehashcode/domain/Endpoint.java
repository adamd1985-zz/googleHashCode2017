package org.googlehashcode.domain;

/**
 * Created by magiccrafter on 2/23/2017.
 */
public class Endpoint {

    public final int datacenterLatencyMS;
    public final int connectedCaches;

    public Endpoint(int datacenterLatencyMS, int connectedCaches) {
        this.datacenterLatencyMS = datacenterLatencyMS;
        this.connectedCaches = connectedCaches;
    }
}
