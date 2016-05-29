package hr.fer.zemris.linearna;

/**
 * Klasa predstavlja zivi pogled na originalnu matricu, ali transponiranu
 * @author Tiyanak
 *
 */
public class MatrixTransponseView extends AbstractMatrix{
	
	private IMatrix matrix;
	
	/**
	 * Konstruktor potsavlja pogled na originalnu matricu
	 * @param matrix
	 */
	public MatrixTransponseView(IMatrix matrix){
		this.matrix = matrix;
	}
	
	/**
	 * Metoda vraca broj redaka transponirane matrice
	 */
	public int getRowsCount(){
		return matrix.getColsCount();
	}
	
	/**
	 * Metoda vraca broj stupaca originalne matrice
	 */
	public int getColsCount(){
		return matrix.getRowsCount();
	}
	
	/**
	 * Metoda vraca element transponirane matrice
	 */
	public double get(int row, int col){
		return matrix.get(col, row);
	}
	
	/**
	 * Metoda postavlja element zadanog retka i stupca na novu vrijednost, dakako vrijednost se zapisuje u originalnu matricu
	 */
	public IMatrix set(int row, int col, double value){
		matrix.set(col, row, value);
		return matrix;
	}
	
	/**
	 * Metoda za kopiranje transponirane matrice
	 */
	public IMatrix copy(){
		double[][] el = new double[this.getRowsCount()][this.getColsCount()];
		for(int i=0; i<this.getRowsCount(); i++){
			for(int j=0; j<this.getColsCount(); j++){
				el[i][j] = matrix.get(j, i);
			}
		}
		
		IMatrix newMatrix = new Matrix(this.getRowsCount(), this.getColsCount(), el, true);
		return newMatrix;
	}
	
	/**
	 * Metoda radi nov instancu prazne transponirane matrice
	 */
	public IMatrix newInstance(int row, int col){
		double[][] el = new double[this.getRowsCount()][this.getColsCount()];
		for(int i=0; i<this.getRowsCount(); i++){
			for(int j=0; j<this.getColsCount(); j++){
				el[i][j] = 0;
			}
		}
		
		IMatrix newMatrix = new Matrix(this.getRowsCount(), this.getColsCount(), el, true);
		return newMatrix;
	}
	
	/**
	 * Metoda stavlja vrijednostu transponirane matrice u polje double-ova
	 */
	public double[][] toArray(){
		double[][] el = new double[matrix.getColsCount()][matrix.getRowsCount()];
		
		for(int i=0; i<matrix.getRowsCount(); i++){
			for(int j=0; j<matrix.getColsCount(); j++){
				el[j][i] = matrix.get(i, j);
			}
		}
		
		return el;
	}

}
