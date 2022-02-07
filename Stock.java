package GoogleStockReader;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Stock {
	public String tick;
	public String date;
	public double quantity;
	public double currPrice;
	public double prevPrice;
	public double split;
	public double difference;
	public double principal;
	public double totalAmnt;
	
	//initiates all variebles
	public Stock(String tick, String date, int quantity, double prevPrice) { 
		this.tick = tick;
		this.date=date;
		this.quantity=quantity;
		this.prevPrice=prevPrice;
		
		split =1;
	}
	//accesses API and finds the line with our date, by parsing through all the data, which is in .csv format
	public void parse() throws IOException {
	String full = ("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol="+tick+"&outputsize=full&datatype=csv&apikey=1UUPCZ34OEX9MDNW");
	URL url = new URL (full);
		URLConnection URLConn = url.openConnection();
		InputStreamReader inStream = new InputStreamReader(URLConn.getInputStream());
		BufferedReader buff = new BufferedReader(inStream);
		String line = buff.readLine();
		int c = 0;
		while (line!=null) {

			if(c==1) {
				currPrice = getData(line);
				

		}

			
			c++;
			line=buff.readLine();
		}
		
	}
	
	//parses through the inputed String, in this case from parse. This returns the current adjusted price of the stock. 
	public double getData(String data) {
		int i = 0;
		int x=0;
		int count=0;
		for(int j = 0;j < data.length(); j++) {
			if(data.charAt(j) == ',') {
				count++;
			}
			if(count == 4) {
				 x=j;
				 count++;
			}
			if(count==6) {
				i = j;
				break;
			}
		}
		
		double ans = Double.parseDouble(data.substring(x+1,i)); 
		return ans;
	}
	
	//calculates the difference from purchase date
	public void getDifference() {
		difference = (currPrice*quantity)-(quantity*prevPrice);
		
	}

	//returns all specific data of a stock
	public void stockAnalysis() {
		System.out.println("The current price of " +tick+" is: $" +currPrice);
		System.out.println("The stock was purchased at: $" +prevPrice);
		System.out.println("Change in stock value: "+(int)difference+" (rounded to the nearest dollar).");
		System.out.println("Total value of investement in "+tick+" is: $"+totalAmnt);

	}
	
	//returns the amount of money initially invested in this stock
	public void getPrincipal() {
		principal = prevPrice*quantity;
	}
	//calculates total value based on the amount and current price of this particular stock
	public void totalToday() {
		totalAmnt = quantity*currPrice;
	}
}
