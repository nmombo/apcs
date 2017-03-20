import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

public class ExampleSynthesizer {

	private static Synthesizer synth;
	private static MidiChannel[] channels;

	public static void main(String[] args) {
        // example
		// MidiChannel.noteOn(int midiNote, int velocity);
		// midiNote is 0-87, velocity is 0-127

		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
			for (MidiChannel c : channels) {
				System.out.println(c.toString());
				c.allNotesOff();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
