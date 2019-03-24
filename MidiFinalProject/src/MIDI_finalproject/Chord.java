import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class Chord {
	private int duration;
	private int channel;
	private int key;
	private String noteName;
	private int octave;
	private int velocity;

	public Chord(long tickin, long tickout, int channel, int key, int velocity) {
		this.setDuration((int) (tickout - tickin));
		this.setChannel(channel);
		this.setKey(key);
		this.setOctave((key / 12) - 1);
		int note = key % 12;
		this.setNoteName(MIDIPlayer.getNoteNames()[note]);
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public void play() {
		Synthesizer synth;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel.noteOn(key, velocity);
			Thread.sleep(duration);
			MidiChannel.noteOff(key, velocity);
		} catch (Exception e) {
		}
	}
}
