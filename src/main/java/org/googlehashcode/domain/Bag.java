package org.googlehashcode.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magiccrafter on 2/23/2017.
 */
public class Bag {

    public final int videosNumber;
    public final int endpointsNumber;
    public final int requestDescriptionsNumber;
    public final int cacheServersNumber;

    public final int cacheServerCapacityMB;

    public final List<Integer> videoSizes = new ArrayList<>();
    public final List<Endpoint> endpoints = new ArrayList<>();
    public final List<VideoRequest> videoRequests = new ArrayList<>();


    public Bag(int videosNumber, int endpointsNumber, int requestDescriptionsNumber,
               int cacheServersNumber, int cacheServerCapacityMB) {
        this.videosNumber = videosNumber;
        this.endpointsNumber = endpointsNumber;
        this.requestDescriptionsNumber = requestDescriptionsNumber;
        this.cacheServersNumber = cacheServersNumber;
        this.cacheServerCapacityMB = cacheServerCapacityMB;
    }

    public Bag addVideoSizes(List<Integer> videoSizes) {
        this.videoSizes.addAll(videoSizes);
        return this;
    }

    public Bag addEndpoint(Endpoint endpoint) {
        this.endpoints.add(endpoint);
        return this;
    }

    public Bag addVideoRequest(VideoRequest videoRequest) {
        this.videoRequests.add(videoRequest);
        return this;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "videosNumber=" + videosNumber +
                ", endpointsNumber=" + endpointsNumber +
                ", requestDescriptionsNumber=" + requestDescriptionsNumber +
                ", cacheServersNumber=" + cacheServersNumber +
                ", cacheServerCapacityMB=" + cacheServerCapacityMB +
                ", videoSizes=" + videoSizes +
                ", endpoints=" + endpoints +
                ", videoRequests=" + videoRequests +
                '}';
    }
}
