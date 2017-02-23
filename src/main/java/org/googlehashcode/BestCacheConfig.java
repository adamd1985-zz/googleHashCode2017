package org.googlehashcode;

import java.util.ArrayList;
import java.util.List;

import org.googlehashcode.domain.Bag;
import org.googlehashcode.domain.VideoCache;
import org.googlehashcode.domain.VideoRequest;

public class BestCacheConfig {

	List<VideoCache> caches = new ArrayList<>();

	public long loadBestConfig(Bag bag) {
		long savedLat = 0L;
		boolean stop = false;
		VideoCache curCache = new VideoCache();
		do {
			int vidId = 0;
			for (Integer vidSize : bag.videoSizes) {
				if (curCache.memRemaining - vidSize > 0) {
					curCache.vidIds.add(vidId);
					++vidId;
				}
				else {
					caches.add(curCache);
					curCache = new VideoCache();
				}
			}

			for (VideoRequest req : bag.videoRequests) {
				for (VideoCache videoCache : caches) {

				}
			}

			stop = true;
		}
		while (!stop);

		return savedLat;
	}
}
