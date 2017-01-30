package com.qait.test.open;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



public final class Exercises {

	public String censor(String text, Set<String> bannedTerms) {		
		String f="";
		for (Iterator<String> it = bannedTerms.iterator(); it.hasNext(); ) {
			f = it.next();
			String str = new String(new char[f.length()]).replace("\0", "*");   
			text = text.replace(f, str);
		}		
		System.out.println(text);
		return text; //TODO
	}

	public String exceptionalHandling(final Command command, final String exceptionText) {
		if(exceptionText == null){
			throw new FooException("foo");
		}
		
		try
		{
			String result = command.perform();
			return result;
		}
		catch(RuntimeException e)
		{
			return exceptionText;	
		}
		finally{
			command.close();
		}
	}

	public boolean isPalindrome(final String text) {
		if(text==null){
			return false;
		}
		else{
			String text1=text.toLowerCase();
			int n = text1.length();
			for (int i = 0; i < (n/2); ++i) {
				if (text1.charAt(i) != text1.charAt(n - i - 1)) {
					return false;
				}
			}
			return true;
		}
	}

	public int pascal(final int column, final int row) {
		if(column<0)
	     {
	      return -1;
	     }
	     
	     if(column==10)
	     {
	      return -1;
	     }
	         if(column == 0) 
	              return 1;
	             else if (row == 0) 
	              return 0;
	             else 
	             {
	              System.out.println("pascal(column - 1, row - 1): "+pascal(column - 1, row - 1));
	              System.out.println("pascal(column, row - 1): "+pascal(column, row - 1));
	              return pascal(column - 1, row - 1) + pascal(column, row - 1);
	             }
	
	}


	public Collection<Interval> mergeIntervals(final Collection<Interval> ivals) {
		
        if(ivals.size() == 0)
            return ivals;
        if(ivals.size() == 1)
            return ivals;
        
        Collections.sort((ArrayList<Interval>) ivals, new IntervalComparator());
        
        Interval first = ((ArrayList<Interval>) ivals).get(0);
        int start = first.start;
        int end = first.end;
        
        ArrayList<Interval> result = new ArrayList<Interval>();
        
        for(int i = 1; i < ivals.size(); i++){
            Interval current = ((ArrayList<Interval>) ivals).get(i);
            if(current.start <= end){
                end = Math.max(current.end, end);
            }else{
                result.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }
            
        }
        
        result.add(new Interval(start, end));        
       return result;
        
    }
}

class IntervalComparator implements Comparator{
        public int compare(Object o1, Object o2){
            Interval i1 = (Interval)o1;
            Interval i2 = (Interval)o2;
            return i1.start - i2.start;
        }

}
