import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class Main {
	private static Invoice[] invoices = {  
			new Invoice(83, "Electric sander", 7, 57.98),
			new Invoice(24, "Power saw", 18, 99.99),
			new Invoice(7, "Sledge hammer", 11, 21.50),
			new Invoice(77, "Hammer", 76, 11.99),
			new Invoice(39, "Lawn mower", 3, 79.50),
			new Invoice(68, "Screwdriver", 106, 6.99),
			new Invoice(56, "Jig saw", 21, 11.00),
			new Invoice(3, "Wrench", 34, 7.50),
			new Invoice(45, "Wrench", 13, 7.50),
			new Invoice(22, "Hammer", 47, 11.99)  }; 
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("Welcome to invoices management system\n");
			System.out.println("Funtions:Select/Report/Sort");
			System.out.print("Choice(-1 to exit):");
			Scanner scanner=new Scanner(System.in);
			String funtion =scanner.nextLine();
			
			if(funtion.equals("-1"))
				System.exit(0);
			else {
				funtion=funtion.toLowerCase();
			}
			while((!funtion.equals("select"))&&(!funtion.equals("report"))&&(!funtion.equals("sort")))
			{
				System.out.print("Wrong Input. Enter Again:");
				funtion=scanner.nextLine();
				funtion=funtion.toLowerCase();
			}
			
			
			if(funtion.equals("report"))
			{

				System.out.println("Invoices group by description:");
				
				Map<String, Double>map = Arrays.asList(invoices)
						.stream()
						.collect(Collectors.groupingBy(value->value.getPartDescription(),Collectors.summingDouble(value->value.getPrice()*value.getQuantity())));
				map.entrySet()
				.stream()
				.sorted(Comparator.comparing(Entry::getValue))
				.forEach(value->{
					System.out.printf("Description: %-19sInvoice amount: %10.2f\n", value.getKey(),value.getValue());
				});
				
			}else if(funtion.equals("select"))
			{
				System.out.print("Input the range to show(min,max):");
				String string=scanner.nextLine();
				String[] str=string.split(",");
				double min=Double.valueOf(str[0]);
				double max=Double.valueOf(str[1]);
				System.out.println("Invoices map to description and invoice amount for invoices in the range "+min+"-"+max);
				Arrays.asList(invoices)
				.stream()
				.collect(Collectors.groupingBy(value->value.getPrice()*value.getQuantity(),TreeMap::new,Collectors.toList()))
				.entrySet()
				.stream()
				.filter(value->value.getKey()<=max&&value.getKey()>=min)
				.forEach(entry->{
					entry.getValue().forEach(invoice->{
						System.out.printf("Description: %-19sInvoice amount: %10.2f\n", invoice.getPartDescription(),entry.getKey());
					});
				});
			}else if(funtion.equals("sort"))
			{
				System.out.print("Input the attribute to sort\nID/Name/Price/Quantity:");
				String str=scanner.nextLine();
				str=str.toLowerCase();
				System.out.println("Result of Invoices sort by part number("+str+"):");
				switch(str)
				{
				default:
				case "id":
					Arrays.asList(invoices)
					.stream()
					.sorted(Comparator.comparing(value->value.getPartNumber()))
					.forEach(value->
					{
						System.out.println(value);
					});
					break;
				case "name":
					Arrays.asList(invoices)
					.stream()
					.sorted(Comparator.comparing(value->value.getPartDescription()))
					.forEach(value->
					{
						System.out.println(value);
					});
					break;
				case "price":
					Arrays.asList(invoices)
					.stream()
					.sorted(Comparator.comparing(value->value.getPrice()))
					.forEach(value->
					{
						System.out.println(value);
					});
					break;
				case "quantity":
					Arrays.asList(invoices)
					.stream()
					.sorted(Comparator.comparing(value->value.getQuantity()))
					.forEach(value->
					{
						System.out.println(value);
					});
					break;
				}
			}
		}
		
		

	}

}
