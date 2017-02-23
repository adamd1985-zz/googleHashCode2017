package org.googlehashcode;

import org.googlehashcode.domain.Bag;
import org.googlehashcode.domain.Endpoint;
import org.googlehashcode.domain.Output;
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
		String[] filenames = { "kittens", "me_at_the_zoo", "trending_today", "videos_worth_spreading" };

		String filename = "trending_today";
		Bag bag = Parser.parse(filename + ".in");

		final BestCacheConfig bestCacheConfig = new BestCacheConfig();
		final Output output = bestCacheConfig.loadBestConfig(bag);
		System.out.println(output);

		OutputWriter.writeToFile(filename + ".out", output);
	}

	private static Bag simpleTestBag() {
		Bag bag = new Bag(2, 2, 4, 2, 400);
		bag.videoSizes.add(100);
		bag.videoSizes.add(200);
		Endpoint ep1 = new Endpoint(1000, 2);
		ep1.cacheLatency.put(0, 200);
		ep1.cacheLatency.put(1, 300);
		bag.endpoints.add(ep1);
		Endpoint ep2 = new Endpoint(1000, 2);
		ep2.cacheLatency.put(1, 200);
		ep2.cacheLatency.put(1, 300);
		bag.endpoints.add(ep2);
		VideoRequest req1 = new VideoRequest(0, 0, 1000);
		VideoRequest req2 = new VideoRequest(1, 1, 1000);
		VideoRequest req3 = new VideoRequest(2, 0, 1000);
		VideoRequest req4 = new VideoRequest(3, 1, 1000);
		bag.videoRequests.add(req1);
		bag.videoRequests.add(req2);
		bag.videoRequests.add(req3);
		bag.videoRequests.add(req4);
		return bag;
	}
}
