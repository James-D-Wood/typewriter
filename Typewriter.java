import java.util.concurrent.TimeUnit;

public class Typewriter {
	
	static private long[] randomIntervals = new long[10];
	
	//The recursive structure allows for unlimited interval generation 
	//without creating an array to match the message size.
	public static void typeWrite(String message) {
		//base case of recursive call, less than 10 characters
		if (message.length() < 10) {
			type(message, randomIntervals);
		}
		//repeat if message remains
		else {
			type(message, randomIntervals);
			message = message.substring(10, message.length());
			typeWrite(message);
		}
	}
	
	public static void type(String message, long[] ranInt) {
		//generate random intervals
		newRandoms();
		if (message.length() < 10) {
			for (int i = 0; i < message.length(); i++) {
				System.out.print(message.charAt(i));
				try {
					TimeUnit.MILLISECONDS.sleep(ranInt[i]);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			for (int i = 0; i < ranInt.length; i++) {
				System.out.print(message.charAt(i));
				try {
					TimeUnit.MILLISECONDS.sleep(ranInt[i]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//generates 10 random time intervals between 0 and 200 ms
	public static void newRandoms() {
		for (int i = 0; i < randomIntervals.length; i++) {
			randomIntervals[i] = (long)(Math.random() * 200);
		}
	}
	
	//This method is independent of the rest of the Typewriter class, it was
	//written especially for the "karaoke function"
	public static void karaokeWrite(String message, int bpm, int bpl) {
		//This interval is actually a character per second rate calculated based on
		//beats per minute of the song and beats per line of lyrics (tapped out by foot)
		//There will be a unique interval value for each line passed through the method
		long interval = (long)(1000 * ((60 * bpl)/(double)(bpm * message.length())));
		for (int i = 0; i < message.length(); i++) {
			System.out.print(message.charAt(i));
			try {
				TimeUnit.MILLISECONDS.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("\n");
	}
}
