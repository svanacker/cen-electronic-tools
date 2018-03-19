package org.focusrobotique.tools.pid.model;

public enum PidType {

	NONE(0), GO(1), ROTATE(2), FINAL(3), MAINTAIN(4), ADJUST(5);

	private int index;

	public int getIndex() {
		return index;
	}

	private PidType(int index) {
		this.index = index;
	}
}
