import java.math.BigInteger;

public class RSA_Encryption {

	public static void main(String[] args) {
		ModularArithmetic testmod = null;
		BigInteger a, b, c, N;
		a = new BigInteger("12"); 
		b = new BigInteger("8" ); 
		N = new BigInteger("15");
		System.out.println("Testing for small.");
		//System.out.println(testmod.modadd(a, b, N));
		//System.out.println(testmod.modmult(a, b, N));
		//System.out.println(testmod.modexp(b,a, N));
		a = new BigInteger("7"); 
		b = new BigInteger("3"); 
		BigInteger result[] = testmod.extendedEuclid(a,b);
		System.out.println("result1: " +result[0]);
		System.out.println("result1: " +result[1]);
		System.out.println("result1: " +result[2]);
		System.out.println(testmod.moddiv(a,b, N));

		int k = 4;
		BigInteger P = new BigInteger("23");
		System.out.println(testmod.isPrime(P, 4) );
		P = new BigInteger("25");
		System.out.println(testmod.isPrime(P, 4) );
		P = new BigInteger("91");
		System.out.println(testmod.isPrime(P, 4) );
		
		
		System.out.println();
		System.out.println("Testing for large.");
		ModularArithmetic testmod_large = null;
		a = new BigInteger("952545454"); 
		b = new BigInteger("834352542" ); 
		N = new BigInteger("5643436545");
		System.out.println(testmod_large.modadd(a, b, N));
		System.out.println(testmod_large.modmult(a, b, N));
		System.out.println(testmod_large.modexp(a, b, N));
		a = new BigInteger("952545454"); 
		b = new BigInteger("834352543" ); 
		N = new BigInteger("5643436545");
		System.out.println(testmod_large.moddiv(a, b, N));
		P = new BigInteger("285044579391958034764329004428399141293");
		System.out.println(testmod_large.isPrime(P, 128) );



	}

}
