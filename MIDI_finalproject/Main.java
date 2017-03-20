import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		ArrayList<ArrayList<String>> message = new ArrayList<ArrayList<String>>();
		MIDIPlayer m = new MIDIPlayer();
		m.main("FortreeCity");
		message.add(m.getMessage());
		Thread.sleep(1000);
		m.main("LittlerootTown");
		message.add(m.getMessage());
		Thread.sleep(1000);
		m.main("ChampionBattle");
		message.add(m.getMessage());
		Thread.sleep(1000);
		m.main("MtPyre");
		message.add(m.getMessage());
		Thread.sleep(10000);
		for (ArrayList<String> arr : message) {
			for (String s : arr)
				System.out.println(s);
			System.out.println();
		}
	}
}
