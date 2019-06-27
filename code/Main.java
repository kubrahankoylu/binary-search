package likeGoogle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		String key;
		int k = 0;
		ArrayList<attributeOfFile> a =new ArrayList<attributeOfFile>(); 
		int numberOfElement=0;
		
		try{
			Scanner scanner = new Scanner(args[2]);
			key = scanner.nextLine();//keyword
			scanner = new Scanner(args[1]);
			k = Integer.parseInt(scanner.nextLine());//how many will be written
			scanner = new Scanner(new File(args[0]));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				String[] noun = line.split("\t");//each of line splitting by tab
				noun[0]=noun[0].replaceAll("\\s+","");//this line is ignore white spaces in start of line 
				if(noun.length==1){
					numberOfElement = Integer.parseInt(noun[0]);//It shows how many elements the file has
				}
				else{
					attributeOfFile temp =new attributeOfFile();
					temp.setId(Integer.parseInt(noun[0]));//the previous number from the tab character on each line is assigned to the id of the temp
					temp.setName(noun[1]);//the next word(s) after the tab character on each line is assigned to the name of the temp
					a.add(temp);//each of the element in the input file add to Array List a
				}
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println("No File Found!");//print the error message when the file is not found
			return;
			}
		
		a = sort(a,0,numberOfElement);//sorts the elements in a array list alphabetically.
		ArrayList<attributeOfFile> bs= new ArrayList<attributeOfFile>();
		bs = binaryS(key,a,0,numberOfElement-1);//With binary search, it assigns what they find to bs array list
		bs = sort(bs, 1, bs.size());//sorts the elements in the bs array list according to their id(in ascending order)
		bs = translate(bs);//reorder by descending order

		for(int i = 0 ; i<k ; i++){
			System.out.println(bs.get(i).getId()+" "+bs.get(i).getName());
		}
	}
	
	public static ArrayList<attributeOfFile> sort(ArrayList<attributeOfFile>  a , int pre , int size)
    {
        int n = size;
        attributeOfFile temp;
        for (int i = n / 2 - 1; i >= 0; i--)
            a = heap(a, n, i, pre);
        for (int i=n-1; i>=0; i--)
            {
        		temp =a.get(0);
                a.set(0, a.get(i));
                a.set(i, temp);
                a = heap(a, i, 0, pre);
            }
        return a;
    }
 
	public static ArrayList<attributeOfFile>  heap(ArrayList<attributeOfFile> a, int n, int i, int pre)
    {
		//The reason for pre-fetching as a parameter is to look at the name or id that is to be compared.
        int largest = i;  //parent
        int l = 2*i + 1;  //left child
        int r = 2*i + 2;  //rigth child
 
        if(pre==0){//compare to names
        	if (l < n && (a.get(largest).compareTo1(a.get(l)))<0)//if left child is larger then parent
        		largest = l;
 
        	if (r < n && (a.get(largest).compareTo1(a.get(r)))<0)//if right child is larger then parent
        		largest = r;
 
        	if (largest != i){//if largest is not parent
        		attributeOfFile swap = a.get(i);
        		a.set(i, a.get(largest));
        		a.set(largest, swap);
        		//sort every little piece
        		a = heap(a, n, largest,pre);//compare to the smallest element
        	}
        }
        else if(pre==1){//compare to ids
        	if (l < n && (a.get(largest).compareTo2(a.get(l)))<0)//if left child is larger then parent
        		largest = l;
 
        	if (r < n && (a.get(largest).compareTo2(a.get(r)))<0)//if right child is larger then parent
        		largest = r;
 
        	if (largest != i){
        		attributeOfFile swap = a.get(i);
        		a.set(i, a.get(largest));
        		a.set(largest, swap);
        		//sort every little piece
        		a = heap(a, n, largest,pre);//compare to the smallest element
        	}
        }
        return a;
    }
	
	public static ArrayList<attributeOfFile> binaryS(String key, ArrayList<attributeOfFile> a, int lo, int hi)
	{
		ArrayList<attributeOfFile> bs=new ArrayList<attributeOfFile>();
		int mid = 0;
		lo=0;
		hi=a.size()-1;
		String temp;
		int find;
		
		while(lo!=hi && lo<=hi){//if lo= hi,this array has one element on the other hand if lo >hi, this array is empty
			mid = (lo+hi)/2;
			temp = a.get(mid).getName();//assigns the median indexed element of a to temp
			find = key.compareToIgnoreCase(temp.substring(0, key.length()));
			//compares to keyword and the word length of the median indexed element of a
			//if keyword equals to the word length of the median indexed element of a, go to "else if(find == 0)"
			//if keyword less then the word length of the median indexed element of a, go to "if(find < 0)"
			//if keyword greater then the word length of the median indexed element of a,go to "else if(find > 0)"

			if(find <0){
				hi=mid-1;
			}
			else if(find >0){
				lo=mid+1;
			}
			else if(find == 0){
				bs.add(a.get(mid));//adds the first matching item to the bs array list
				break;
			}
		}
		boolean cond =true;
		// start the linear search
		for(int i=mid ; cond ;i--){//linearly search the left side of the median indexed element of a array list
			temp = a.get(i).getName();
			find = key.compareToIgnoreCase(temp.substring(0, key.length()));
			if(find == 0){
				bs.add(a.get(i));
				cond = true;
			}
			else{
				break;
			}
			//when it finds the first element that does not match the keyword.
		}
		cond = true;
		for(int i = mid ; cond ; i++){//linearly search the right side of the median indexed element of a array list
			temp = a.get(i).getName();
			find = key.compareToIgnoreCase(temp.substring(0, key.length()));
			if(find == 0){
				bs.add(a.get(i));
				cond = true;
			}
			else{
				break;
			}
			//when it finds the first element that does not match the keyword.
		}
		return bs;//add the other matching items to the bs array list
	}
	
	public static ArrayList<attributeOfFile> translate(ArrayList<attributeOfFile> a){
		int i = a.size() - 1;
		ArrayList<attributeOfFile> bs = new ArrayList<attributeOfFile>();
		while(i>=0){
			bs.add(a.get(i));
			i--;
		}
		return bs;
	}
}
