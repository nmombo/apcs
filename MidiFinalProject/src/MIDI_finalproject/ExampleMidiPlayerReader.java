import java.io.File;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class ExampleMidiPlayerReader {
	public static final int NOTE_ON = 0x90;
	public static final int NOTE_OFF = 0x80;
	public static final String[] NOTE_NAMES = { "C", "C#", "D", "D#", "E", "F",
			"F#", "G", "G#", "A", "A#", "B" };

	public static void main(String[] args) throws Exception {
		Sequence sequencer = MidiSystem.getSequence(new File("FortreeCity.mid"));

		int trackNumber = 0;
		for (Track track : sequencer.getTracks()) {
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
						System.out.println("On,  " + noteName + " in octave "
								+ octave + ". MIDI number " + key
								+ ". velocity " + velocity + ".");
					} else if (sm.getCommand() == NOTE_OFF) {
						System.out.print("           ");
						int key = sm.getData1();
						int octave = (key / 12) - 1;
						int note = key % 12;
						String noteName = NOTE_NAMES[note];
						int velocity = sm.getData2();
						System.out.println("Off, " + noteName + " in octave "
								+ octave + ". MIDI number " + key
								+ ". Velocity " + velocity + ".");
					} else {
						System.out.println("Command:" + sm.getCommand());
					}
				} else {
					System.out.println("Other message: " + message.getClass());
				}
			}

			System.out.println();
		}

	}
}