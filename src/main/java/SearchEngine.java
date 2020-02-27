import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.apache.lucene.queryparser.classic.ParseException;

import a1.docs.Cranfield;
import a1.docs.queryDocs;
import a1.Parser;
import a1.UIComponents;
import a1.Indexer;


public class SearchEngine {

	public static void main(String[] args) throws IOException, ParseException{
		// TODO Auto-generated method stub
		String docs_path = "./Docs/crandocs";
		String index_path_directories = "./Docs/index";
		String queries_path_directory = "./Docs/queries";
		String docs_location = "C:/Users/bhard/eclipse-workspace/a1/src/main/resources/cran.all.1400";
		String results_path = "C:\\Trinity Assignment\\Information Retrival\\crafield_assignment\\trec_eval-9.0.7\\results";
		
		Parser par = new Parser(docs_path,queries_path_directory);
		Indexer indx = new Indexer();
		
		indx.open();
		par.documents();
		File docsDirectory = new File(docs_path);
		File[] filesDoc =  docsDirectory.listFiles();
		
		int count = 0 ;
		 for (File file : Objects.requireNonNull(filesDoc)) {
	            if (file.isFile()) {
	                indx.indexingDocuments(par.makeDoc(++count));
	            }
	        }
		
		 indx.close();
		 par.createQueries();
		 
		 File queryDirectory = new File(queries_path_directory);
		 File[] filesQuery =  queryDirectory.listFiles();
		 FileWriter res = new FileWriter(results_path);
		 int count1 = 1;
		 
	       while (count1 < Objects.requireNonNull(filesQuery).length) {
	            queryDocs query = par.queryMaker(count1++);
	            String result = "";
	            String queryString = query.getQuery();
	            ArrayList<UIComponents> resultList = indx.search(queryString);
	            for (UIComponents currentResult : resultList) {
	                res.append(query.getID()).append(" 0 ").append(currentResult.getID());
	                res.append(" ").append(String.valueOf(currentResult.getRank())).append(" ");
	                res.append(String.valueOf(currentResult.getScore())).append(" exp_0").append("\n");
	            }
	            res.write(result);
	        }
	        res.close();
		
		
		

	}

}
