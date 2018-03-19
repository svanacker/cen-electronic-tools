package org.focusrobotique.tools.pid.model;

public enum InstructionType {

	THETA(0),

	ALPHA(1);
	
	private int index;
	
	public int getIndex() {
		return index;
	}

	private InstructionType(int index) {
		this.index = index;
	}
}
