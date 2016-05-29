package hr.fer.zemris.linearna;

import javax.naming.OperationNotSupportedException;

/**
 * Sucelje koje sadrzi sve operacije sa matricama
 * @author Tiyanak
 *
 */
public interface IMatrix {

	public int getRowsCount();
	public int getColsCount();
	public double get(int numberOne, int numberTwo);
	public IMatrix set(int numberOne, int numberTwo, double numberThree);
	public IMatrix copy();
	public IMatrix newInstance(int numberOne, int numberTwo);
	public IMatrix nTranspose(boolean trans);
	public IMatrix add(IMatrix matrix) throws OperationNotSupportedException;
	public IMatrix nAdd(IMatrix matrix) throws OperationNotSupportedException;
	public IMatrix sub(IMatrix matrix) throws OperationNotSupportedException;
	public IMatrix nSub(IMatrix matrix) throws OperationNotSupportedException;
	public IMatrix nMultiply(IMatrix matrix) throws OperationNotSupportedException;
	public double determinant() throws OperationNotSupportedException;
	public IMatrix subMatrix(int numberOne, int numberTwo, boolean subM);
	public IMatrix nInvert() throws OperationNotSupportedException;
	public double[][] toArray();
	public IVector toVector(boolean toVect) throws OperationNotSupportedException;
	public IMatrix scalarMultiply(double value);
	public IMatrix nScalarMultiply(double value);
	
}

