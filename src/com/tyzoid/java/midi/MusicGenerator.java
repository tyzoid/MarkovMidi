package com.tyzoid.java.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

public class MusicGenerator implements Runnable {
	private MusicControl controller;

	public MusicGenerator(MusicControl controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		while (true) {
			try {
				short note = (short) ((Math.random() * 30) + 45);
				ShortMessage note_on = new ShortMessage(ShortMessage.NOTE_ON, 0, note, 0x7f);
				controller.getFacilitator().send(note_on, -1);
				controller.getQueue().insert(new MusicElem(note), System.currentTimeMillis() + 250);
				Thread.sleep(500);
			} catch (InterruptedException e) {
			} catch (InvalidMidiDataException e) {
			}
		}
	}
}