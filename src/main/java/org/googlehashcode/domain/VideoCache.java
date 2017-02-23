package org.googlehashcode.domain;

import java.util.ArrayList;
import java.util.List;

public class VideoCache {
	public int id;

	public int memRemaining;

	public int latency;

	public long weightUsage = 0;

	public List<Integer> vidIds = new ArrayList<>();

	public long getWeightUsage() {
		return weightUsage;
	}

	
}
