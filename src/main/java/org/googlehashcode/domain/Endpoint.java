package org.googlehashcode.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by magiccrafter on 2/23/2017.
 */
public class Endpoint {
	public int id;
    public final int datacenterLatencyMS;
    public final int connectedCaches;
    
    public long totalReqs = 0;
    
    // <cache id, latency>
    public final Map<Integer, Integer> cacheLatency = new HashMap<>();

    public Endpoint(int datacenterLatencyMS, int connectedCaches) {
        this.datacenterLatencyMS = datacenterLatencyMS;
        this.connectedCaches = connectedCaches;
    }

    public Endpoint addCacheLatency(int cacheId, int latencyMS) {
        this.cacheLatency.put(cacheId, latencyMS);
        return this;
    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "datacenterLatencyMS=" + datacenterLatencyMS +
                ", connectedCaches=" + connectedCaches +
                ", cacheLatency=" + cacheLatency +
                '}';
    }
}
