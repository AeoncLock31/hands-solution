package io.robusta.java.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicorama on 21/06/2017.
 */
public class ListApp {

    List<Integer> buildList(int a, int b, int c){
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	list.add(0, a);
    	list.add(1, b);
    	list.add(2, c);
    	return list;
    }


    ArrayList<Integer> buildList(int... numbers){
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	int index = 0;
    	for (int i : numbers) {
    		list.add(index, i);
    		index++;
    	}
    	return list;
    }


    boolean equality(List<Integer> list1, List<Integer> list2){
    	boolean b = true;
    	if (list1.size()!=list2.size()) {
    		b=false;
    	} else {
    		for (int i = 0; i < list1.size(); i++) {
    			if(!list1.get(i).equals(list2.get(i))) {
    				b=false;
    			}
    		}
    	}
        return b;
    }

    int first(List<Integer> ints){
        return ints.get(0);
    }

    int last(List<Integer> ints){
        return ints.get(ints.size()-1);
    }

    int medium(List<Integer> ints){
       if (ints.size()%2!=0) {
        	return ints.get((ints.size()-1)/2);
        }else {
        	return ints.get(ints.size()/2-1);
        }
    }
}
