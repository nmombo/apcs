import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class MIDIPlayer {
	private long tickin;
	private long tickout;
	private int channel;
	private int key;
	private int velocity;
	private ArrayList<Chord> toReturn = new ArrayList<Chord>();
	
	private ArrayList<String> mess = new ArrayList<String>();

	public static final int NOTE_ON = 0x90;
	public static final int NOTE_OFF = 0x80;
	public static final String[] NOTE_NAMES = { "C", "C#", "D", "D#", "E", "F",
			"F#", "G", "G#", "A", "A#", "B" };

	public void main(String fileName) throws Exception {
		// fix file name
		if (!fileName.endsWith(".mid"))
			fileName = fileName + ".mid";
		// Set sequencer to default
		Sequencer sequencer = MidiSystem.getSequencer();
		sequencer.open();
		// Stream of file
		InputStream in = new BufferedInputStream(new FileInputStream(new File(
				fileName)));
		// Loads input stream into sequencer
		sequencer.setSequence(in);
		// Create JFrame before playing
		JFrame frame = new JFrame("Example MIDI Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Starts play-back of the MIDI data in the currently loaded sequence.
		sequencer.start();
		JProgressBar progressBar = new JProgressBar(0,
				(int) sequencer.getMicrosecondLength());
		frame.getContentPane().add(progressBar);
		frame.pack();
		frame.setVisible(true);

		int trackNumber = 0;
		Sequence sequence = MidiSystem.getSequence(new File("FortreeCity.mid"));
		for (Track track : sequence.getTracks()) {
			for (int i = 0; i < track.size(); i++) {
				MidiEvent event = track.get(i);
				System.out.println("Recording tick " + event.getTick() + ".");
				Thread.sleep(1);
			}
		}
		ArrayList<Long> sep = new ArrayList<Long>();
		while (sequencer.isRunning()) {
			progressBar.setValue((int) sequencer.getMicrosecondPosition());
			progressBar.setStringPainted(true);
			System.out.println("Recording ms "
					+ sequencer.getMicrosecondPosition());
			sep.add(new Long(sequencer.getMicrosecondPosition()));

		}
		trackNumber = 0;
		sequence = MidiSystem.getSequence(new File("FortreeCity.mid"));
		for (Track track : sequence.getTracks()) {
			trackNumber++;
			System.out.println("Track " + trackNumber + ": size = "
					+ track.size());
			System.out.println();
			for (int i = 0; i < track.size(); i++) {
				MidiEvent event = track.get(i);
				System.out.print("Tick " + event.getTick() + ". ");
				MidiMessage message = event.getMessage();
				if (message instanceof ShortMessage) {
					ShortMessage sm = (ShortMessage) message;
					if (sm.getCommand() == NOTE_ON) {
						System.out.print("Channel " + sm.getChannel() + ". ");
						int key = sm.getData1();
						int octave = (key / 12) - 1;
						int note = key % 12;
						String noteName = NOTE_NAMES[note];
						int velocity = sm.getData2();
						String mes = "On,  " + noteName + " in octave "
								+ octave + ". MIDI number " + key
								+ ". velocity " + velocity + ".";
						System.out.println(mes);
						mess.add(mes);
						makeChord(event.getTick(), sm.getChannel(),
								sm.getData1(), sm.getData2());
					} else if (sm.getCommand() == NOTE_OFF) {
						System.out.print("           ");
						int key = sm.getData1();
						int octave = (key / 12) - 1;
						int note = key % 12;
						String noteName = NOTE_NAMES[note];
						int velocity = sm.getData2();
						String mes = "Off, " + noteName + " in octave "
								+ octave + ". MIDI number " + key
								+ ". Velocity " + velocity + ".";
						System.out.println(mes);
						mess.add(mes);
						endChord(event.getTick());
					} else {
						System.out.println("Command:" + sm.getCommand());
					}
				} else {
					System.out.println("Other message: " + "done.");
				}
			}

			System.out.println();
		}
	}

	public void makeChord(long tickin, int channel, int key, int velocity) {
		this.tickin = tickin;
		this.channel = channel;
		this.key = key;
		this.velocity = velocity;
	}

	public void endChord(long tickout) {
		this.tickout = tickout;
		makeChord();
	}

	private void makeChord() {
		toReturn.add(new Chord(tickin, tickout, channel, key, velocity));
	}

	public ArrayList<Chord> getSong() {
		return toReturn;
	}
	
	public static String[] getNoteNames() {
		return NOTE_NAMES;
	}
	
	public ArrayList<String> getMessage() {
		return mess;
	}
}
