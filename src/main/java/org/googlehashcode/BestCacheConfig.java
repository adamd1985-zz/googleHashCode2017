package org.googlehashcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.googlehashcode.domain.Bag;
import org.googlehashcode.domain.Endpoint;
import org.googlehashcode.domain.Output;
import org.googlehashcode.domain.VideoCache;
import org.googlehashcode.domain.VideoRequest;

public class BestCacheConfig {

	public List<VideoCache> prioritizeCacheByWeight(List<Endpoint> endpoints, List<VideoCache> caches, List<VideoRequest> reqs) {
		Map<Integer, VideoCache> wieghtedCaches = new HashMap<>();
		caches.forEach(c -> {
			wieghtedCaches.put(c.id, c);
		});

		endpoints.forEach(e -> {
			
			reqs.parallelStream().filter(reqs1 -> {
				return reqs1.endpointId == e.id;
			}).forEach(reqs2 -> {
				e.totalReqs += reqs2.requestsNumber;
			});

			e.cacheLatency.keySet().parallelStream().forEach(key -> {
				wieghtedCaches.get(key).weightUsage += e.totalReqs;

			});

			
			
			
			/*reqs.parallelStream().filter(reqs1 -> {
				return reqs1.endpointId == e.id;
			}).forEach(reqs2 -> {
				wieghtedCaches.get(key).weightUsage *= (reqs2.requestsNumber * e.cacheLatency.get(key).intValue());
			});*/
		});

		return wieghtedCaches.entrySet().parallelStream()
				.map(x -> x.getValue())
				.sorted(Comparator.comparing(VideoCache::getWeightUsage).reversed())
				.collect(Collectors.toList());

	}

	private List<VideoCache> createCaches(Bag bag) {
		List<VideoCache> caches = new ArrayList<>();

		for (int i = 0; i < bag.cacheServersNumber; ++i) {
			VideoCache c = new VideoCache();
			c.id = i;
			c.memRemaining = bag.cacheServerCapacityMB;
			caches.add(c);
		}

		System.out.println("Cache created");

		return caches;
	}

	public Output loadBestConfig(Bag bag) {
		long savedLat = 0L;
		boolean stop = false;
		System.out.println("Loading caches");
		List<VideoCache> weightedCaches = prioritizeCacheByWeight(bag.endpoints, createCaches(bag), bag.videoRequests);
		Output output = new Output(weightedCaches.size());

		int curCacheId = 0;
		VideoCache curCache = weightedCaches.get(curCacheId);
		System.out.println("Filling caches");
		do {

			int vidId = 0;
			for (Integer vidSize : bag.videoSizes) {
				if (curCache.memRemaining - vidSize > 0) {
					curCache.vidIds.add(vidId);
					curCache.memRemaining -= vidSize;
					++vidId;
				}
				else {
					++curCacheId;
					if (curCacheId >= weightedCaches.size()) {
						break;
					}
					curCache = weightedCaches.get(curCacheId);
				}
			}

			System.out.println("Judged vids");
			for (VideoRequest req : bag.videoRequests) {
				for (VideoCache videoCache : weightedCaches) {
					if (!bag.endpoints.get(req.endpointId).cacheLatency.containsKey(weightedCaches.indexOf(videoCache))) {
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
			System.out.println("Populated reqs");
			stop = true;
		}
		while (!stop);

		weightedCaches.stream().forEach(cache -> {
			cache.vidIds.stream().forEach(vid -> output.addVideo(cache.id, vid));
		});

		return output;
	}
}
