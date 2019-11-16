import jml.model.Model;
import jml.trainer.Trainer;

public class ModelTester {
	public static void main(String[] args) {
		int[] nodes = { 1, 1 };
		Model m = new Model(nodes);

		System.out.println(m.toString());

		DataParserTester dp = new DataParserTester();

		dp.openFile("src\\DeathsPer100kvsDoctorsPer100k.txt");
		
		Trainer t = new Trainer(m, dp);

		t.start();

	}
}
