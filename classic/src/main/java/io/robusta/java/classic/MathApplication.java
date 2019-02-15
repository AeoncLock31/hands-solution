package io.robusta.java.classic;

public class MathApplication {

    public int addition(int x, int y) {
        return x+y;
    }

    public int multiplication(int x, int y) {
        return x*y;
    }

    public int[] addition1(int x1, int y1, int x2, int y2) {

        // array as result
        int[] result = {x1+x2 , y1+y2};
        return result;
    }

    public int[] addition2(int[] vector, int[] vector2) {

        // array as result
        int[] result = {vector[0]+vector2[0], vector[1]+vector2[1]};
        return result;
    }

    public Vector addition3(Vector vector, Vector vector2) {

        // Vector as result
        Vector result = new Vector(vector.getX()+vector2.getX() , vector.getY()+vector2.getY());
        return result;
    }




    public int factorial(int n) {
        int result = 0;
        if (n==0) {
    	result = 1;
        } else {
        		result = n * factorial(n-1);
        }
        return result;
    }

    /**
     * Makes the sum from 0 to n: 0 + 1 + 2 + 3 + ... + n
     */
    public int sumAll(int n) {
    	int result = 0;
        if (n==0) {
    	result = 0;
        } else {
        	result = n + sumAll(n-1);       
        }
        return result;
    }


}
