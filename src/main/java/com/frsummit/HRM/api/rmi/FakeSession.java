package com.frsummit.HRM.api.rmi;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class FakeSession {
	private static final Random random = new Random();
	private HashMap<Long, String> map;

	public String get(Long req) {
		if (map.containsKey(req)) {
			return map.get(req);
		} else {
			return null;
		}
	}

	public void bind(Long req, String id) {
		this.map.put(req, id);
	}

	public void unbind(Long req) {
		this.map.remove(req);
	}

	public long getFreeRequestId() {
		long idTemp;
		do {
			idTemp = random.nextLong();
		} while (map.containsKey(idTemp));

		return idTemp;
	}

	public FakeSession() {
	}

}
