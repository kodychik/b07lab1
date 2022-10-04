import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Polynomial {

	//double [] poly;
	int [] power;
	double [] coeff;


	public Polynomial()
	{
		power = new int[1];
		power[0] = 0;
		coeff = new double[1];
		coeff[0] = 0;
	}

	public Polynomial(double [] coeffArray, int [] powerss)
	{
		coeff = new double[coeffArray.length];
		power = new int[powerss.length];

		for (int i = 0; i < coeffArray.length; i++)
		{
			power[i] = powerss[i];
			coeff[i] = coeffArray[i];
		}
	}

	/*
		double po1[] = new double[Math.max(this.poly.length, po.poly.length)];
		if (this.poly.length > po.poly.length)
		{
			for (int i = 0; i < po.poly.length; i++) po1[i] = po.poly[i];
			//for (int j = po.poly.length; j < this.poly.length; j++) po1[j] = 0;
		}
		else if (this.poly.length < po.poly.length)
		{
			for (int i = 0; i < this.poly.length; i++) po1[i] = this.poly[i];
			//for (int j = this.poly.length; j < po.poly.length; j++) po1[j] = 0;
		}

		if (this.poly.length != po.poly.length)
		{
			double po2[] = new double[po1.length];
			for (int k = 0; k < po1.length; k++)
			{
				if (this.poly.length > po.poly.length) po2[k] = po1[k] + this.poly[k];
				else po2[k] = po1[k] + po.poly[k];
			}
			return new Polynomial(po2);
		}
		double po2[] = new double[po.poly.length];
		for (int l = 0; l < po.poly.length; l++) po2[l] = po.poly[l] + this.poly[l];

		return new Polynomial(po2);*/

	public Polynomial simplify(double [] coeffArray, int [] powerss)
	{
		int count = 0;
		for (int i = 0; i < coeffArray.length; i++)
		{
			for (int j = 0; j < coeffArray.length; j++)
			{
				if (powerss[i] == powerss[j] && i == j) break;
				
				else if (powerss[i] == powerss[j] && i != j)
				{
					coeffArray[j] += coeffArray[i];
					coeffArray[i] = 0;
					powerss[i] = 0;
					count++;
					break;
				}
				/*
				else if (powerss[i] == powerss[j] && j > i)
				{
					coeffArray[i] += coeffArray[j];
					coeffArray[j] = 0;
					powerss[j] = 0;
					count++;
				}*/
			}
		}

		double [] newCoeff = new double[coeffArray.length - count];
		int [] newPow = new int[powerss.length - count];
		int count2 = 0;

		for (int k = 0; count2 < newCoeff.length; k++)
		{
			if (coeffArray[k] == 0) continue;

			else
			{
				newCoeff[count2] = coeffArray[k];
				newPow[count2] = powerss[k];
				count2++;
			}
		}
		return new Polynomial(newCoeff, newPow);
	}

	public Polynomial add(Polynomial po)
	{
		int [] newPow = new int[this.power.length + po.power.length];
		double [] newCoeff = new double[this.power.length + po.power.length];
		// concatenate 2 lists together, 2 times
		// once for newPow, once for new Coeff
		// write helper function to remove redundancies from both lists
		// return Polynomial form of it

		for (int i = 0; i < this.power.length; i++)
		{
			newPow[i] = this.power[i];
			newCoeff[i] = this.coeff[i];
		}

		for (int j = 0; j < po.power.length; j++)
		{
			newPow[j + this.power.length] = po.power[j];
			newCoeff[j + this.coeff.length] = po.coeff[j];
		}

		return simplify(newCoeff, newPow);
	}
	
	public double evaluate(double x)
	{
		Polynomial p1 = new Polynomial();
		p1 = simplify(this.coeff, this.power);

		/*
		int a[] = new int[this.power.length];
		a = this.power.clone();
		double b[] = new double[this.coeff.length];
		b = this.coeff.clone();*/

		double result = 0;

		for (int i = 0; i < p1.coeff.length; i++)
		{
			result = result + p1.coeff[i] * Math.pow(x, p1.power[i]);
		}
		return result;
	}

	public boolean hasRoot(double x)
	{
		if (evaluate(x) == 0) return true;
		return false;
	}

	public Polynomial multiply(Polynomial popo)
	{
		/* (length k * length n -> output k * n length array)
		*/
		int [] newPow = new int[this.power.length * popo.power.length];
		double [] newCoeff = new double[this.coeff.length * popo.coeff.length];
		int count = 0;

		for (int i = 0; i < popo.power.length; i++)
		{
			for (int j = 0; j < this.power.length; j++)
			{
				newPow[count] = popo.power[i] + this.power[j];
				newCoeff[count] = popo.coeff[i] * this.coeff[j];
				count++;
			}
		}
		return simplify(newCoeff, newPow);

	}
	
	public Polynomial (File file) throws FileNotFoundException
	{
		Scanner input = new Scanner(file);
		String line = input.nextLine();
		//System.out.println(line);
		int count = 0;
		String splitted = line.replace("-", "#-");
		String [] splitted1 = splitted.split("[#+]");
		String [] splitted2 = new String[splitted1.length - 1];
		int k = 0;


		if (line.charAt(0) == '-') 
		{
			power = new int[splitted1.length - 1];
			coeff = new double[splitted1.length - 1];
		}
		else
		{
			power = new int[splitted1.length];
			coeff = new double[splitted1.length];
		}

		for (k = 0; k < splitted2.length; k++)
		{
			splitted2[k] = splitted1[k + 1];
		}

		k = 0;

		//String [] splitted3 = new String[];
		//System.copy
		//System.arraycopy()
		//if (line.charAt(0) == '-') 
		String temp = "";
		int i;
		if (line.charAt(0) == '-')
		{
			for (i = 0; i < splitted2.length; i++)
			{
				//System.out.println(Character.compare(splitted2[i].charAt(2), 'x'));
				while (Character.compare(splitted2[i].charAt(k), 'x') != 0 && k < splitted2[i].length())
				{
					temp = temp + splitted2[i].charAt(k);
					k++;
				}

				if (!temp.isEmpty()) coeff[count] = Double.parseDouble(temp);
				System.out.println(coeff[count]);
				temp = "";
				k++;
				while (k < splitted2[i].length())
				{
					temp = temp + splitted2[i].charAt(k);
					k++;
				}
				if (!temp.isEmpty()) power[count] = Integer.parseInt(temp);
				System.out.println(power[count]);
				temp = "";
				k = 0;
				count++;
			}
		}
		else
		{
			for (i = 0; i < splitted1.length; i++)
			{
				while (Character.compare(splitted1[i].charAt(k), 'x') != 0 && k < splitted1[i].length())
				{
					temp = temp + splitted1[i].charAt(k);
					k++;
				}

				if (!temp.isEmpty()) coeff[count] = Double.parseDouble(temp);
				System.out.println(coeff[count]);
				temp = "";
				k++;
				while (k < splitted1[i].length())
				{
					temp = temp + splitted1[i].charAt(k);
					k++;
				}
				if (!temp.isEmpty()) power[count] = Integer.parseInt(temp);
				System.out.println(power[count]);
				temp = "";
				k = 0;
				count++;
			}
		}
	}
		
	public void saveToFile(String x) throws FileNotFoundException
	{
		String temp = "";
		for (int i = 0; i < this.coeff.length; i++)
		{
			if (i >= 1 && this.coeff[i] > 0) temp = temp + '+';

			temp = temp + Double.toString(this.coeff[i]);

			if (this.power[i] != 0)
			{
				temp = temp + 'x';
				temp = temp + Integer.toString(this.power[i]);
			}
		}
		PrintStream ps = new PrintStream(x);
		ps.println(temp);
		ps.flush();
	}



	
}