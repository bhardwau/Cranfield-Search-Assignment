package a1;
import java.io.Reader;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.ClassicFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;


public class myAnalyzer extends StopwordAnalyzerBase {
	public myAnalyzer(CharArraySet stopWords) {
		super(stopWords);
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		// TODO Auto-generated method stub
		Tokenizer token = new ClassicTokenizer();
        TokenStream tks = new ClassicFilter(token);
        tks = new LowerCaseFilter(tks);
        tks = new PorterStemFilter(tks);
        tks = new EnglishPossessiveFilter(tks);
        tks = new StopFilter(tks, stopwords);
        tks = new KStemFilter(tks);
        return new TokenStreamComponents(token, tks);

	

	}
	

}
