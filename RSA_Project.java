import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA_Project {
	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");
	private static final BigInteger NEGONE = new BigInteger("-1");
	
	public static void main(String[] args) throws IOException {
//		BigInteger b = new BigInteger("16");
//		System.out.println(ModularArithmetic.computelargexponent(TWO, b));
		
//		RSA receiver = new RSA(1048, "privatekeytestkevin.txt", "publickeytestkevin.txt");
		//----------------------------
//		RSA messagesender = new RSA("publickeytestkevin.txt");
//		String message = "sample message";
//		System.out.println("Original Message: " + message);
//
//		BigInteger encodedMessage = messagesender.paddingScheme(message);
//		System.out.println("Encoded Message: " + encodedMessage);
//		
//		//encrypt(BigInteger m1, BigInteger N1, BigInteger e1
//		BigInteger encryptedMessage = messagesender.encrypt(encodedMessage, messagesender.N, messagesender.publickey);
//		System.out.println("Encrypted Message: " + encryptedMessage);
		//--------------------------------------
//		RSA receiver = new RSA("privatekeytestkevin.txt");
//
//		BigInteger decryptedMessage = receiver.decrypt(new BigInteger("2613574872591309751026636761555769412983450061859065561046681264981409076629543765050001533807839999482479583187680957824687267191087714512999203133937471769079539366329971429138275204031466354085421486467529772537319857482079700762947129601328478418415461324415117793085602659828917161896404620319226311400105108568386504700097986652320334244414224071683659985406831643496310525020306410413974784181644468951520012707740454575311726424511242656715415975572884719467391575934948222163139348806916699524216918794954343111113599747761326799051865078393381792618760209973139777722025323910444543709411037887528269847348433915543753909"));
//		System.out.println("Decrypted Message: " + decryptedMessage);
//
//		String decodedMessage = receiver.depaddingScheme(decryptedMessage);
//		System.out.println("Decoded Message: " + decodedMessage);
		
	}
}
