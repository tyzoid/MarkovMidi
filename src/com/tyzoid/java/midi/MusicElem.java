package com.tyzoid.java.midi;

public class MusicElem {
	private short[] notes;
	
	public MusicElem(short[] notes){
		this.notes = notes;
	}
	
	public MusicElem(short note){
		this.notes = new short[1];
		notes[0] = note;
	}
	
	public short[] getNotes(){
		return notes;
	}
}
