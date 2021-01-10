import java.util.Arrays;
import java.util.Random;

public class MultiDS<T> implements PrimQ<T>, Reorder {
	
	private T[] theData;  // Note that the data is an array of T
	// You will also need one or more state variables to keep track of your
	// logical data.  The variables you need depend on how you will manage
	// your queue.
	
	//Logical size is total occupied slots
	private int logSize = 0;
	
	private Random R;  // This is needed so that you can shuffle your data

	public MultiDS(int sz)
	{
		theData = (T[]) new Object[sz];  // Note how this is created
		R = new Random();
		logSize = sz-1;
		// You will also need to initialize your other state variable(s).
		// You will need to create a new array in a similar way in your
		// resize() method.
	}

	// Below you should implement all of the methods in the PrimQ<T> and Reorder
	// interfaces.  See the details for these methods in files PrimQ.java and
	// Reorder.java.  See how these are actually used in the test program Assig4A.java.
	// Once you have completed this class the program Assig4A.java should compile and
	// run and give output identical to that shown in file A4Out.txt (except for the
	// order of the data after shuffling, since that should be random).
	
	public void reverse() {
		for(int i = 0; i<theData.length/2; i++) {
			T temp = theData[i];
			theData[i] = theData[theData.length - i -1];
			theData[theData.length-i-1] = temp;
		}
	}

	
	public void shiftRight() {
		int b = 0;
		T lastVal = theData[logSize-1];
		while(lastVal==null) {
			logSize-=b;
			lastVal = theData[logSize-1];
			b++;
		}
		for(int i  = logSize-1; i>0; i--) {
			theData[i] = theData[i-1];
		}
		theData[0] = lastVal;
	}

	
	public void shiftLeft() {
		T firstVal = theData[0];
		for(int i =0; i<theData.length-1; i++) {
			theData[i] = theData[i+1];
		}
		theData[theData.length-1] = firstVal;
	}

	
	public void shuffle() {
		for(int i = 0; i<logSize; i++) {
			int changeIndex = R.nextInt(theData.length);
			T temp = theData[changeIndex];
			theData[changeIndex] = theData[i];
			theData[i] = temp;
			}
	}

	
	public boolean addItem(T item) {
		if(theData.length >= logSize) {
			theData = Arrays.copyOf(theData, logSize+1);
		}
		//logSize++;
		theData[theData.length-1] = item;
		logSize++;
		return true;
	}
	
//	public boolean addItem(T item) {
//		for(int i = 0; i < theData.length; i++){
//			if(this.full()){
//				T[] newArray = (T[]) new Object[theData.length+1];
//				newArray[i] = item;
//				theData = newArray;
//				return true;
//			}
//			else
//			{
//				if(theData[i]==null){
//					theData[i] = item;
//					return true;
//				}
//				else
//					continue;
//			}
//		}
//		return true;
//	}
	

	
	public T removeItem() {
		//Check to see if array is empty.
		if(this.empty()) {
			return null;
		}
		else {
		//Always removing oldest value
		int index = 0;
		//Gets the last non-null index in the array.
		for(int i = 0; i < theData.length; i++) {
			if(theData[i]!=null) {
				index = i;
				break;
			}
			else
				continue;
		}
		T tempT = theData[index];
		theData[index] = null;
		logSize--;
		return tempT;
	}
	}

	
	public boolean full() {
		for(int i = 0; i < theData.length; i++) {
			if(theData[i]==null) {
				return false;
			}
			else
				continue;
		}
		return false;
	}

	
	public boolean empty() {	
		boolean isEmpty = true;
		for(int i = 0; i<theData.length; i++) {
			if(theData[i] != null) {
				isEmpty = false;
				break;
			}
		}
		return isEmpty;
	}

	
	public int size() {
		logSize = 0;
		for (int i = 0; i < theData.length; i++) {
			if (theData[i] != null)
				logSize++;
			else
				continue;
		}
		return logSize;
	}

	
	public void clear() {
		for(int i = 0; i < theData.length; i++) {
			theData[i] = null;
		}
		//logSize = 0;
		
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder("");
		for(T i : theData) {
			if(i!=null)
				s.append(i+ " ");
		}
		return s.toString();
	}

}
