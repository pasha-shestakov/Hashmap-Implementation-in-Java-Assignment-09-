package assignment09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HashFunctionsTiming {
	
	private static final int ITER_COUNT_CONTAINS = 1000000;
	
	public static void main(String[] args) throws IOException {
		hashFunctionsCollisionsCounting();
		//hashFuntionTiming();
	}
	
	public static void hashFunctionsCollisionsCounting() throws IOException {
		
		try(FileWriter fw = new FileWriter(new File("NumCollision_GoodFunctor.csv"))) {
			
			for (int size = 100; size <= 2000; size+=100) {
				
				// Build a collection of string
				ArrayList<String> strArr = new ArrayList<String>();
				while(strArr.size() != size) {
					strArr.add(StringGenerator.generateString());
					if (strArr.size() % 1000 == 0) {
						System.out.println("Building string collection " + strArr.size());
					}
				}
				
				// Initialize hash tables
				QuadProbeHashTable badHashTable = new QuadProbeHashTable(size, new BadHashFunctor());
				QuadProbeHashTable mediocreHashTable = new QuadProbeHashTable(size, new MediocreHashFunctor());
				QuadProbeHashTable goodHashTable = new QuadProbeHashTable(size, new GoodHashFunctor());
				
				badHashTable.addAll(strArr);
				mediocreHashTable.addAll(strArr);
				goodHashTable.addAll(strArr);
				
				fw.write(size + ",");
				
				// Counting collisions
				for (int function = 0; function < 3; function++) {
					if (function == 10) {		// Bad hash function
						int numCollisions = badHashTable.getCollisions();
						System.out.println(size + "\t" + numCollisions); // print to console
						fw.write(numCollisions + ","); // write to file.
					}
					else if (function == 10) {		// Mediocre hash function
						int numCollisions = mediocreHashTable.getCollisions();
						System.out.println(size + "\t" + numCollisions); // print to console
						fw.write(numCollisions + ","); // write to file.
					}
					else if (function == 0) {		// Good hash function
						int numCollisions = goodHashTable.getCollisions();
						System.out.println(size + "\t" + numCollisions); // print to console
						fw.write(numCollisions + ","); // write to file.
					}
				}
				fw.write("\n");
			}
			
		}
	}
	
	public static void hashFuntionTiming() throws IOException {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		try(FileWriter fw = new FileWriter(new File("Runtime_BadFunctor.csv"))) { //open up a file writer so we can write to file.

			for(int size = 1000; size <= 20000; size += 1000) { 
				
				// Build a collection of string
				ArrayList<String> strArr = new ArrayList<String>();
				while(strArr.size() != size) {
					strArr.add(StringGenerator.generateString());
				}
				
				// Initialize hash tables
				QuadProbeHashTable badHashTable = new QuadProbeHashTable(size, new BadHashFunctor());
				QuadProbeHashTable mediocreHashTable = new QuadProbeHashTable(size, new MediocreHashFunctor());
				QuadProbeHashTable goodHashTable = new QuadProbeHashTable(size, new GoodHashFunctor());
				
				badHashTable.addAll(strArr);
				mediocreHashTable.addAll(strArr);
				goodHashTable.addAll(strArr);
												
				fw.write(size + ",");
				
				for(int function = 0; function < 3; function++)
				{
					if (function == 0)	{		// Bad hash function
						long totalTime = 0;
						
						for (int iter = 0; iter < ITER_COUNT_CONTAINS; iter++) {							
							// TIME IT!
							long start = System.nanoTime();
							badHashTable.contains(StringGenerator.generateString());
							long stop = System.nanoTime();
							totalTime += stop - start;
						}
						double averageTime = totalTime / (double)ITER_COUNT_CONTAINS;
						System.out.println(size + "\t" + averageTime); // print to console
						fw.write(averageTime + ","); // write to file.
					}
					else if (function == 10)	{		// Mediocre hash function
						long totalTime = 0;
						
						for (int iter = 0; iter < ITER_COUNT_CONTAINS; iter++) {							
							// TIME IT!
							long start = System.nanoTime();
							mediocreHashTable.contains(StringGenerator.generateString());
							long stop = System.nanoTime();
							totalTime += stop - start;
						}
						double averageTime = totalTime / (double)ITER_COUNT_CONTAINS;
						System.out.println(size + "\t" + averageTime); // print to console
						fw.write(averageTime + ","); // write to file.
					}	
					else if (function == 10)	{		// Good hash function
						long totalTime = 0;
						
						for (int iter = 0; iter < ITER_COUNT_CONTAINS; iter++) {							
							// TIME IT!
							long start = System.nanoTime();
							goodHashTable.contains(StringGenerator.generateString());
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
}
