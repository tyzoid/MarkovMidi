package com.tyzoid.java.midi;

public class NoteQueue {
	private QueueElem head;

	private Object si = new Object();
	private Object sp = new Object();

	public NoteQueue() {
		head = null;
	}

	public long getTimestamp() {
		long ts = -1;
		synchronized(sp) {
			if(head != null) ts = head.getTimestamp();
		}
		
		return ts;
	}

	public MusicElem popTop() {
		synchronized(sp) {
			if(head == null) return null;
			QueueElem pop = head;

			head = head.getNext();
			return pop.getMusicElem();
		}
	}

	public void insert(MusicElem music, long timestamp) {
		synchronized(si) {
			QueueElem insert = new QueueElem(music, timestamp);

			if(head == null) {
				head = insert;
			} else {
				QueueElem reference;

				synchronized(sp) {
					if(head.getTimestamp() < timestamp || head.getNext() == null) {
						insert.setNext(head.getNext());
						head.setNext(insert);
						insert = null;
					}

					reference = head.getNext();
				}

				while(reference.getNext() != null && insert != null) {
					if(reference.getTimestamp() < timestamp) {
						insert.setNext(reference.getNext());
						reference.setNext(insert);
						insert = null;
					}

					reference = reference.getNext();
				}

				if(insert != null) reference.setNext(insert);
			}
		}
	}
}