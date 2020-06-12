package main;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Repeater extends Thread implements IRepeater {
	
	private final Runnable action;
	private final int interval;

	private boolean started = false;
	private boolean end = false;
	
	@Override
	public void run() {
		while(!end) {
			action.run();
			sleep();
		}
	}

	private void sleep() {
		try {
			sleep(interval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		if (started) return;
		
		super.start();
		started = true;
	}

	@Override
	public void end() {
		end = true;
	}
}
