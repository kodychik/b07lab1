public class Polynomial {

	double [] poly;

	public Polynomial()
	{
		poly = new double[1];
		poly[0] = 0;
	}

	public Polynomial(double [] myarray)
	{
		poly = new double[myarray.length];
		for (int i = 0; i < myarray.length; i++) poly[i] = myarray[i];
	}

	public Polynomial add(Polynomial po)
	{
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

		return new Polynomial(po2);
	}

	public double evaluate(double x)
	{
		double result = 0;
		for (int i = 0; i < this.poly.length; i++)
		{
			result = result + this.poly[i] * Math.pow(x, i);
		}
		return result;
	}

	public boolean hasRoot(double x)
	{
		if (evaluate(x) == 0) return true;
		return false;
	}
	/*
	public static void main(String [] argz)
	{
		//double [] inputarray = {1.0, 2.0, 3.0, 4.0, 5.0};
		//Polynomial p1 = new Polynomial(inputarray);
		double arra[] = new double[]{1, 2, 3, 4, 5};
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial(arra);
		for (double i : p1.poly)
		{
			System.out.println(i);
		}
		for (double j : p2.poly)
		{
			System.out.println(j);
		}
		
	}*/
	
}