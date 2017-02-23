package org.googlehashcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.googlehashcode.domain.Bag;
import org.googlehashcode.domain.Endpoint;
import org.googlehashcode.domain.VideoCache;
import org.googlehashcode.domain.VideoRequest;

public class BestCacheConfig {

	public List<VideoCache> caches = new ArrayList<>();

	public List<VideoCache> prioritizeCacheByWeight(List<Endpoint> endpoints, List<VideoCache> caches, List<VideoRequest> reqs) {
		Map<Integer, VideoCache> wieghtedCaches = new HashMap<>();
		caches.forEach(c -> {
			wieghtedCaches.put(c.id, c);
		});

		endpoints.forEach(e -> {

			e.cacheLatency.keySet().stream().forEach(key -> {
				wieghtedCaches.get(key).weightUsage++;
				reqs.stream().filter(reqs1 -> {
					return reqs1.endpointId == e.id;
				}).forEach(reqs2 -> {
					wieghtedCaches.get(key).weightUsage *= reqs2.requestsNumber;
				});
			});
		});

		return wieghtedCaches.entrySet().stream()
				.map(x -> x.getValue())
				.sorted(Comparator.comparing(VideoCache::getWeightUsage).reversed())
				.collect(Collectors.toList());

	}

	public long loadBestConfig(Bag bag) {
		long savedLat = 0L;
		boolean stop = false;
		VideoCache curCache = new VideoCache();

		curCache.memRemaining = bag.cacheServerCapacityMB;
		do {

			int vidId = 0;
			for (Integer vidSize : bag.videoSizes) {
				if (curCache.memRemaining - vidSize >= 0) {
					curCache.vidIds.add(vidId);
					++vidId;
				}
				else {
					caches.add(curCache);
					curCache = new VideoCache();
					curCache.memRemaining = bag.cacheServerCapacityMB;
				}
			}
			caches.add(curCache);
			for (VideoRequest req : bag.videoRequests) {
				for (VideoCache videoCache : caches) {
					if (!bag.endpoints.get(req.endpointId).cacheLatency.containsKey(caches.indexOf(videoCache))) {
						continue;
					}

					boolean found = false;
					for (Integer vid : videoCache.vidIds) {

						if (req.videoId == vid) {
							savedLat += ((bag.endpoints.get(req.endpointId).datacenterLatencyMS - videoCache.latency) * req.requestsNumber);
							found = true;
							break;
						}
					}
					if (found) {
						break;
					}
				}
			}

			stop = true;
		}
		while (!stop);

		return savedLat;
	}
}
