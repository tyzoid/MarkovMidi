package com.tyzoid.java.midi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.ShortMessage;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MusicControl extends JFrame implements ActionListener, Runnable {
	private final NoteQueue queue = new NoteQueue();
	private final MidiFacilitator facilitator;
	private final MusicGenerator generator;

	private MusicControl() {
		facilitator = new MidiFacilitator();

		this.setSize(640, 480);
		this.setTitle("Music Generator");

		this.setResizable(true);
		this.setVisible(true);

		generator = new MusicGenerator(this);

		new Thread(generator).start();
		new Thread(this).start();
	}

	public static void main(String[] args) {
		new MusicControl();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

	}

	public MidiFacilitator getFacilitator() {
		return this.facilitator;
	}

	public NoteQueue getQueue() {
		return this.queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				while (queue.getTimestamp() <= System.currentTimeMillis()) {
					MusicElem music = queue.popTop();
					if (music == null) break;

					for (short note : music.getNotes()) {
						ShortMessage note_off = new ShortMessage(ShortMessage.NOTE_OFF, 0, note, 0x7f);
						this.getFacilitator().send(note_off, -1);
					}
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
