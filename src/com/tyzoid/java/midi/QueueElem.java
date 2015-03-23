package com.tyzoid.java.midi;

public class QueueElem {
	private QueueElem next;
	private MusicElem music;
	private long timestamp;
	
	public QueueElem(MusicElem music, long timestamp){
		this.music = music;
		this.timestamp = timestamp;
		next = null;
	}
	
	public QueueElem getNext(){
		return this.next;
	}
	
	public void setNext(QueueElem next){
		this.next = next;
	}
	
	public MusicElem getMusicElem(){
		return this.music;
	}
	
	public long getTimestamp(){
		return this.timestamp;
	}
}
