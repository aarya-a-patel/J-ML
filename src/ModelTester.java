import jml.model.Model;
import jml.trainer.DataParser;
import jml.trainer.Trainer;

public class ModelTester {
	public static void main(String[] args) {
		int[] nodes = { 3, 1 };
		Model m = new Model(nodes, false);

		//System.out.println(m.toString());

		DataParser dp = new DataParserIDKTester();

		dp.openFile("src\\5k.RectNode.normal.ascii.txt");
		
		Trainer t = new Trainer(m, dp);

		t.start();

	}
}
