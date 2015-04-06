package mechaxor;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.swing.text.Document;
 
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;

public class Sessionals {
	static Scanner cin=new Scanner(System.in);
	private final String USER_AGENT = "Mozilla/5.0";
	 
	public static void main(String[] args) throws Exception {
 
		Sessionals http = new Sessionals();
		String clss="C4A"; System.out.print("Enter class: "); clss = cin.nextLine();
		int from=21; System.out.print("Enter start roll: "); from = cin.nextInt();
		int to=21; System.out.print("Enter end roll: "); to = cin.nextInt();
		String roll="";
		System.out.println("\n\n------------------------------------\tH@XoR\t------------------------------------\n\n");
		for(int i=from;i<=to;i++)
			{roll=Integer.toString(i);
			 System.out.println("----------"+clss+roll+"----------");
			 http.sendPost(clss,roll);
			 System.out.println("----------"+clss+roll+"----------");
			}
	}
 

 
	// HTTP POST request
	private void sendPost(String clss,String roll) throws Exception {

		String url = "http://www.mec.ac.in/sessional-marks/sessional-individual-student.php";
		String output="";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
 
		// add header
		post.setHeader("User-Agent", USER_AGENT);
 
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("class", clss));
		urlParameters.add(new BasicNameValuePair("rollno", roll));
		urlParameters.add(new BasicNameValuePair("Button1", "Submit"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));
 
		HttpResponse response = client.execute(post);
		/*System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
                                    response.getStatusLine().getStatusCode());
 		*/
		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		//System.out.println(result.toString());
		output=result.toString();
		String html = output;
		org.jsoup.nodes.Element table = Jsoup.parse(html.toString());
		org.jsoup.select.Elements rows = table.select("tr");
		
		//System.out.println(rows.get(2).children());
		for(int i=3; i<rows.size();i++){
		for (org.jsoup.nodes.Element td: rows.get(i).children()) {
				
	            System.out.println(td.text());
	        }
		System.out.println();
		}
	}
 
}
