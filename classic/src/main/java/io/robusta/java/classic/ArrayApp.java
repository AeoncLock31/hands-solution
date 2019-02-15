package io.robusta.java.classic;

import java.util.ArrayList;

/**
 * Created by nicorama on 21/06/2017.
 */
public class ArrayApp {



    String[] buildEmptyArray(int size){
    	return (new String[size]);
    }

    int[] buildArray(int a, int b, int c){
        int[] array = {a, b, c};
        return array;               
    }


    boolean equality(int[] array1, int[] array2){
    	boolean b = true;
    	if (array1.length == array2.length) {
    		for (int i = 0; i<=(array1.length-1); i++){
    			if(array1[i]!=array2[i]) {
    				b=false;
    			}
    		}
    	}else b = false;
    	return b;
    }
    	

    String asString (int [] numbers){
    	String message = "";
    	for (int i : numbers) {
    		message = (message + i + ":");
    	}
        return message;
    }

    String asStringJoin (int [] numbers){
    	String message = "";
    	for (int i : numbers) {
    		if (i == numbers[numbers.length-1]) {
    			message = (message + i);
    		} else message = (message + i + ":");	
    	}
        return message;
    }

    String asString (String [] strings){
    	String message = "";
    	for (String s : strings) {
    		if (s == strings[strings.length-1]) {
    			message = (message + s);
    		} else message = (message + s + " ");	
    	}
        return message;
    }
    
    String asString (Card [] cards){
    	String message = "[";
    	for (Card c : cards) {
    		if (c == cards[cards.length-1]) {
    			message = (message + c.getValue() + c.getColor() +"]");
    		} else message = (message + c.getValue() + c.getColor() + " ");	
    	}
        return message;
    }

}
