package org.googlehashcode;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.googlehashcode.domain.Bag;
import org.googlehashcode.domain.Endpoint;
import org.googlehashcode.domain.VideoRequest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by magiccrafter on 2/23/2017.
 */
public class Parser {

    public static Bag parse(String filename) throws URISyntaxException, IOException {
        File file = Paths.get(Resources.getResource(filename).toURI()).normalize().toFile();

        ImmutableList<String> lines = Files
                .asCharSource(file, Charsets.UTF_8)
                .readLines();

        Bag bag = null;
        System.out.println(String.format("Lines read: %s", lines.size()));
        for (int i = 0; i < 2; i++) {
            String line = lines.get(i);
            if (i == 0) {
                Integer[] elems = Parser.integerArray(line);
                bag = new Bag(elems[0],elems[1],elems[2],elems[3],elems[4]);
            } else if (i == 1) {
                Integer[] videoSizes = Parser.integerArray(line);
                bag.addVideoSizes(Arrays.stream(videoSizes).collect(Collectors.toList()));
            }
        }

        int step = 2;

        // Endpoints
        int endpointsCnt = 0;
        while (endpointsCnt < bag.endpointsNumber) {
            String line = lines.get(step);
//            System.out.println(line);

            Integer[] info = Parser.integerArray(line);
            int endpointLatency = info[0];
            int endpointCacheSize = info[1];

            Endpoint endpoint = new Endpoint(endpointLatency, endpointCacheSize);
            int i = 0;
            while (i < endpointCacheSize) {
                step++;
                i++;
                Integer[] endpointMeta = Parser.integerArray(lines.get(step));
                endpoint.addCacheLatency(endpointMeta[0], endpointMeta[1]);
            }
            bag.addEndpoint(endpoint);

            step++;
            endpointsCnt++;
        }

        // Video Requests
        while (step < lines.size()) {
            String line = lines.get(step);
//            System.out.println(line);

            Integer[] info = Parser.integerArray(line);
            VideoRequest videoRequest = new VideoRequest(info[0], info[1], info[2]);
            bag.addVideoRequest(videoRequest);
            step++;
        }

//        System.out.println(bag);

        return bag;
    }

    public static Integer[] integerArray(String line) {
        return Arrays.stream(line.split(" ")).map(s -> Integer.valueOf(s)).toArray(Integer[]::new);
    }
}
