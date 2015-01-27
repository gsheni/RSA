import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;


public class RSA {
	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");
	private static final BigInteger NEGONE = new BigInteger("-1");

	public BigInteger privatekey;
	public BigInteger publickey;
	public BigInteger N;
	BigInteger s,p,q,e,d;
	
	public RSA(int n){
		p = ModularArithmetic.genPrime(n); //n-bit prime p
		q = ModularArithmetic.genPrime(n); //n-bit prime q
		while(true){
			if(p.equals(q)){
				p = ModularArithmetic.genPrime(n); //n-bit prime p
				q = ModularArithmetic.genPrime(n); //n-bit prime q
			}
			else{
				break;
			}
		}
		N = p.multiply(q); // n = p*q
		BigInteger s1 = (p.subtract(ONE));
		BigInteger s2 = (q.subtract(ONE));
		s = s1.multiply(s2); // s = (p-1)*(q-1)
		e = ModularArithmetic.genPrime(s.bitLength());
		BigInteger[] result = ModularArithmetic.extendedEuclid(s, e);
		while( !result[2].equals(ONE) && !(e.compareTo(s) == -1) ){
			e = ModularArithmetic.genPrime(s.bitLength());
			result = ModularArithmetic.extendedEuclid(s, e);
		}
		//BigInteger d2 = e.modInverse(s);
		//System.out.println("D shoudl be " + d);
				
		d = ModularArithmetic.moddiv(ONE, e, s);
		
		//System.out.println("result should be--- " + d2);
//		if(d.compareTo(ZERO) == -1 ){
//			d = d.add(s);
//		}
		publickey = e;
		privatekey = d;
//		System.out.println("publickey (" + N + ", " + publickey + ")");
//		 System.out.println("p: " + p);
//		 System.out.println("q: " + q);
//		 System.out.println("N: " + N);
//		 System.out.println("s: " + s);
//		 System.out.println("e: " + e);
//		 System.out.println("d: " + d);		 
	}
	public RSA(int n, String privatefilename, String publicfilename) throws IOException{
//		/* Borrows constructor definition from above */
//		try {
//		File privateDirectory = new File(".");
//		File publicDirectory = new File(".");
//
//		File pri = new File(privateDirectory.getCanonicalFile() + File.separator + privatefilename);
//		File pub = new File(publicDirectory.getCanonicalFile() + File.separator + publicfilename);
//
//		if(!pri.exists() || !pub.exists()) {
//		pri.createNewFile();
//		pub.createNewFile();
//		}
//
//		FileWriter privateWriter = new FileWriter(pri.getAbsoluteFile());
//		FileWriter publicWriter = new FileWriter(pub.getAbsoluteFile());
//
//		BufferedWriter privateBufferedWriter = new BufferedWriter(privateWriter);
//		BufferedWriter publicBufferedWriter = new BufferedWriter(publicWriter);
//
//		privateBufferedWriter.write(String.valueOf(this.N)+"\n");
//		publicBufferedWriter.write(String.valueOf(this.N)+"\n");
//
//		privateBufferedWriter.write(String.valueOf(this.privatekey));
//		publicBufferedWriter.write(String.valueOf(this.publickey));
//
//		privateBufferedWriter.close();
//		publicBufferedWriter.close();
//
//		} catch (IOException err) {
//		err.printStackTrace();
//		}
//	}
		this(n);
		String outputprivate = this.N.toString() +  "\n" + this.privatekey.toString() ;
		String outputpublic = this.N.toString() + "\n" + this.publickey.toString() ;
		
		//System.out.println("outputprivate: = " + outputprivate);
		//System.out.println("outputpublic: = " + outputpublic);

		
		File f = new File(privatefilename);
		File f2 = new File(publicfilename);
		
//		while(f.exists() && !f.isDirectory()) { 
//			f.createNewFile();
//		}
//		
//		while(f.exists() && !f2.isDirectory()) { 
//			f2.createNewFile();
//		}
		
		File directory = new File(".");
		File directory2 = new File(".");
		
		File finalpath = new File(directory.getCanonicalPath() + File.separator + privatefilename);
		File finalpath2 = new File(directory2.getCanonicalPath() + File.separator + publicfilename);

		//System.out.println(finalpath);
		//System.out.println(finalpath2);
		
		FileWriter fw = new FileWriter(finalpath);
		FileWriter fw2 = new FileWriter(finalpath2);
		
		BufferedWriter bw = new BufferedWriter(fw);
		BufferedWriter bw2 = new BufferedWriter(fw2);

		bw.write(outputprivate);
		bw2.write(outputpublic);
		bw.close();
		fw.close();
		bw2.close();
		fw2.close();
		//System.out.println("Done writing");
	}
	
	public RSA(String filename) throws IOException{
		if (filename.matches(".*public.*")) {
			File directory = new File(".");
			File finalpath = new File(directory.getCanonicalPath() + File.separator + filename);
			Scanner scan = new Scanner(finalpath);
			this.N = scan.nextBigInteger();
			System.out.println(this.N);
			this.publickey =scan.nextBigInteger();
			this.e = this.publickey;
			System.out.println(this.publickey);
			scan.close();
		}
		else{
		File directory = new File(".");
		File finalpath = new File(directory.getCanonicalPath() + File.separator + filename);
		Scanner scan = new Scanner(finalpath);
		this.N = scan.nextBigInteger();
		System.out.println(this.N);
		this.privatekey =scan.nextBigInteger();
		this.d = this.privatekey;
		System.out.println(this.privatekey);
		scan.close();
		}
	}
	public BigInteger encrypt(BigInteger m1, BigInteger N1, BigInteger e1){
		return ModularArithmetic.modexp(m1, e1, N1);
		//return m1.modPow(e1, N);

	}
	public BigInteger decrypt(BigInteger c){
		return ModularArithmetic.modexp(c, this.d, this.N);
		//return c.modPow(this.d, this.N);

	}
	public BigInteger paddingScheme(String message) {
		// This fully copied from Dr. Pauca's RSA driver since it was not required for the program
		int c;
		String intMessage = ""; // corresponding integer message to encrypt

		//System.out.println("original message = " + message);
		for(int i = 0; i < message.length(); i++){
			c = message.charAt(i);
			//System.out.print(String.format("%1$03d ", c));
			intMessage = intMessage + String.format("%1$03d", c);
		}
		return new BigInteger(intMessage);
	}
	public String depaddingScheme(BigInteger msg) {
		// This fully copied from Dr. Pauca's RSA driver since it was not required for the program
		String encodedMessage = msg.toString();
		String decryptedMessage = "";
		int a = 0;
		int b = 0;
		for (int i = 0; i < encodedMessage.length()/3; i++)		{
		a = encodedMessage.charAt(3*i); // decimal of charAt(3*i)
		// e.g. 48 for '1', 52 for '4'
		a = (a-48)*100;
		b = encodedMessage.charAt(3*i+1);
		a += (b-48)*10;
		b = encodedMessage.charAt(3*i+2);
		a += (b-48);
		decryptedMessage += (char)a;
		}
		return decryptedMessage;
		}	
}