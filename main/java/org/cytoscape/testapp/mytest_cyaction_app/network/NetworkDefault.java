package org.cytoscape.testapp.mytest_cyaction_app.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkDefault {
	
	//public static List<String[]> createNetwork(String fileName) throws FileNotFoundException{
	public static List<List<String>> createNetwork(String fileName) throws FileNotFoundException{
		
		Scanner sc = new Scanner(new File(fileName));
		List<List<String>> result = new ArrayList<List<String>>();
		//List<String[]> result = new ArrayList<String[]>();
		List<String> Protein1 = new ArrayList<String>();
		List<String> Protein2 = new ArrayList<String>();
		List<String> Score = new ArrayList<String>();
		
		while (sc.hasNext()) {
			Protein1.add(sc.next());
			Protein2.add(sc.next());
			Score.add(sc.next()); 
			}
		sc.close();
		
		result.add(Protein1);
		result.add(Protein2);
		result.add(Score);

//		String[] node_1 = new String[Protein1.size()-1];
//		String[] node_2 = new String[Protein2.size()-1];
//		String[] score  = new String[Score.size()-1];
//		
//		for(int i=1; i<Protein1.size(); i++) {
//			node_1[i-1] = Protein1.get(i);
//			node_2[i-1] = Protein2.get(i);
//			score[i-1] = Score.get(i);
//		}
//		
//		result.add(node_1);
//		result.add(node_2);
//		result.add(score);
		return result;
	}
}
