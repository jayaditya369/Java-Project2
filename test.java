
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import sorting.*;
import algorithmDesign.*;
public class test {
	
	int l=100000;                           // for sorting
	int size;                               // Stores String Length
	int number;                             // Stores number of times
	int pairs=1000;                         // for sequence
	Long [ ] a = new Long[ l ];         // array of type Long
	Long [ ] b = new Long[ l ];         // COPY of array a 
	String [ ] c = new String[ l ];     // array of type String
	String [ ] d = new String[ l ];     // COPY of array c

	public static void main(String args[])
	{
		int n,select;
		test t = new test();
		Scanner input = new Scanner(System.in);
		while(true)
		{
			System.out.println("Select Option ");
			System.out.printf("1.Sort 2.Sequence 3.Exit : ");
			select=input.nextInt();
			if(select==3)
				break;
			switch(select)
			{
				case 1 :                              // Sorting
				{
					while(true)
					{	System.out.println("  Select Option");
						System.out.printf("  1.GeneralSorts 2.RadixSort 3.Exit : ");
						n=input.nextInt();
						
						if(n==3)
							break;
						System.out.printf("    Enter String length : ");
						t.size=input.nextInt();
						//System.out.printf("    Number of Times to repeat : ");
						//t.number=input.nextInt();
						if(n==2)
							t.number=10;
						if(n==1)
							t.number=100;
						if(n!=1&&n!=2&&n!=3)
							System.out.println("Invalid Input");
						t.sort(n);
					}                                               // Close of Inner While Loop
					break;
				}                                                   // Close of Case 1
				case 2 :                            // Sequence
				{
					System.out.printf("    Enter String length : ");
					t.size=input.nextInt();
					t.sequence();
					break;
				}                                                   // Close of Case 2
			}                                                       // Close of Switch Case
		}                                                           // Close of Outer While Loop
			input.close();
	}
	public void sort(int n)
	{

	    String generatedString;          // A string to store the random string generated
	    long start1,start2;
	    long sumM=0,sumQ=0,sumH=0,sumDQ=0,sumR=0;
	    long avg;
	    long string1;                    // A long variable to store the random string generated

	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(l);
	    for(int k=0; k<number; k++)                     // Loop1 to repeat n number of times
	    {
	    	for(int j=0 ; j<l ; j++)                    // Loop2 to generate 100000 no. of strings
	    	{
	    		string1=0;
	    		for (int i = 0 ; i < size ; i++)        // Loop3 to generate string of size= size entered
	    		{
	    			int num = (int)(random.nextFloat()*10);
	    			buffer.append(num);            // buffer of type string
	    			if(num==0)
	    				num++;
	    			string1 = Math.abs((string1*10)+num);  // string2 of type long	
	    		}                                       // Close of Loop3 (size loop)
	    		generatedString=buffer.toString();
	    		
	    		a[j]=string1;
	    		b[j]=a[j];
	    
	    		c[j]=generatedString;
	    		d[j]=c[j];
	    		buffer = new StringBuilder(l);
	    	}                                          // Close of Loop2 (1,00,000 strings loop)
	    	
	    	
	    	if(n==1)
	    	{
	    		start1 = System.nanoTime();
	    		Sort.mergeSort(a); 						// Merge Sort
	    		start2 = System.nanoTime();
	    		copy(a,b);
	    		sumM = sumM + (start2 - start1);
	    		
	    		start1 = System.nanoTime();
	    		Sort.quicksort(a); 						// Quick Sort
	    		start2 = System.nanoTime();
	    		copy(a,b);
	    		sumQ = sumQ + (start2 - start1);
	    		
	    		start1 = System.nanoTime();
	    		Sort.heapsort(a);						// Heap Sort
	    		start2 = System.nanoTime();
	    		copy(a,b);
	    		sumH = sumH + (start2 - start1);
	    		
	    		start1 = System.nanoTime();
	    		Arrays.sort(a);							// Dual-Pivot Sort
	    		start2 = System.nanoTime();
	    		sumDQ = sumDQ + (start2 - start1);	
	    	}
	    	if(n==2)
	    	{
	    		start1 = System.nanoTime();
	    		Sort.mergeSort(c);						// Merge Sort
	    		start2 = System.nanoTime();
	    		copy(c,d);
	    		sumM = sumM + (start2 - start1);
	    		
	    		start1 = System.nanoTime();
	    		Sort.quicksort(c);						// Quick Sort
	    		start2 = System.nanoTime();
	    		copy(c,d);
	    		sumQ = sumQ + (start2 - start1);
	    		
	    		start1 = System.nanoTime();
	    		Sort.heapsort(c);						// Heap Sort
	    		start2 = System.nanoTime();
	    		copy(c,d);
	    		sumH = sumH + (start2 - start1);
	    		
	    		start1 = System.nanoTime();
	    		Arrays.sort(c);							// Dual-Pivot Quick Sort
	    		start2 = System.nanoTime();
	    		copy(c,d);
	    		sumDQ = sumDQ + (start2 - start1);
	    		
	    		start1 = System.nanoTime();
	    		RadixSort.radixSortA( c , size );		// Radix Sort
	    		start2 = System.nanoTime();
	    		sumR = sumR + (start2 - start1);
	    	}

	    }                                             // Close of Loop1

	    avg = sumM/number;
	    System.out.println("      Avg Time took for (MERGE SORT) for "+number+" times : "+avg/1000000+" ms");
	    avg = sumQ/number;
	    System.out.println("      Avg Time took for (QUICK SORT) for "+number+" times : "+avg/1000000+" ms");
	    avg = sumH/number;
	    System.out.println("      Avg Time took for (HEAP SORT) for "+number+" times : "+avg/1000000+" ms");
	    avg = sumDQ/number;
	    System.out.println("      Avg Time took for (DUAL PIVOT QUICKSORT) for "+number+" times : "+avg/1000000+" ms");
	    avg = sumR/number;
	    if(n==2)
	    	System.out.println("      Avg Time took for (RADIX SORT) for "+number+" times : "+avg/1000000+" ms");
	}
	
