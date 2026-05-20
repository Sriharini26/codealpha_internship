package Task2;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HashMap<String, Double> market = new HashMap<>();
		
		market.put("SHR",3500.0);
		market.put("CSK",1450.0);
		market.put("TVK",4450.0);
		
		portfolio user= new portfolio();
		
		int choice;
		
		do {
			System.out.println("\n===STOCK TRANDING PLATFROM===");
			System.out.println("1.Display Market Data");
			System.out.println("2.Buy Stock");
			System.out.println("3.sell Stock");
			System.out.println("4.view portfolio");
			System.out.println("5.Exit");
			
			System.out.println("Enter choice");
			choice = sc.nextInt();
			
			switch(choice) {
			
			case 1:  
				System.out.print("\n Available stocks:");
			
			for(String Stock : market.keySet()) {
				System.out.print(Stock+"- ₹" + market.get(Stock));
			}
			break;
			
			case 2:
				
				System.out.print("Enter Stock Name: ");
				String buyStock = sc.next();
				
				System.out.print("Enter Quantity");
				int buyQty = sc.nextInt();
				
				
				if(market.containsKey(buyStock)) {
					double total = market.get(buyStock) * buyQty;
				
					if(user.balance >= total) {
						
					
						user.balance -=total;
						
					
					user.holdings.put(
						buyStock,
						user.holdings.getOrDefault(buyStock, null)+ buyQty);
				System.out.println("purchased Successfully");
						
					}else {
						System.out.println("Insufficient Balance");
					}
				}
				
				else {
						System.out.println("Stock not found!");
					}
				
				break;
				case 3:
					
					System.out.print("Enter Stock Name:");
					String sellStock = sc.next();
					
					System.out.println("Enter quantity:");
					int sellQty = sc.nextInt();
					
					if(user.holdings.containsKey(sellStock)) {
						int ownedQty = user.holdings.get(sellStock);
						
						if(ownedQty >= sellQty) {
							double amount = market.get(sellStock) * sellQty;
							user.balance += amount;
							
							user.holdings.put(sellStock, ownedQty - sellQty);
							
							System.out.println("Stock sold Successfully");
						
							
						}else {
							System.out.println("Not Enough Shares");
						}
					}else {
							System.out.println("You Don't Own This Stock");
						}
						break;
						
						case 4:
							System.out.println("\n===Portfolio===");
							
							double totalValue = user.balance;
							
							for(String stock:user.holdings.keySet()) {
								int Qty = user.holdings.get(stock);
								
								double stockValue = Qty*market.get(stock);
								
								totalValue += stockValue;
								System.out.println(stock +"-"+ Qty +"shares");
								
							}
							System.out.println("Balance:₹"+user.balance);
							
							System.out.println("Total portfolio value:"+ totalValue);;
							
							break;
						case 5:
							System.out.println("Thank You!");
							break;
							
							default:
								
								System.out.println("Invalid Choice");
							}
							
					} while(choice!=5);
					
					sc.close();

		}
	}
					
