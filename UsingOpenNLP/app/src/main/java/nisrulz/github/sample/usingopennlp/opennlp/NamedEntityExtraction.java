package nisrulz.github.sample.usingopennlp.opennlp;

import android.app.Activity;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class NamedEntityExtraction {
	public void findNames(Activity activity,String data, int pathToModel) {
		System.out.println("\n>> Running " + getClass().getSimpleName() + "\n");
		
		CommonUtils commonUtils = new CommonUtils();
		
		
		String[] tokens = commonUtils.createTokensFromString(data);
		InputStream is = null;
		try {
			is = activity.getResources().openRawResource(pathToModel);;

			TokenNameFinderModel model = new TokenNameFinderModel(is);

			NameFinderME detector = new NameFinderME(model);

			Span nameSpans[] = detector.find(tokens);

			for (Span s : nameSpans) {
				System.out.println(s.toString());

				for (int i = s.getStart(); i < s.getEnd(); i++) {
					System.out.println(tokens[i] + " ");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
