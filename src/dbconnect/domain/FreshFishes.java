package dbconnect.domain;

import java.util.ArrayList;
import java.util.List;

public class FreshFishes {
	private List<FreshFish> fishes;

	public FreshFishes() {
	}

	public FreshFishes(List<FreshFish> fishes) {
		this.fishes = fishes;
	}

	public List<FreshFish> deepCopyListValue() {
		List<FreshFish> copy = new ArrayList<>(this.fishes);
		return copy;
	}
}
