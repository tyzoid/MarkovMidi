package com.tyzoid.java.midi.markov;

import java.util.HashMap;
import java.util.Set;

public class MarkovStat extends MarkovElem {
	private HashMap<MarkovElem, Integer> notemap;

	private MarkovElem mostCommon;
	private int mostCommonFrequency = 0;
	private long totalNotes;

	public MarkovStat(short[] notes) {
		super(notes);

		this.notemap = new HashMap<MarkovElem, Integer>();
	}

	public void add(MarkovElem elem) {
		this.totalNotes++;

		int frequency = 1;
		if (this.notemap.containsKey(elem)) {
			this.notemap.put(elem, 1);
		} else {
			frequency = notemap.get(elem);
			this.notemap.put(elem, ++frequency);
		}

		if (this.mostCommonFrequency < frequency) {
			this.mostCommon = elem;
			this.mostCommonFrequency = frequency;
		}
	}

	public short[] getMostCommonNote() {
		// If we don't have any notes, return a blank note.
		if (this.totalNotes == 0) return new short[]{0};

		return this.mostCommon.getNotes();
	}

	public short[] getRandomNote() {
		// If we don't have any notes, return a blank note.
		if (this.totalNotes == 0) return new short[]{};
		
		MarkovElem[] notes = (MarkovElem[]) this.notemap.keySet().toArray();

		return notes[(int) (Math.random() * notes.length)].getNotes();
	}

	public short[] getProbabilisticNote() {
		// If we don't have any notes, return a blank note.
		if (this.totalNotes == 0) return new short[]{0};
		
		Set<MarkovElem> elems = this.notemap.keySet();

		int r = (int) (Math.random() * this.totalNotes);

		int i = 0;
		for (MarkovElem elem : elems) {
			if ((i += this.notemap.get(elem)) > r) return elem.getNotes();
		}

		System.out.println("We shouldn't be here... Returning most common note.");

		return this.getMostCommonNote();
	}
	
	public long getTotalNotes(){
		return this.totalNotes;
	}
}
