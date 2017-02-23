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
		System.out.println("Hello world!");

		File file = Paths.get(Resources.getResource("big.in").toURI()).normalize().toFile();

		ImmutableList<String> lines = Files
				.asCharSource(file, Charsets.UTF_8)
				.readLines();

		final BestCacheConfig bestCacheConfig = new BestCacheConfig();
		bestCacheConfig.loadBestConfig();
	}
}
