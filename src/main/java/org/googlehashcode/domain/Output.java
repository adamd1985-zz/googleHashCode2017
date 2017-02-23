package org.googlehashcode.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by magiccrafter on 2/23/2017.
 */
public class Output {

    public int serversNumber;
    public final Map<Integer, List<Integer>> cacheServers = new HashMap<>();

    public Output(List<Integer> serverIds) {
        serverIds.forEach(id -> cacheServers.put(id, new ArrayList<>()));
        serversNumber = serverIds.size();
    }

    public void addVideo(int serverId, int videoId) {
        cacheServers.get(serverId).add(videoId);
    }

    public void removeVideo(int serverId, int videoId) {
        List<Integer> updated = cacheServers.get(serverId)
                .stream().filter(v -> v != videoId).collect(Collectors.toList());
        cacheServers.put(serverId, updated);
    }

    @Override
    public String toString() {
        return "Output{" +
                "serversNumber=" + serversNumber +
                ", cacheServers=" + cacheServers +
                '}';
    }
}
