package hr.fer.zemris.linearna;

import javax.naming.OperationNotSupportedException;

/**
 * Sucelje koje sadrzi sve operacije sa vektorima
 * @author Tiyanak
 *
 */
public interface IVector {
	
	public double get(int number);
	public IVector set(int numberOne, double numberTwo);
	public int getDimension();
	public IVector copy();
	public IVector copyPart(int number);
	public IVector newInstance(int number);
	public IVector add(IVector vector) throws OperationNotSupportedException ;
	public IVector nAdd(IVector vector) throws OperationNotSupportedException;
	public IVector sub(IVector vector) throws OperationNotSupportedException;
	public IVector nSub(IVector vector) throws OperationNotSupportedException;
	public IVector scalarMultiply(double number);
	public IVector nScalarMultiply(double number);
	public double norm();
	public IVector normalize();
	public IVector nNormalize();
	public double cosine(IVector vector) throws OperationNotSupportedException;
	public double scalarProduct(IVector vector) throws OperationNotSupportedException;
	public IVector nVectorProduct(IVector vector) throws OperationNotSupportedException;
	public IVector nFromHomogeneus();
	public IMatrix toRowMatrix(boolean toRow);
	public IMatrix toColumnMatrix(boolean toColumn);
	public double[] toArray();

}
