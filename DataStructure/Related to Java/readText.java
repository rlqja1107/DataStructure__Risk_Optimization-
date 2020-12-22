package DS_TeamProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class readText {

	public static HashMap<Integer,ArrayList<Double>> InvestTable=new HashMap<Integer,ArrayList<Double>>();
	public static HashMap<Double,Integer> z_scenario=new HashMap<Double,Integer>();
	public static HashMap<Integer,Double> scenario_z=new HashMap<Integer,Double>();
	public static HashMap<Double,ArrayList<Integer>> time_scenario=new HashMap<Double,ArrayList<Integer>>();



	public static void Text2(String filename) {
		try{
			FileInputStream fstream = new FileInputStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				try {
					String val12[]=strLine.split("\\s");


					if(time_scenario.keySet().contains(Double.valueOf(val12[2]))) {
						time_scenario.get(Double.valueOf(val12[2])).add(Integer.valueOf(val12[0]));

						continue;
					}
					ArrayList<Integer> list=new ArrayList<Integer>();
					list.add(Integer.valueOf(val12[0]));
					time_scenario.put(Double.valueOf(val12[2]),list);


				}
				catch (NumberFormatException e){
					System.err.println("Error: " + e.getMessage());
				}
			}

			fstream.close();
		} catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}


	public static void Text(String filename) {
		try{
			FileInputStream fstream = new FileInputStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;


			//Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				try {
					String val2[]=strLine.split("\\s");

					z_scenario.put(Double.valueOf(val2[2]),Integer.valueOf(val2[0]));   
					scenario_z.put(Integer.valueOf(val2[0]),Double.valueOf(val2[2]));

				}
				catch (NumberFormatException e){
					System.err.println("Error: " + e.getMessage());
				}
			}

			fstream.close();
		} catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	public static void Valueand(String filename) {


		try{   
			FileInputStream fstream = new FileInputStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;

			//Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				try {
					ArrayList<Double> list = new ArrayList<Double>();
					list.add(0d);
					String val2[]=strLine.split("\\s");
					int k=Integer.valueOf(val2[0]);

					for(int i=3;i<val2.length;i++) {

						list.add(Double.valueOf(val2[i]));
					}
					InvestTable.put(k,list);
				}
				catch (NumberFormatException e){
					System.err.println("Error: " + e.getMessage());
				}
			}


			fstream.close();
		}
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}}

