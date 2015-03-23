package com.tyzoid.java.midi.markov;

public class MarkovElem {
	private short[] notes;

	public MarkovElem(short[] notes) {
		if (notes.length < 1) throw new ArrayIndexOutOfBoundsException("Note array is empty.");
		this.notes = notes;
	}

	public MarkovElem(short note) {
		this.notes = new short[1];
		this.notes[0] = note;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MarkovElem)) return false;

		MarkovElem obj = (MarkovElem) o;

		if(this.notes.length != obj.notes.length) return false;

		for(int i = 0; i < this.notes.length; i++){
			if (this.notes[i] != obj.notes[i]) return false;
		}

		return true;
	}
	
	public short[] getNotes(){
		return this.notes;
	}

	public short getNote() {
		return this.notes[0];
	}
}
