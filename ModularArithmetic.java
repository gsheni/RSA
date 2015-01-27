import java.math.BigInteger;
import java.util.Random;

public class ModularArithmetic {
	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");
	private static final BigInteger NEGONE = new BigInteger("-1");
	
	public static BigInteger  modadd(BigInteger  a, BigInteger   b, BigInteger  N){
		// returns c = a + b (mod N)
		// could have also done 
		// a.remainder(N) + b.remainder(N)
		// did the simplest step possible
		return a.add(b).remainder(N);
	}
	public static BigInteger  modmult(BigInteger  a, BigInteger  b, BigInteger  N){
		// return c = a * b (mod N)
		// could have also done 
		// a.remainder(N) * b.remainder(N)
		// did the simplest step possible
		return a.multiply(b).remainder(N);
	}
	public static BigInteger modexp(BigInteger  a, BigInteger  b, BigInteger  N){
//		 modPow(BigInteger exponent, BigInteger m)
//		 TODO: ASK PAUCA IF OKAY to use modPow
//		 easy  answer = a.modPow(b, N)
//		 below solution is based on wikipedia algorithm, book didn't work for me
//		 Its right to left binary method 
//		 wikipedia pseudocode, my explanation
		BigInteger result = ONE; //result := 1, set result to one
	    a = a.mod(N); // base := base mod modulus, find the remainder when the base, a, is mod by N
		for (int i = 0; i < b.bitLength(); i++) { //while exponent > 0 is what wikpedia said, i just iterated through the n-bit size of b
			// the for loop also remove the first binary digit, well doesn't remove but iterates through
			// TODO: ASK PAUCA IF OKAY for testbit function
				if (b.testBit(i)) { //if (exponent mod 2 == 1):, it converts b to binary notation when it uses the testbit function, to see if its true, if it set
					result = result.multiply(a).mod(N); // result := (result * base) mod modulus,  does what it says
				}
				a = a.multiply(a).mod(N); //base := (base * base) mod modulus, does what it says
		}
	    return result; //return result, does what it says 
//		return a.modPow(b, N);
	}
	public static BigInteger  moddiv(BigInteger  a, BigInteger  b, BigInteger  N){
		//need to find the inverse so we use extendedEuclid, since it can
		BigInteger[] gcd = extendedEuclid(N, b); // it finds the inverse
		BigInteger temp = gcd[1].mod(N); // now we get the inverse out of the array
		return a.multiply(temp).remainder(N); // multiple the by the inverse and mod as usual math
	}
	public static BigInteger[] extendedEuclid(BigInteger  a, BigInteger  b){
		if (!(a.compareTo(b) == 1)){ // if a is not greater than b then system error
			System.out.println(a);
			System.out.println(b);
			System.out.println("Error");
			System.exit(0);// the book says that a has to be greater than b.
			}
        BigInteger[] ans = new BigInteger[3]; // array to output the result
        if (b.compareTo(ZERO) == 0) { // this is the base case, being that b is zero
        	ans[0] = ONE; //return 1 as per book
        	ans[1] = ZERO; //return 0 as per book
        	ans[2] = a; // return a as per book
        	}
        else {
            BigInteger[] temp = extendedEuclid(b, a.mod(b)); // this is just like the book's recursive call
            ans[0] = temp[1]; // the first result is y, so its the 2nd spot in the array
            ans[1] = temp[0].subtract(temp[1].multiply(a.divide(b))); // x'-[a/b]y'
            ans[2] = temp[2]; // the divisor, the gcd
        	} 
        return ans;
        //result[0] = x
        //result[1] = y
        //result[2] = d the divisor, the gcd
	}

	public static boolean isPrime(BigInteger  N, int  k){
		//following book's algorithm
		BigInteger a, ans, tmp; //hold values later on
		for(int i = 0 ; i < k ; i++){ // keep going until reached k
			Random rand = new Random(Integer.MAX_VALUE); // this makes a random integer
			// I tried to use System.nanoTime(), or some other time based seed so it kept chaning
			// however, that didn't work, so I changed it to a constant seed
			BigInteger upperLimit = N.add(ZERO);// this is the N-1, for the limit of random biginteger 
	        do {
	        	a = new BigInteger(upperLimit.bitLength(), rand); //get  a randomly generated BigInteger
	        	// it is uniformly distributed over the range 0 to (2^(upperLimit) - 1), inclusive
	        }
	        while(a.compareTo(upperLimit) >= 0); // if the number made is the same as N-1, or greater than it try again
	        if( a.compareTo(ZERO) == 0){ // the range includes zero, so make it 1 if it is 0, as per book
	        	a = a.add(ONE); // make it 1
	        }
	        tmp =  N.add(NEGONE); // this is the N-1, the exponent
	        ans = modexp(a, tmp, N); // use our modexponent function to get result
	        if (!(ans.compareTo(ONE) == 0)) { // if the ans is not equal to 1, then return false
	        	return false; 
	        }
		}
	return true;// after going through 1 to k, then you know its equal to 1 for all cases than thus return true
	}

	 public static BigInteger genPrime(int n){				
	    	BigInteger numberofbits = new BigInteger(Integer.toString(n));
 
	    	BigInteger k = numberofbits.subtract(ONE);
	    	BigInteger smallest = computelargexponent(TWO, k);
	    	while(true){
		    	Random rand = new Random();
		    	BigInteger a = new BigInteger(n, rand);
	    		if ( isPrime(a, n + 9999 ) &&  a.compareTo(ONE) == 1  && a.compareTo(smallest) >= 0){ 
	    			return a;
	    		}
	    	}
	 }
	 
	 public static BigInteger computelargexponent(BigInteger a, BigInteger b){
		 //I had noticed that in Dr. Pauca's program, he was computing the smallest nbit integer
		 //by doing 2^k
		 //so the smallest 16 bit integer would be 2^16
		 //I googled how to compute large exponents faster, since these numbers would be huge and 
		 //came across the Exponentiation by squaring
		 //using the psuedocode I wrote this 
		 BigInteger result = ONE;
		 while (!(b.equals(ZERO))){
	        if (!(b.and(ONE).equals(ZERO))){
	            result = result.multiply(a);
	        }
	        b = b.shiftRight(1);
	        a = a.multiply(a);
	    }
	    return result;
	 }
}

//	public static BigInteger genPrime(int  n){
//    	//TODO: CHANGE!!!
//		Random rand = new Random();
//		return new BigInteger(n, 100, rand);
//	}
