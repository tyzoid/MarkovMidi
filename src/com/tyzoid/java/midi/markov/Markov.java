package com.tyzoid.java.midi.markov;

import java.util.HashMap;

public class Markov {
	private HashMap<MarkovSequence, MarkovStat> markov;

	public Markov() {
		this.markov = new HashMap<MarkovSequence, MarkovStat>();
	}

	public void add(MarkovSequence sequence, short[] notes) {
		if (this.markov.containsKey(sequence)) this.markov.get(sequence).add(new MarkovElem(notes));
		else this.markov.put(sequence, new MarkovStat(notes));
	}
	
	public MarkovStat getStat(MarkovSequence sequence){
		if (this.markov.containsKey(sequence)) return this.markov.get(sequence);

		// D'Oh, we've never encountered this sequence before...
		System.out.println("We've never encountered this sequence before...");
		return null;
	}
}
