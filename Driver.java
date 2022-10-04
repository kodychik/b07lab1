import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
public class Driver {
	public static void main(String [] args) throws FileNotFoundException
	{
	/*
	Polynomial p = new Polynomial(); System.out.println(p.evaluate(3));
	double [] c1 = {6,0,0,5};
	Polynomial p1 = new Polynomial(c1);
	double [] c2 = {0,-2,0,0,-9};
	Polynomial p2 = new Polynomial(c2);
	Polynomial s = p1.add(p2);
	System.out.println("s(0.1) = " + s.evaluate(0.1));
	if(s.hasRoot(1))
	System.out.println("1 is a root of s");
	else
	System.out.println("1 is not a root of s");*/
	
	
	double [] c1 = {5, -3, 7};
	int [] p1 = {0, 2, 8};

	double [] c2 = {4, 2, 1, 10};
	int [] p2 = {0, 1, 2, 20};

	Polynomial p11 = new Polynomial(c1, p1);
	Polynomial p12 = new Polynomial(c2, p2);

	Polynomial p13 = p11.multiply(p12);
	
	for (int i = 0; i < p13.power.length; i++)
	{
		System.out.println(p13.power[i]);
		System.out.println(p13.coeff[i]);
	}


	double [] c3 = {3, 5, 8, -702};
	int [] p3 = {1, 2, 4, 0};

	Polynomial p21 = new Polynomial(c3, p3);

	System.out.println(p21.hasRoot(3));


	String a = "#-3x2+5x1#-6";
	String [] b = {"-3x2","5x1","6"};
	//String [] c = new String[3];
	//System.arraycopy(b, 0, c, 0, b.length);

	System.out.println(Character.compare(b[0].charAt(2),'x'));
	System.out.println(b[0].length());

	File file = new File("lab2.txt");
    Polynomial f = new Polynomial(file);
    System.out.println(Arrays.toString(f.coeff));
    System.out.println(Arrays.toString(f.power));

    f.saveToFile("do.txt");
    double c4[] = {-1,-2500,-453,750,34,90};
    int e4[] = {0,5,8,2,3,2};
    Polynomial n = new Polynomial(c4,e4);
    n.saveToFile("test.txt");
    System.out.println("*******************");

	/*
	for (int i = 0; i < b.length; i++)
	{
		System.out.println(b[i]);
	}
	System.out.println(b[0]);*/


	











	}
}