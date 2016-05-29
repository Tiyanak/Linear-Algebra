package hr.fer.zemris.linearna;

import javax.naming.OperationNotSupportedException;

public class Demo {
	
	public static void main(String[] args) throws OperationNotSupportedException, CloneNotSupportedException{
		
		double[] values = {-1, 4, -1};
		
		IVector k1 = Vector.parseSimple("2 3 -4");
		IVector k2 = new Vector(values);
		
		IVector v1 = k1.nAdd(k2);
		System.out.println("v1 = " + v1.toString());
		
		double s = v1.scalarProduct(k2.copy());
		System.out.println("s = " + s);
		
		IVector v2 = v1.nVectorProduct(Vector.parseSimple("2 2 4"));
		System.out.println("v2 = " + v2.toString());
		
		IVector v3 = v2.normalize();
		System.out.println("v3 = " + v3.toString());
		
		IVector v4 = v2.nScalarMultiply(-1);
		System.out.println("v4 = " + v4.toString());
		
		IMatrix x1 = Matrix.parseSimple("1 2 3 | 2 1 3 | 4 5 1");
		IMatrix x2 = Matrix.parseSimple("-1 2 -3|5 -2 7|-4 -1 3");
		
		IMatrix M1 = x1.nAdd(x2);
		System.out.println("M1 = \n" + M1.toString());
		
		IMatrix M2 = x1.nMultiply(x2.nTranspose(true));
		System.out.println("M2 = \n" + M2.toString());
		
		IMatrix A = Matrix.parseSimple("-24 18 5 | 20 -15 -4 | -5 4 1");
		IMatrix B = Matrix.parseSimple("1 2 3 | 0 1 4 | 5 6 0");
		System.out.println("A invert = \n" + A.nInvert());
		System.out.println("B invert = \n" + B.nInvert());
	
		IMatrix M3 = A.nInvert().nMultiply(B.nInvert());
		System.out.println("M3 = \n" + M3);
		
		double M4 = A.determinant();
		System.out.println(M4);
		
		IVector r = Vector.parseSimple("3 4");
		IVector t = Vector.parseSimple("4 3");
		System.out.println(r.cosine(t));
	
		
	}
}
