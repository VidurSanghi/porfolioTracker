package GoogleStockReader;

import java.io.IOException;
import java.util.*;
public class Stockcalc {
	//calls all functions
	public static void main(String[] args) throws IOException {
		ArrayList<Stock>  stockList = new ArrayList<Stock>();
		Scanner in = new Scanner(System.in);
		Intro();
		boolean checkTrue = true;
		gatherInfo(stockList, checkTrue);
		System.out.println();
		System.out.println("Below is a summary of the current porfolio value.");
		System.out.println("Principal amount invested: $"+ principal(stockList));
		System.out.println("Current portfolio value: $"+ totalValue(stockList));
		System.out.println("Change in portfolio value: $"+(int)totalChange(stockList)+" (rounded ot the nearest dollar).");
		System.out.println();
		System.out.println("Do you want the summary for a specific stock in the portfolio? (yes or no)");
		specificData(stockList, in.nextLine());
		
	}
	//sets up program and gives user instructions
	public static void Intro() {
		System.out.println("Hello, this is a Portfolio Analyzer.");
		System.out.println("Inputs: summary of stocks in the portfolio.");
		System.out.println("Outputs: analysis of current portfolio value.");
		System.out.println();
		System.out.println("Please answer the following question to input your portfolio data.");
		System.out.println();

	}
	
	//gets all user input and creates an object, which is stored into an array.
	public static void gatherInfo(ArrayList<Stock> stockList, boolean checkTrue) throws IOException {
		
		Scanner in = new Scanner(System.in);
		
		

		while(checkTrue==true) {
			System.out.println("Ticker symbol for stock?");
			String a = in.nextLine();
	
			System.out.println("Stock purchase date? (year-month-day format)");
			String b = in.nextLine();
			
			System.out.println("Stock purchase price?");
			double d = in.nextDouble(); 
			
			System.out.println("Number of shares held currently");
			int c = in.nextInt();

		
			Stock tick = new Stock(a, b, c, d);
			tick.parse();
			

			tick.getDifference();

			tick.getPrincipal();
			tick.totalToday();
			stockList.add(tick);

			
			System.out.println("If there are any additional stocks in the portfolio, enter 'continue', else enter 'exit'.");
			String check1 = in.nextLine();
			String check2 = in.nextLine();
			if(check1.equals("exit")||check1.equals("Exit")||check2.equals("exit")||check2.equals("Exit")) {
			checkTrue = false;
			}
			else {checkTrue = true;}
	
				
		}
		
	}
	//returns specific data about a particular stock.
	public static void specificData(ArrayList<Stock> stockList, String init) throws IOException{
		Scanner in = new Scanner(System.in);
		boolean check=true;
		if(init.equals("yes")||init.equals("Yes")) {
		while (check) {
				System.out.println("Ticker symbol for the stock?");
				String tick = in.nextLine();
				boolean printCheck = false;
				for(int i=0;i<stockList.size();i++) {
					if(tick.equals(stockList.get(i).tick)) {
						stockList.get(i).stockAnalysis();
						printCheck = true;
						break;
					}
					
				}
			if(!printCheck) {
					System.out.println("Please enter a valid ticker.");
				}
				
			System.out.println("Would you like to continue? (yes or no)");
			String check3 = in.nextLine();
			if(check3.equals("yes")||check3.equals("Yes")) {
				check=true;
			}
			else {
				System.out.println("Thank you for using the Portfolio Analyzer, happy investing!.");
				check=false;
				}
			}
		}
		else {
			System.out.println("Thank you for using the Portfolio Analyzer, happy investing!");
		}
		
		
	}
	
	//shows total growth in $ of portfolio
	public static double totalChange(ArrayList<Stock> stockList) throws IOException{
		double total = 0;
		for(int i=0;i<stockList.size();i++) {
			Stock var = stockList.get(i);
			total = total + var.difference;
		}
		return total;
	}
	//returns total invested initially
	public static double principal(ArrayList<Stock> stockList) throws IOException{
		double total = 0;
		for(int i=0;i<stockList.size();i++) {
			Stock var = stockList.get(i);
			total = total+var.principal;
		}
		return total;
	}
	//returns total value of portfolio today
	public static double totalValue(ArrayList<Stock> stockList) throws IOException {
		double total = 0;
		for(int i=0;i<stockList.size();i++) {
			Stock var = stockList.get(i);
			total = total+(var.totalAmnt);
		}
		return total;
	}
}

