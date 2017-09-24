package dbconnect.domain;

public class FreshFish {
	private final int freshFishID;
	private final int fishCode;
	private final String fishName;
	private final String district;
	private final int unitPrice;

	public static class Builder {
		private int freshFishID;
		private int fishCode;
		private String fishName;
		private String district;
		private int unitPrice;

		public Builder freshFishID(int val) {
			this.freshFishID = val;
			return this;
		}

		public Builder fishCode(int val) {
			this.fishCode = val;
			return this;
		}

		public Builder fishName(String val) {
			this.fishName = val;
			return this;
		}

		public Builder district(String val) {
			this.district = val;
			return this;
		}

		public Builder unitPrice(int val) {
			this.unitPrice = val;
			return this;
		}

		public FreshFish build() {
			return new FreshFish(this);
		}
	}

	FreshFish(Builder builder) {
		this.freshFishID = builder.freshFishID;
		this.fishCode = builder.fishCode;
		this.fishName = builder.fishName;
		this.district = builder.district;
		this.unitPrice = builder.unitPrice;
	}

	public int freshFishIdValue() {
		return new Integer(this.freshFishID);
	}

	public int fishCodeValue() {
		return new Integer(this.fishCode);
	}

	public String fishNameValue() {
		return new String(this.fishName);
	}

	public String districtValue() {
		return new String(this.district);
	}

	public int unitPriceValue() {
		return new Integer(this.unitPrice);
	}
}
