package org.googlehashcode.domain;

/**
 * Created by magiccrafter on 2/23/2017.
 */
public class VideoRequest {

    public final int videoId;
    public final int endpointId;
    public final int requestsNumber;

    public VideoRequest(int videoId, int endpointId, int requestsNumber) {
        this.videoId = videoId;
        this.endpointId = endpointId;
        this.requestsNumber = requestsNumber;
    }

    @Override
    public String toString() {
        return "VideoRequest{" +
                "videoId=" + videoId +
                ", endpointId=" + endpointId +
                ", requestsNumber=" + requestsNumber +
                '}';
    }
}
