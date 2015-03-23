package com.tyzoid.java.midi.markov;

public class MarkovSequence {
	private MarkovElem[] elements;

	public MarkovSequence(MarkovElem[] elements) {
		this.elements = elements;
	}

	public MarkovElem[] getElements() {
		return this.elements;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MarkovSequence)) return false;

		MarkovSequence obj = (MarkovSequence) o;

		if (obj.elements.length != this.elements.length) return false;

		for (int i = 0; i < this.elements.length; i++) {
			if (!this.elements[i].equals(obj.elements[i])) return false;
		}

		return true;
	}
}
