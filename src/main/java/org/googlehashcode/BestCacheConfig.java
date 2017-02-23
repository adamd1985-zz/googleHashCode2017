package org.googlehashcode;

import java.util.ArrayList;
import java.util.List;

public class BestCacheConfig {
	final int[] vidSizes = { 20, 11 };

	final int ep0nCache = 3;

	final int[] ep0CachesLats = { 170, 1, 22, 2, 224 };

	final EP[] eps = { new EP(new int[] { 340, 500 }), new EP(new int[] { 1232, 334 }) };

	class EP {

		int[] reqs;

		public EP(int[] reqs) {
			super();
			this.reqs = reqs;
		}
	}

	class VidCache {
		List<Integer> vids = new ArrayList<>();

		int lat = 100;

		int size = 100;

	}

	List<List<VidCache>> poConfigs = new ArrayList<>();

	List<VidCache> caches = new ArrayList<>();

	public void loadBestConfig() {
		boolean stop = false;
		VidCache curCache = new VidCache();
		do {
			int vidId = 0;
			for (int vidSize : vidSizes) {
				if (curCache.size - vidSize > 0) {
					curCache.vids.add(vidId);
					++vidId;
				}
				else {
					caches.add(curCache);
					curCache = new VidCache();
				}
			}
			// Find least latency
			stop = true;
		}
		while (!stop);
	}
}
