package org.googlehashcode;

public class BestCacheConfig {
	final int[] vidSizes = { 20, 11, 50, 26, 5, 3, 6, 32, 40, 22, 4, 20, 50, 27, 49, 44, 1, 37, 35, 27, 14, 33, 6, 22, 23, 48, 44, 14, 26, 9, 46, 44, 15, 32, 31, 8, 39, 27, 39, 27, 1, 17, 1, 47, 44, 42, 16, 3, 44, 48, 5, 25, 4, 39, 39, 7,
			24, 28, 14, 44, 22, 11, 27, 37, 11, 16, 50, 33, 22, 26, 7, 12, 17, 30, 12, 12, 4, 32, 12, 46, 43, 4, 12, 34, 11, 7, 47, 29, 24, 40, 41, 10, 5, 22, 22, 24, 37, 34, 50, 5 };

	final int ep0Let = 1013;

	final int ep0nCache = 3;

	final int[] ep0CachesLats = { 170, 1, 22, 2, 224 };

	final EP[] eps = { new EP(new int[] { 1, 2 }, new int[] { 340, 500 }), new EP(new int[] { 1, 2 }, new int[] { 1232, 334 }) };

	class EP {
		int[] vids;

		int[] reqs;

		public EP(int[] vids, int[] reqs) {
			super();
			this.vids = vids;
			this.reqs = reqs;
		}
	}

	class cache {
		int[] vids;

		public cache(int[] vids) {
			super();
			this.vids = vids;
		}

	}

	void loadBestConfig() {

	}
}
