package hr.fer.zemris.linearna;

import javax.naming.OperationNotSupportedException;

/**\
 * Klasa predstavlja pogled na matricu u obliku vektora
 * @author Tiyanak
 *
 */
public class VectorMatrixView {

	private int dimension;
	private boolean rowMatrix;
	private IMatrix matrix;
	
	/**
	 * Konstruktor radi novi pogled sa zadanom matricom i zastavicom koja kaze dali je matrica stupcana ili retcana
	 * Stupcana = false, Retcana = true
	 * @param matrix
	 * @param rowMatrix
	 */
	public VectorMatrixView(IMatrix matrix, boolean rowMatrix){
		this.matrix = matrix;
		this.rowMatrix = rowMatrix;
	}
	
	/**
	 * Metoda za dohvat elementa matrice, to jest, vektora
	 * @param dim
	 * @return
	 */
	public double get(int dim){
		if(rowMatrix == true){
			return matrix.get(1, dim-1);
		}else{
			return matrix.get(dim-1, 1);
		}
	}
	
	/**
	 * Metoda postavlja vrijednost matrice na zadanu, to jest vektora
	 * @param dim
	 * @param value
	 * @return
	 */
	public IVector set(int dim, double value){
		if(rowMatrix == true){
			matrix.set(0, dim-1, value);
			double[] el = new double[matrix.getColsCount()];
			for(int i=0; i<matrix.getColsCount(); i++){
				el[i] = matrix.get(0, i);
			}
			return new Vector(el);
		}else{
			matrix.set(dim-1, 0, value);
			double[] el = new double[matrix.getRowsCount()];
			for(int i=0; i<matrix.getRowsCount(); i++){
				el[i] = matrix.get(i, 0);
			}
			return new Vector(el);
		}
	}
	
	/**
	 * Metoda za dohvat dimenzije vektora, tocnije velicine matrice
	 * @return
	 */
	public int getDimension(){
		if(rowMatrix == true){
			return matrix.getColsCount();
		}else{
			return matrix.getRowsCount();
		}
	}
	
	/**
	 * Metoda za kopiranje vektora
	 * @return
	 */
	public IVector copy(){
		if(rowMatrix == true){
			double[] el = new double[matrix.getColsCount()];
			for(int i=0; i<matrix.getColsCount(); i++){
				el[i] = matrix.get(0, i);
			}
			return new Vector(el);
		}else{
			double[] el = new double[matrix.getRowsCount()];
			for(int i=0; i<matrix.getRowsCount(); i++){
				el[i] = matrix.get(i, 0);
			}
			return new Vector(el);
		}
	}
	
	/**
	 * Metoda za izradu novog vektora iste dimenzije
	 * @param dim
	 * @return
	 */
	public IVector newInstance(int dim){
		double[] el = new double[dim];
		for(int i=0; i<dim; i++){
			el[i] = 0;
		}
		return new Vector(el);
	}
	
}
