package com.qait.test.open;

public final class Interval {
	public final int start;
	
	public int end;

	public Interval(final int start, final int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Interval other = (Interval) obj;
		if (end != other.end) {
			return false;
		}
		if (start != other.start) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}
}
