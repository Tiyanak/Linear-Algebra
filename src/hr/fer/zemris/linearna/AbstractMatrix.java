package hr.fer.zemris.linearna;

import java.text.DecimalFormat;

import javax.naming.OperationNotSupportedException;

/**
 * Abstraktna klasa koja sadrzi osnovne operacije sa matricama
 * @author Tiyanak
 *
 */

public abstract class AbstractMatrix implements IMatrix{

	/**
	 * prazan kostruktor
	 */
	public AbstractMatrix(){
	}
	
	/**
	 * abstraktne metode implementirane u klasi Matrix, sadrže osnovna svojstva matrice
	 */
	public abstract int getRowsCount();
	public abstract int getColsCount();
	public abstract double get(int row, int col);
	public abstract IMatrix set(int row, int col, double value);
	public abstract IMatrix copy();
	public abstract IMatrix newInstance(int row, int col);
	
	/**
	 * Metoda za mnozenje matrice brojem A = k * [B]
	 * @param double broj kojim se mnozi svaki clan matrice
	 */
	@Override
	public IMatrix scalarMultiply(double value){
		for(int i=0; i<this.getRowsCount(); i++){
			for(int j=0; j<this.getColsCount(); j++){
				this.set(i, j, value*this.get(i, j));
			}
		}
		return this;
	}
	
	/**
	 * Metoda za racunanje umnoska matrice i broja A = k * [B]
	 * @param double broj kojim se mnozi
	 */
	@Override
	public IMatrix nScalarMultiply(double value){
		return this.copy().scalarMultiply(value);
	}
	
	/**
	 * Metoda vraca novu instancu transponirane matrice
	 * @param boolean transpose
	 */
	@Override
	public IMatrix nTranspose(boolean transpose){
		MatrixTransponseView tranMat = new MatrixTransponseView(this);
		return tranMat.copy();
	}
	
