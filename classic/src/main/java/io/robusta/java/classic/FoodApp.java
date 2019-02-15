package io.robusta.java.classic;

/**
 * Created by nicorama on 22/06/2017.
 */
public class FoodApp {


    static final int TRAVEL_COST = 2;

    int money = 0;
    int foodStock = 1000;



    void reset(){
    	money = 0;
    	foodStock = 1000;

    }

    int deliver(int food){
    	foodStock = foodStock - food;
    	money = money + food*2 - TRAVEL_COST;
    	return food *2;
    }

    int deliverAll(int count, int food){
    	for (int i = 1 ; i<=count; i++) {
    		deliver(food);
    	}
    	return count*food*2;
    }


}
