import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class testClass {
	private static Scanner keyboard;
	private static MediaPlayer mediaPlayer;
	
	public static void main(String[] args) {
		String message = "";
		String choice = "";
		while (!choice.equals("Q")) {
			System.out.println("What would you like me to do?");
			System.out.println("(1) Read what you typed?");
			System.out.println("(2) Read from a file?");
			System.out.println("(3) Sing Karaoke?");
			System.out.println("(Q) Quit");
			keyboard = new Scanner(System.in);
			choice = keyboard.next();
			//Stores message
			if (choice.charAt(0) == '1') {
				System.out.println("Please enter a message");
				keyboard.nextLine();			
				message = keyboard.nextLine();
			} else if (choice.charAt(0) == '2') {
				message = readFromFile();
			} else if (choice.charAt(0) == '3') {
				message = karaokeType();
			} else if (choice.charAt(0) == 'Q') {
				message = "Thanks!";
			} else {
				message = "Not a valid choice.";
			}
			Typewriter.typeWrite(message);
			System.out.println("\n");
		}
		
	}
	
	private static String readFromFile() {
		String message = "";
		try {
			FileReader fileReader = new FileReader("message.txt");
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			if (line == null) {
				message ="File empty.";
			}
			else {
				while (line != null) {
					message = message + line + "\n";
					line = reader.readLine();
				}
			}
			reader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			message = "File not found.";
		} catch (IOException e) {
			message = "File read error.";
		}
		return message;
	}
	
	private static String karaokeType() {
		String message = "";
		try {
			FileReader fileReader = new FileReader("CoughSyrup.txt");
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			if (line == null) {
				message ="File empty.";
			}
			else {
				int bpm = Integer.parseInt(line);
				int bpl = Integer.parseInt(reader.readLine());
				line = reader.readLine();
				playSong();
				while (line != null) {
					Typewriter.karaokeWrite(line, bpm, bpl);
					line = reader.readLine();
				}
			}
			reader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			message = "File not found.";
		} catch (IOException e) {
			message = "File read error.";
		}
		
		return message;
	}
	
	private static void playSong() {
		JFXPanel fxPanel = new JFXPanel();
		String songName = "CoughSyrup.mp3";
		Media song = new Media(new File(songName).toURI().toString());
		mediaPlayer = new MediaPlayer(song);
		mediaPlayer.setStopTime(new Duration(75000));
		mediaPlayer.play();
		
	}
}
