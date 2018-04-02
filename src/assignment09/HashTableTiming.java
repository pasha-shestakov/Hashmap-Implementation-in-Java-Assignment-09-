package assignment09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HashTableTiming {
	
	private static final int ITER_COUNT_CONTAINS = 100000;
	
	public static void main(String[] args) throws IOException {
		//hashTableCollisionsCounting();
		hashTableContainsTiming();
	}
	
	public static void hashTableCollisionsCounting() throws IOException {
		try(FileWriter fw = new FileWriter(new File("ChainTable_GoodFunctor_experiment.csv"))) {
			
			for (int size = 1000; size <= 200000; size+=1000) {
				
				// Build a collection of string
				ArrayList<String> strArr = new ArrayList<String>();
				while(strArr.size() != size) {
					strArr.add(StringGenerator.generateString());
				}
				
				// Initialize hash tables
				QuadProbeHashTable quadTable = new QuadProbeHashTable(size, new GoodHashFunctor());
				ChainingHashTable chainingTable = new ChainingHashTable(size, new GoodHashFunctor());
				
				quadTable.addAll(strArr);
				chainingTable.addAll(strArr);
				
				fw.write(size + ",");
				
				// Counting collisions
				for (int function = 0; function < 2; function++) {
					if (function == 10) {		// Quad probe hash table
						int numCollisions = quadTable.getCollisions();
						System.out.println(size + "\t" + numCollisions); // print to console
						fw.write(numCollisions + ","); // write to file.
					}
					else if (function == 0) {		// Chaining hash table
						int numCollisions = chainingTable.getCollision();
						System.out.println(size + "\t" + numCollisions); // print to console
						fw.write(numCollisions + ","); // write to file.
					}
				}
				fw.write("\n");
			}
			
		}
	}
	
	public static void hashTableContainsTiming() throws IOException {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		try(FileWriter fw = new FileWriter(new File("ChainTable_Runtime_GoodFunctor.csv"))) { //open up a file writer so we can write to file.

			for(int size = 1000; size <= 20000; size += 1000) { 
				
				// Build a collection of string
				ArrayList<String> strArr = new ArrayList<String>();
				while(strArr.size() != size) {
					strArr.add(StringGenerator.generateString());
				}
				
				// Initialize hash tables
				QuadProbeHashTable quadTable = new QuadProbeHashTable(size, new GoodHashFunctor());
				ChainingHashTable chainingTable = new ChainingHashTable(size, new GoodHashFunctor());
				
				quadTable.addAll(strArr);
				chainingTable.addAll(strArr);
												
				fw.write(size + ",");
				
				for(int function = 0; function < 2; function++)
				{
					if (function == 10)	{		// Quad probe hash table
						long totalTime = 0;
						
						for (int iter = 0; iter < ITER_COUNT_CONTAINS; iter++) {							
							// TIME IT!
							long start = System.nanoTime();
							quadTable.contains(StringGenerator.generateString());
							long stop = System.nanoTime();
							totalTime += stop - start;
						}
						double averageTime = totalTime / (double)ITER_COUNT_CONTAINS;
						System.out.println(size + "\t" + averageTime); // print to console
						fw.write(averageTime + ","); // write to file.
					}
					else if (function == 0)	{		// Chaining hash table
						long totalTime = 0;
						
						for (int iter = 0; iter < ITER_COUNT_CONTAINS; iter++) {							
							// TIME IT!
							long start = System.nanoTime();
							chainingTable.contains(StringGenerator.generateString());
							long stop = System.nanoTime();
							totalTime += stop - start;
						}
						double averageTime = totalTime / (double)ITER_COUNT_CONTAINS;
						System.out.println(size + "\t" + averageTime); // print to console
						fw.write(averageTime + ","); // write to file.
					}	
				}
				fw.write("\n");
			}
		}
	}
	
	public static void hashTableAddTiming() {
		
	}
}
