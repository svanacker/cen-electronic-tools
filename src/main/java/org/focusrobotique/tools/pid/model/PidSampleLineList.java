package org.focusrobotique.tools.pid.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PidSampleLineList implements Iterable<PidSampleLine> {
	
	private List<PidSampleLine> list;

	public PidSampleLineList() {
		list = new ArrayList<>();
	}
	
	public void add(PidSampleLine pidSampleLine) {
		list.add(pidSampleLine);
	}
	
	public int size() {
		return list.size();
	}

	@Override
	public Iterator<PidSampleLine> iterator() {
		return list.iterator();
	}
}