	/**
	 * Metoda za zbrajanje 2 matrice te zapis rezultata u prvu matricu
	 * @param IMatrix matrix - prima drugi operand u zbrajanju (matricu)
	 */
	@Override
	public IMatrix add(IMatrix matrix) throws OperationNotSupportedException {
		try {
			if(this.getRowsCount() != matrix.getRowsCount() || this.getColsCount() != matrix.getColsCount()){
				throw new OperationNotSupportedException();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<this.getRowsCount(); i++){
			for(int j=0; j<this.getColsCount(); j++){
				double sum = this.get(i, j) + matrix.get(i, j);
				this.set(i, j, sum);
			}
		}
		return this;
	}
	
	/**
	 * Metoda za zbrajanje dviju matrica te vraca rezultat kao novu matricu
	 * @param IMatrix matrix - drugi operand u zbrajanju prima matricu
	 */
	@Override
	public IMatrix nAdd(IMatrix matrix) throws OperationNotSupportedException{
		return this.copy().add(matrix);
	}
	
	/**
	 * Metoda za oduzimanje dviju matrica te rezultat se zapisuje u prvu matricu
	 * @param IMatrix matrix - drugi parametar je matrica kao drugi operand sa kojim se oduzima 
	 */
	@Override
	public IMatrix sub(IMatrix matrix) throws OperationNotSupportedException {
		try {
			if(this.getRowsCount() != matrix.getRowsCount() || this.getColsCount() != matrix.getColsCount()){
				throw new OperationNotSupportedException();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<this.getRowsCount(); i++){
			for(int j=0; j<this.getColsCount(); j++){
				double sub = this.get(i, j) - matrix.get(i, j);
				this.set(i, j, sub);
			}
		}
		return this;
	}
	
	/**
	 * Metoda za oduzimanje 2 matrice te zapis rezultata u novu matricu
	 * @param IMatrix matrix - prima matricu kao drugi operand sa kojim se oduzima
	 */
	public IMatrix nSub(IMatrix matrix) throws OperationNotSupportedException{
		return this.copy().sub(matrix);
	}
	
	/**
	 * Metoda za mnozenje 2 matrica te zapis rezultata u novu matricu
	 * @param IMatrix matrix - matrica kao mnozitelj
	 */
	@Override
	public IMatrix nMultiply(IMatrix matrix) throws OperationNotSupportedException{
		if(this.getColsCount() != matrix.getRowsCount()){
			throw new OperationNotSupportedException();
		}
		double[][] mulMat = new double[this.getRowsCount()][matrix.getColsCount()];
		
		for(int i=0; i<this.getRowsCount(); i++){
			for(int j=0; j<matrix.getColsCount(); j++){
				double sum = 0;
				for(int l=0; l<this.getColsCount(); l++){
					sum += this.get(i, l) * matrix.get(l, j);
				}
				mulMat[i][j] = sum;
			}
		}
		IMatrix mulMatrix = new Matrix(this.getRowsCount(), matrix.getColsCount(), mulMat, true);
		return mulMatrix;
	}
	
	/**
	 * Metoda za racunanje determinante matrice
	 */
	@Override
	public double determinant() throws OperationNotSupportedException{
		if(this.getRowsCount() != this.getColsCount()){
			throw new OperationNotSupportedException();
		}
		double[][] mat = new double[this.getRowsCount()][this.getColsCount()];
		mat = this.toArray();
		double det = this.detCalc(mat, this.getRowsCount());
		
		return det;
		
	}
	
	/**
	 * Pomocna metoda za racun determinante matrice
	 * @param A
	 * @param N
	 * @return
	 */
	public double detCalc(double[][] A, int N) {
		double det = 0;
		if (N == 1) {
			det = A[0][0];
		} else if (N == 2) {
			det = A[0][0] * A[1][1] - A[1][0] * A[0][1];
		} else {
			det = 0;
			for (int j1 = 0; j1 < N; j1++) {
				double[][] m = new double[N - 1][];
				for (int k = 0; k < (N - 1); k++) {
					m[k] = new double[N - 1];
				}
				for (int i = 1; i < N; i++) {
					int j2 = 0;
					for (int j = 0; j < N; j++) {
						if (j == j1)
							continue;
						m[i - 1][j2] = A[i][j];
						j2++;
					}
				}
				det += Math.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1] * detCalc(m, N - 1);
			}
		}
		return det;
	}
	
	/**
	 * Metoda vraca podmatricu(novu instancu) od trenutne matrice 
	 * od koje se makne 1 zadani redak i 1 zadani stupac
	 * @param int row, int col, boolean subM - redak i stupac koji se makivaju iz prave matrice
	 */
	@Override
	public IMatrix subMatrix(int row, int col, boolean subM){
		MatrixSubMatrixView matrix = new MatrixSubMatrixView(this, row, col);
		return matrix.copy();
	}
	
	/**
	 * Metoda za racun inverza matrice preko determinanti
	 */
	@Override
	public IMatrix nInvert() throws OperationNotSupportedException{
		double determinant = this.copy().determinant();
		if(determinant == 0){
			throw new OperationNotSupportedException();
		}
		double inverzDeterminant = 1 / determinant;
		IMatrix mat = new Matrix(this.getRowsCount(), this.getColsCount());
		if(this.getRowsCount() == 2){
			double A00 = this.get(1, 1);
			double A01 = (-1) * this.get(1, 0);
			double A10 = (-1) * this.get(0, 1);
			double A11 = this.get(0, 0);
			mat.set(0, 0, A00);
			mat.set(1, 0, A10);
			mat.set(0, 1, A01);
			mat.set(1, 1, A11);
			return mat.copy().nTranspose(true).nScalarMultiply(inverzDeterminant);
		}else{
			IMatrix matrix = this.copy();
			for(int i=0; i<mat.getRowsCount(); i++){
				for(int j=0; j<mat.getColsCount(); j++){
					int exp = (i+1) + (j+1);
					double minus = Math.pow((-1), exp);
					MatrixSubMatrixView detMat = new MatrixSubMatrixView(matrix, i, j);
					double det = detMat.copy().determinant();
					mat.set(i, j, minus*det);
				}
			}	
			return mat.copy().nTranspose(true).nScalarMultiply(inverzDeterminant);
		}
	}
	
	/**
	 * Metoda vraca vrijednosti matrice u polju decimalnih vrijednosti
	 */
	@Override
	public double[][] toArray(){
		double[][] matrix = new double[this.getRowsCount()][this.getColsCount()];
		for(int i=0; i<this.getRowsCount(); i++){
			for(int j=0; j<this.getColsCount(); j++){
				matrix[i][j] = this.get(i, j);
			}
		}
		return matrix;
	}
	
	/**
	 * Metoda vraca String lijepi ispis matrice
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		double[][] matrix = new double[this.getRowsCount()][this.getColsCount()];
		matrix = this.toArray();
		
		for(int i=0; i<this.getRowsCount(); i++){
			sb.append("[");
			for(int j=0; j<this.getColsCount(); j++){
				sb.append(matrix[i][j]);
				sb.append(" ");
			}
			sb.append("]\n");
		}
		return sb.toString();
	}
	
	/**
	 * Vraca String lijepi ispis  matrice zaokruzenih vrijednosti na n decimala
	 * @param int number - broj decimala na koji se vrijednost 
	 * @return String
	 */
	public String toString(int number){
		StringBuilder sb = new StringBuilder();
		double[][] matrix = new double[this.getRowsCount()][this.getColsCount()];
		matrix = this.toArray();
		String pattern = "#.";
		for(int k=0; k<number; k++){
			pattern = pattern + "#";
		}
		DecimalFormat format = new DecimalFormat(pattern);
		
		for(int i=0; i<this.getRowsCount(); i++){
			sb.append("[");
			for(int j=0; j<this.getColsCount(); j++){
				sb.append(format.format(matrix[i][j]));
				sb.append(" ");
			}
			sb.append("]\n");
		}
		return sb.toString();
	}
	
	/**
	 * Metoda matricu vraca kao vektor
	 */
	public IVector toVector(boolean vectRow) throws OperationNotSupportedException{
		if(vectRow == true && this.getRowsCount() != 1){
			throw new OperationNotSupportedException();
		}
		if(vectRow == false && this.getColsCount() != 1){
			throw new OperationNotSupportedException();
		}

		if(vectRow == true){
			double[] el = new double[this.getColsCount()];
			for(int i=0; i<this.getColsCount(); i++){
				el[i] = this.get(1, i);
			}
			return new Vector(el);
		}else{
			double[] el = new double[this.getRowsCount()];
			for(int i=0; i<this.getRowsCount(); i++){
				el[i] = this.get(i, 1);
			}
			return new Vector(el);
		}
	}
	
}
