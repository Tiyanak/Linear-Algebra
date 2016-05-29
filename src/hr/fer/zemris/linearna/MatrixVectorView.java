package hr.fer.zemris.linearna;

import javax.naming.OperationNotSupportedException;

/**
 * Klasa koja predstavlja pogled vektora u obliku matrice
 * Promjene na pogledu se mjenjaju i na originalnom vektoru
 * @author Tiyanak
 *
 */
public class MatrixVectorView {

	private boolean asRowMatrix;
	private IVector vect;
	
	/**
	 * Konstruktor postavlja pogled vektora u obliku matrice
	 * @param vect
	 * @param asRowMatrix
	 */
	public MatrixVectorView(IVector vect, boolean asRowMatrix){
		this.asRowMatrix = asRowMatrix;
		this.vect = vect;
	}
	
	/**
	 * Metoda vraca broj redaka matrice, a on predstavlja broj dimenzija vektora
	 * @return
	 */
	public int getRowsCount(){
		if(asRowMatrix == true){
			return 1;
		}else{
			return vect.getDimension();
		}
	}
	
	/**
	 * Metoda vraca broj stupaca matrice
	 * @return
	 */
	public int getColsCount(){
		if(asRowMatrix == false){
			return 1;
		}else{
			return vect.getDimension();
		}
	}
	
	/**
	 * Metoda vraca element matrice u zadanaom retku i stupcu
	 * @param int row redak
	 * @param int col stupac
	 * @return double vrijednost elementa
	 * @throws OperationNotSupportedException
	 */
	public double get(int row, int col) throws OperationNotSupportedException{
		if(row != 1 && col != 1){
			throw new OperationNotSupportedException();
		}
		if(row > 1){
			return vect.get(row-1);
		}else if(col > 1){
			return vect.get(col-1);
		}else{
			return vect.get(0);
		}
	}
	
	/**
	 * Metoda postavlja vrijednost zadanog elementa matrice na zadanu vrijednost
	 * Istovremeno se mjenja i vrijednost u originalnom vektoru
	 * @param row
	 * @param col
	 * @param value
	 * @return
	 * @throws OperationNotSupportedException
	 */
	public IMatrix set(int row, int col, double value) throws OperationNotSupportedException{
		if(row != 1 && col != 1){
			throw new OperationNotSupportedException();
		}
		if(row > 0){
			vect.set(row, value);
			double[][] el = new double[vect.getDimension()][1];
			for(int i=0; i<row; i++){
				el[i][0] = vect.get(i);
			}
			el[row][0] = value;
			IMatrix matrix = new Matrix(this.getRowsCount(), 1, el, true);
			return matrix;
		}else if(col > 0){
			vect.set(row, value);
			double[][] el = new double[1][vect.getDimension()];
			for(int i=0; i<row; i++){
				el[0][i] = vect.get(i);
			}
			el[0][col] = value;
			IMatrix matrix = new Matrix(this.getRowsCount(), 1, el, true);
			return matrix;
		}else{
			vect.set(0, value);
			if(asRowMatrix == true){
				double[][] el = new double[1][vect.getDimension()];
				for(int i=0; i<col; i++){
					el[0][i] = vect.get(i);
				}
				el[0][col] = value;
				IMatrix matrix = new Matrix(1, col, el, false);
				return matrix;
			}else{
				double[][] el = new double[vect.getDimension()][1];
				for(int i=0; i<row; i++){
					el[i][0] = vect.get(i);
				}
				el[row][0] = value;
				IMatrix matrix = new Matrix(row, 1, el, false);
				return matrix;
			}
			
		}
	}
	
	/**
	 * Metoda vraca kopiju vektora u obliku matrice
	 * @return
	 */
	public IMatrix copy(){
		if(asRowMatrix == true){
			double[][] el = new double[1][this.getColsCount()];
			for(int i=0; i<this.getColsCount(); i++){
				el[0][i] = vect.get(i);
			}
			return new Matrix(1, this.getColsCount(), el, true);
		}else{
			double[][] el = new double[this.getRowsCount()][1];
			for(int i=0; i<this.getRowsCount(); i++){
				el[i][0] = vect.get(i);
			}
			return new Matrix(this.getRowsCount(), 1, el, true);
		}
	}
	
	/**
	 * Metoda radi novu matricu zadane velicine
	 * @param row
	 * @param col
	 * @return
	 */
	public IMatrix newInstance(int row, int col){
		return new Matrix(row, col);
	}
	
}
