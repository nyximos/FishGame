package gameObject;

import java.util.Timer;
import java.util.TimerTask;

// 1초마다 메서드 실행

public class Test {
	public static void main(String[] args) {

		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			int cnt = 0;

			@Override
			public void run() {
				if (cnt++ < 5) {
					System.out.println("task...");
				} else {
					timer.cancel();
				}
			}
		};
		timer.schedule(timerTask, 1000, 1000);
	}
}
