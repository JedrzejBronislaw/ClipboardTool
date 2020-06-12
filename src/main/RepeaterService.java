package main;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepeaterService implements ServiceOperator {

	private static final int INTERVAL = 1000;
	private final Runnable action;
	
	private IRepeater w;


	@Override
	public boolean turnOff() {
		if (w == null) return false;

		w.end();
		w = null;
		
		return true;
	}
	
	@Override
	public boolean turnOn() {
		if (w != null) return false;
		
		w = new Repeater(action, INTERVAL);
		w.start();
		
		return true;
	}

	@Override
	public synchronized boolean isWorking() {
		return w != null;
	}
}