	public <AnyType> void copy(AnyType [] a, AnyType [] b)
	{
		for(int i=0;i<l;i++)
			a[i] = b[i];
	}
	
	public void sequence()
	{
		long start1,start2,sum=0,avg;
		String generatedString1, generatedString2;
		int left=65;                                 // ASCII for A
		int right=90;                                // ASCII for Z
	    Random random = new Random();
	    StringBuilder buffer1 = new StringBuilder(pairs);
	    StringBuilder buffer2 = new StringBuilder(pairs);
		for(int j=0 ; j<pairs ; j++)                 // Loop1 to generate 1000 pair of strings
    	{
    		for (int i = 0 ; i < size ; i++)         // Loop2 to generate string of size= size entered
    		{
    			int string1 = left + (int)(random.nextFloat() * (right - left + 1));
    			buffer1.append((char)string1);
    			int string2 = left + (int)(random.nextFloat() * (right - left + 1));
    			buffer2.append((char)string2);
    		}                                        // Close of Loop2 (size loop)
    		generatedString1=buffer1.toString();
    		generatedString2=buffer2.toString();
    		//System.out.println(generatedString1+" "+generatedString2);
    		start1= System.nanoTime();
    		int d = Sequences.editDistance(generatedString1, generatedString2);
    		start2= System.nanoTime();
    		sum = sum + (start2 - start1);
    		buffer1 = new StringBuilder(pairs);
    		buffer2 = new StringBuilder(pairs);
    	}                                            // Close of Loop1 (number of pairs)
		avg = sum/pairs;
		System.out.println("Avg Time to Compute distance of string size="+size+",1000 pairs is : "+avg+" ns");
	}
}
