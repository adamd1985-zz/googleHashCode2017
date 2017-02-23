package org.googlehashcode;

import org.googlehashcode.domain.Bag;
import org.googlehashcode.domain.Endpoint;
import org.googlehashcode.domain.VideoRequest;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Entry class. </br>
 * Google helper libs:
 * <ul>
 * <li>https://google.github.io/guava/releases/snapshot/api/docs/com/google/common/base/package-summary.html</li>
 * <li>https://google.github.io/guava/releases/snapshot/api/docs/com/google/common/collect/package-summary.html</li>
 * <li>https://google.github.io/guava/releases/snapshot/api/docs/com/google/common/graph/package-summary.html</li>
 * <li>https://google.github.io/guava/releases/snapshot/api/docs/com/google/common/io/package-summary.html</li>
 * </ul>
 * </br>
 * Apache helper libs:
 * <ul>
 * <li>https://commons.apache.org/proper/commons-chain/</li>
 * <li>https://commons.apache.org/proper/commons-collections/</li>
 * <li>https://commons.apache.org/proper/commons-configuration/</li>
 * <li>https://commons.apache.org/proper/commons-exec/</li>
 * <li>https://commons.apache.org/proper/commons-io/</li>
 * <li>https://commons.apache.org/proper/commons-math/</li>
 * <li>https://commons.apache.org/proper/commons-text/</li>
 * </ul>
 * 
 * @author Adam Darmanin
 * @author Nasko Vasilev
 * @version 1
 */
public class Main {

	/**
	 * Entry method.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws IOException, URISyntaxException {
		/*
		 * File file = Paths.get(Resources.getResource("test.in").toURI()).normalize().toFile();
		 * 
		 * ImmutableList<String> lines = Files .asCharSource(file, Charsets.UTF_8) .readLines();
		 * 
		 * Bag bag = null; System.out.println(String.format("Lines read: %s", lines.size())); for
		 * (int i = 0; i < lines.size(); i++) { String line = lines.get(i); if (i == 0) { Integer[]
		 * elems = Parser.integerArray(line); bag = new Bag(elems[0], elems[1], elems[2], elems[3],
		 * elems[4]); } else if (i == 1) { Integer[] videoSizes = Parser.integerArray(line);
		 * bag.addVideoSizes(Arrays.stream(videoSizes).collect(Collectors.toList())); } else { //
		 * Endpoints
		 * 
		 * } } System.out.println(bag);
		 */
		Parser.parse("test.in");

		Bag bag1 = new Bag(2, 2, 4, 2, 400);
		bag1.videoSizes.add(100);
		bag1.videoSizes.add(200);
		Endpoint ep1 = new Endpoint(1000, 2);
		ep1.cacheLatency.put(0, 200);
		ep1.cacheLatency.put(1, 300);
		bag1.endpoints.add(ep1);
		Endpoint ep2 = new Endpoint(1000, 2);
		ep2.cacheLatency.put(1, 200);
		ep2.cacheLatency.put(1, 300);
		bag1.endpoints.add(ep2);
		VideoRequest req1 = new VideoRequest(0, 0, 1000);
		VideoRequest req2 = new VideoRequest(1, 1, 1000);
		VideoRequest req3 = new VideoRequest(2, 0, 1000);
		VideoRequest req4 = new VideoRequest(3, 1, 1000);
		bag1.videoRequests.add(req1);
		bag1.videoRequests.add(req2);
		bag1.videoRequests.add(req3);
		bag1.videoRequests.add(req4);

		final BestCacheConfig bestCacheConfig = new BestCacheConfig();
		final long score = bestCacheConfig.loadBestConfig(bag1);
		System.out.println(score);
	}
}
