import java.io.*;
import java.util.*;
import java.lang.*;

class Pilot 
{	
	public static int freq=0;
	public static int threshold=3;
	public static int size_win_str;
	public static int[] win_str;
	public static HashSet<List<Integer>> twod_set;

	public static int CheckPatternUtil(int[] a,int start){
		int p=start;
		int count=0;
	//	System.out.println("check pattern util working and p="+p);
		for(int i=0;i<a.length;i++){
			while(p<size_win_str&&a[i]!=win_str[p]){
				p++;
			}
			if(p<size_win_str&&a[i]==win_str[p]){
				count++;
				p++;
			}else
				break;
		}
		if(count==a.length)
			freq++;
		if(freq>=threshold){
		   // System.out.print("Cartel :");
		   List<Integer> l1=new ArrayList<Integer>();
			for(int i=0;i<a.length;i++){
				//System.out.print(a[i]+" ");
				l1.add(a[i]);
			}
		//	System.out.println();
			twod_set.add(l1);
			p=-1;
		}			
       // System.out.println(p);
		return p;
	}
	public static void CheckPattern(int[] a,int n){
		int start=0;
		while(start+n-1<size_win_str){
		    	//	System.out.println("check pattern working and start="+start);
	            	start=CheckPatternUtil(a,start);
		if(start==-1)
			return;
		}
		
	}
	public static void printArr(int a[], int n)
	{
		int[] temp=new int[n];
	//	System.out.println("PrintArr working");
	//	System.out.print("Checking : ");

		for (int i = 0; i < n; i++){
			temp[i]=a[i];
		//	System.out.print(a[i] + " ");
		}
	//	System.out.println();
		freq=0;
		CheckPattern(temp,n);
	}

	// Generating permutation using Heap Algorithm
	public static void heapPermutation(int a[], int size, int n)
	{
		// if size becomes 1 then prints the obtained
		// permutation
		if (size == 1)
			printArr(a, n);
			
		//System.out.println("hp working");	

		for (int i = 0; i < size; i++) {
			heapPermutation(a, size - 1, n);

			// if size is odd, swap 0th i.e (first) and
			// (size-1)th i.e (last) element
			if (size % 2 == 1) {
				int temp = a[0];
				a[0] = a[size - 1];
				a[size - 1] = temp;
			}

			// If size is even, swap ith 
			// and (size-1)th i.e last element
			else {
				int temp = a[i];
				a[i] = a[size - 1];
				a[size - 1] = temp;
			}
		}
	}
	
	public static void main(String[] args) 
	{
		System.out.println("************************************************");
		System.out.println("Program to demonstrate pattern Matching");
		System.out.println();
		System.out.println("List of Trader ids who won the auction");
		int[] arr={4,1,5,2,3,1,2,3,5,1,4,2,1,3,5,2};
		twod_set=new HashSet<List<Integer>>();
		//Winner Trader Ids of an auction sorted according to time
		win_str=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			win_str[i]=arr[i];
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		System.out.println();
		//size of win_str
		size_win_str=win_str.length;
		//list of traders who get nearly equal profits on a periodic basis
		System.out.println("List of trader ids whose profits are nearly equal");
		int[] prof_list={1,2,3,5};
		//min no.of traders in a cartel
		for(int i=0;i<prof_list.length;i++)
			System.out.print(prof_list[i]+" ");
		System.out.println();
		System.out.println();
		int min_trad=3;
		//ma no.of traders in cartel = size of prof_list
		int max_trad=prof_list.length;
		int size_of_prof_list=max_trad;
		//choosing every combination of prof_list and chechikng if a pattern is found in win_str
		for(int i=min_trad;i<=max_trad;i++){
			heapPermutation(prof_list,size_of_prof_list,i);
		}
		System.out.println("List of cartels :");
		System.out.println(twod_set);
	}
}
