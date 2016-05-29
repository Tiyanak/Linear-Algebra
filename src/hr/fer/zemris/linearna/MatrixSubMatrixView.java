package hr.fer.zemris.linearna;

/**
 * Klasa koja predstavlja zivi pogled na originalnu matricu, vrijednosti koje se mjenjaju u pogledu, se mjenjaju i u originalnoj matrici
 * Pogled predstavlja jednu podmatricu, matricu sa maknutim zadanim stupcem i/ili retkom
 * @author Tiyanak
 *
 */
public class MatrixSubMatrixView {

	private int[] rowIndexes;
	private int[] colIndexes;
	private IMatrix matrix;
	
	/**
	 * Konstruktor prima originalnu matricu, redak i stupac koji iz nje mora maknuti
	 * Zadani redak i stupac zapravo ne brise iz originalne matrice
	 */
	public MatrixSubMatrixView(IMatrix matrix, int row, int col){
		this.matrix = matrix;
		int counter = 0;
		int i = 0;
		rowIndexes = new int[matrix.getRowsCount()-1];
		colIndexes = new int[matrix.getColsCount()-1];
		while(counter < matrix.getRowsCount()){
			if(counter != row){
				rowIndexes[i] = counter;
				i++;
				counter++;
			}else{
				counter++;
			}
		}
		i = 0;
		counter = 0;
		while(counter < matrix.getColsCount()){
			if(counter != col){
				colIndexes[i] = counter;
				i++;
				counter++;
			}else{
				counter++;
			}
		}
	}
	
	/**
	 * Konstruktor postavlja pogled na podmatricu, od zadane matrice, te sa zadanim retcima i stupcima
	 * @param matrix - originalna matrica
	 * @param rowIndexes - retci koji se prikazuju
	 * @param colIndexes - stupci koji se prikazuju
	 */
	private MatrixSubMatrixView(IMatrix matrix, int[] rowIndexes, int[] colIndexes){
		this.matrix = matrix;
		this.rowIndexes = rowIndexes;
		this.colIndexes = colIndexes;
	}
	
	/**
	 * Metoda vraca broj redaka podmatrice
	 * @return int
	 */
	public int getRowsCount(){
		return this.rowIndexes.length;
	}
	
	/**
	 * Metoda vraca broj stupaca podmatrice
	 * @return int
	 */
	public int getColsCount(){
		return this.colIndexes.length;
	}
	
	/**
	 * Metoda dobavlja element podmatrice sa zadanim retkom i stupcem
	 * @param int row - redak elementa
	 * @param int col - stupac elementa
	 * @return double - vrijednost elementa
	 */
	public double get(int row, int col){
		return matrix.get(rowIndexes[row], colIndexes[col]);
	}
	
	/**
	 * Metoda postavlja vrijednost u podmatrici, a ujedno i originalnoj matrici
	 * @param int row - redak elementa
	 * @param int col - stupac elementa 
	 * @param double value - nova vrijednost elementa
	 * @return IMatrix - originalnu matricu sa izmijenjenim elementom
	 */
	public IMatrix set(int row, int col, double value){
		matrix.set(rowIndexes[row], colIndexes[col], value);
		return matrix;
	}
	
	/**
	 * Metoda kopira pogled na originalnu matricu, to jest, vraca novu instancu matrica koja predstavlja podmatricu
	 * @return IMatrix
	 */
	public IMatrix copy(){
		double[][] el = new double[this.getRowsCount()][this.getColsCount()];
		for(int i=0; i<this.getRowsCount(); i++){
			for(int j=0; j<this.getColsCount(); j++){
				el[i][j] = matrix.get(this.rowIndexes[i], this.colIndexes[j]);
			}
		}
		int rows = rowIndexes.length;
		int cols = colIndexes.length;
		
		IMatrix newMatrix = new Matrix(rows, cols, el, true);
		return newMatrix;
	}
	
	/**
	 * Metoda radi novu instancu prazne matrice velicine kao podmatrica koju se gleda
	 * @param int row - redak
	 * @param int col - stupac
	 * @return IMatrix
	 */
	public IMatrix newInstance(int row, int col){
		double[][] el = new double[row][col];
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				el[i][j] = 0;
			}
		}
		
		IMatrix newMatrix = new Matrix(this.getRowsCount(), this.getColsCount(), el, true);
		return newMatrix;
	}
	
	/**
	 * Metoda vraca podmatricu koju se gleda, odnosno kopiju podmatrice, ili zivi pogled,ovisno o zastavici liveView jel je false, ili true
	 * @param row
	 * @param col
	 * @param liveView
	 * @return
	 */
	public IMatrix subMatrix(int row, int col, boolean liveView){
		if(liveView == false){
			return this.copy();
		}else{
			return this.matrix;
		}
	}
	
}
