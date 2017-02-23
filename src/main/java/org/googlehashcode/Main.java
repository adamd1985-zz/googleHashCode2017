package org.googlehashcode;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import com.google.common.io.Resources;

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
		File file = Paths.get(Resources.getResource("test.in").toURI()).normalize().toFile();

		ImmutableList<String> lines = Files
				.asCharSource(file, Charsets.UTF_8)
				.readLines();

		Bag bag = null;
		System.out.println(String.format("Lines read: %s", lines.size()));
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (i == 0) {
				Integer[] elems = Parser.integerArray(line);
				bag = new Bag(elems[0],elems[1],elems[2],elems[3],elems[4]);
			} else if (i == 1) {
				Integer[] videoSizes = Parser.integerArray(line);
				bag.addVideoSizes(Arrays.stream(videoSizes).collect(Collectors.toList()));
			} else {
				// Endpoints

			}
		}
		System.out.println(bag);

        final BestCacheConfig bestCacheConfig = new BestCacheConfig();
        bestCacheConfig.loadBestConfig();
    }
}
