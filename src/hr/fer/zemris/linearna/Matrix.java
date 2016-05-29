package hr.fer.zemris.linearna;

/**
 * Klasa predstavlja jednu matricu te dozvoljava promjenu njenih vrijednosti
 * @author Tiyanak
 *
 */
public class Matrix extends AbstractMatrix{

	protected double[][] elements;
	protected int rows;
	protected int cols;
	
	/**
	 * Konstruktor radi praznu matricu sa rows redova i cols stupaca
	 * @param rows
	 * @param cols
	 */
	public Matrix(int rows, int cols){
		this.cols = cols;
		this.rows = rows;
		double[][] init = new double[rows][cols];
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				init[i][j] = 0;
			}
		}
		this.elements = init;
	}
	
	/**
	 * Konstruktor stvara novu matricu sa rows redaka i cols stupaca
	 * ovisno o zastavici fieldRead, za true postavlja referencu na elements, a za false stvara novo polje vrijednosti matrice
	 * @param rows - broj redaka
	 * @param cols - broj stupaca
	 * @param elements - elementi matrice
	 * @param fieldRead - zastavica za stvaranje novog polja elemenata
	 */
	public Matrix(int rows, int cols, double [][] elements, boolean fieldRead){
		this.rows = rows;
		this.cols = cols;
		if(fieldRead == true){
			this.elements = elements;
		}else{
			double[][] newElements = new double[rows][cols];
			for(int i=0; i<rows; i++){
				for(int j=0; j<cols; j++){
					newElements[i][j] = elements[i][j];
				}
			}
			this.elements = newElements;
		}
	}
	
	/**
	 * Metoda dobavlja broj redaka matrice
	 * @return int
	 */
	public int getRowsCount(){
		return this.rows;
	}
	
	/**
	 * Metoda dobavlja broj stupaca matrice
	 * @return int
	 */
	public int getColsCount(){
		return this.cols;
	}
	
	/**
	 * Metoda dobavlja zadani element matrice
	 * @param int row - redak matrice u kojem se trazi element
	 * @param int col - stupac matrice u kojem se trazi element
	 * @return double - vrijednost nadenog elementa
	 */
	public double get(int row, int col){
		return elements[row][col];
	}
	
	/**
	 * Metoda za postavljanje zadanog elemnta matrice na zadanu vrijednost
	 * @param int row, int col - redak i stupac matrice elementa cija se vrijednost mijenja
	 * @param double value - nova vrijednost koja se stavlja mjesto zadanog elementa
	 * @return IMatrix - vraca istu matricu sa izmijenjenim elementom
	 */
	public IMatrix set(int row, int col, double value){
		this.elements[row][col] = value;
		return this;
	}
	
	/**
	 * Metoda za kopiranje trenutne matrice
	 * @return IMatrix - vraca novu nezavisnu instancu iste matrice
	 */
	public IMatrix copy(){
		
		double[][] newMat = new double[this.getRowsCount()][this.getColsCount()];
		for(int i=0; i<this.getRowsCount(); i++){
			for(int j=0; j<this.getColsCount(); j++){
				newMat[i][j] = this.get(i, j);
			}
		}
		
		IMatrix newMatrix = new Matrix(this.getRowsCount(), this.getColsCount(), newMat, false);
		
		return newMatrix;
	}
	
	/**
	 * Metoda radi novu instancu iste ali prazne matrice
	 * @return IMatrix
	 */
	public IMatrix newInstance(int rows, int cols){
		IMatrix newMatrix = new Matrix(rows, cols);
		return newMatrix;
	}
	
	/**
	 * Metoda za parsiranje ulaznog String niza znakova u matricu
	 * Niz mora biti u obliku: "1 2 3 | 4 5 6 | 7 8 9" 
	 * @param String str - ulazni niz znakova
	 * @return IMatrix
	 */
	public static IMatrix parseSimple(String str){
		String[] rows = str.split("\\|");
		String[] cols = rows[0].split(" ");
		double[][] el = new double[rows.length][cols.length];
				
		for(int i=0; i<rows.length; i++){
			if(i > 0){
				if(rows[i].startsWith(" ")){
					rows[i] = rows[i].substring(1);
					
				}
			}
			String[] newCols = rows[i].split(" ");
			for(int j=0; j<newCols.length; j++){
				el[i][j] = Double.parseDouble(newCols[j]);
			}
		}
		
		IMatrix newMatrix = new Matrix(rows.length, cols.length, el, false);
		return newMatrix;
	}
	
}
