package com.tyzoid.java.midi;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MidiFacilitator {
	private Receiver midi_reciever;
	public MidiFacilitator(){
		try {
			midi_reciever = MidiSystem.getReceiver();
		} catch(Exception e) {
			System.out.println("FAILURE");
			return;
		}
	}
	
	public synchronized void send(ShortMessage message, long timestamp){
		midi_reciever.send(message, timestamp);
	}
}
