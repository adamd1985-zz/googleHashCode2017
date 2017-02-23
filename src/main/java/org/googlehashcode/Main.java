package org.googlehashcode;

import org.googlehashcode.domain.Bag;
import org.googlehashcode.domain.Output;

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
}
