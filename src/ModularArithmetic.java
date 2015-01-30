import java.math.BigInteger;
import java.util.Random;

public class ModularArithmetic {
	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");
	public static BigInteger  modadd(BigInteger  a, BigInteger   b, BigInteger  N){
		return a.add(b).remainder(N);
	}
	public static BigInteger  modmult(BigInteger  a, BigInteger  b, BigInteger  N){
		return a.multiply(b).remainder(N);
	}
	public static BigInteger  moddiv(BigInteger  a, BigInteger  b, BigInteger  N){
		BigInteger[] gcd = extendedEuclid(N, b);
		assert(gcd[2].compareTo(ONE) == 0);
		BigInteger temp;
		temp = gcd[1].remainder(N);
		BigInteger temp_2 = a.multiply(temp);
		temp_2 = temp_2.remainder(N);
		return temp_2;
	}
	public static BigInteger[] extendedEuclid(BigInteger  a, BigInteger  b){
		assert(a.compareTo(b) == -1);
		BigInteger[] result;
		if (b == ZERO){
			result = new BigInteger[3];
			result[0] = ONE;
			result[1] = ZERO;
			result[2] = a;
			return result;
		}
		BigInteger temp;
		temp = a.remainder(b);
		System.out.println(a + ", " + b + ", " + temp);
		result = extendedEuclid(b, temp);
		BigInteger[] temp2 = new BigInteger[3];
		BigInteger xp = result[0];
		BigInteger yp = result[1];
		BigInteger dp = result[2];
		result[0] = yp;
		BigInteger div = (a.divide(b));
		BigInteger sub = xp.subtract(div);
		result[1] = sub.multiply(yp);
		result[2] = dp;
		return result;	
	}
	public static BigInteger modexp(BigInteger  a, BigInteger  b, BigInteger  N){
		//modPow(BigInteger exponent, BigInteger m)
		// TODO: ASK PAUCA IF OKAY 
		return a.modPow(b, N);
	}
	public static boolean isPrime(BigInteger  N, int  k){
		BigInteger a_value;
		BigInteger neg_one = new BigInteger("-1");
		BigInteger n_minus_one = N.add(neg_one);
		BigInteger pos_one = new BigInteger("1");
		BigInteger check;
		for(int i = 0; i < k ; i++){
	        Random rand = new Random();
	        BigInteger upperLimit = n_minus_one;
	        do {
	        	a_value = new BigInteger(upperLimit.bitLength(), rand); // (2^4-1) = 15 is the maximum value
	        }
	        while(a_value.compareTo(upperLimit) >= 0);   // exclusive of 13
	        a_value.add(pos_one);
		    check = modexp(a_value, n_minus_one, N );
		    if( check != ONE){
		    	return false;
		    }
		}
		return true;
	}
	public static BigInteger genPrime(BigInteger  N){
		Random rand = new Random();
		int maxNumBitLength = N.bitLength();
		BigInteger random_prime_possible = new BigInteger(maxNumBitLength, rand);
		boolean prime_check = isPrime(random_prime_possible, 100);
		while(!prime_check){
			 random_prime_possible = new BigInteger(maxNumBitLength, rand);
			 prime_check = isPrime(random_prime_possible, 100);
		}
		return random_prime_possible;
	}
}
