package hr.fer.zemris.linearna;

/**
 * Klasa predstavlja jedan vektor, i njegova svojstva te operacije za kopiranje i dohvat podataka 
 * @author Tiyanak
 *
 */
public class Vector extends AbstractVector{

	private double[] elements;
	private int dimension;
	private boolean readOnly; 

	/**
	 * Prazan konstruktor
	 */
	public Vector(){
	}
	
	/**
	 * Konstruktor radi novi vektor sa zadanim elementima
	 * @param elements
	 */
	public Vector(double[] elements){
		this.elements = elements;
		this.dimension = elements.length;
		this.readOnly = false;
	}
	
	/**
	 * Konstruktor radi novi vektor sa zadanim elementima, ovisno o zastavici readOnly, moze bit promjenjiv i nepromjenjiv
	 * @param readOnly
	 * @param takeField
	 * @param elements
	 */
	public Vector(boolean readOnly, boolean takeField, double[] elements){
		this.readOnly = readOnly;
		if(takeField == true){
			this.elements = elements;
		}else{
			double[] newElements = elements;
			this.elements = newElements;
		}
		this.dimension = elements.length;
	}

	/**
	 * Metoda dohvaca zadani element vektora
	 * @param int dimension
	 * @return double vrijednost
	 */
	public double get(int dimension){
		return elements[dimension];
	}
	
	/**
	 * Metoda postavlja element vektora na zadanu vrijednost
	 * @param int dimension - koji element
	 * @param double value - koja vrijednost
	 */
	public IVector set(int dimension, double value){
		if(this.readOnly == true){
			return this;
		}else{
			this.elements[dimension] = value;
			return this;
		}
	}
	
	/**
	 * Metoda dohvaca dimenziju vektora
	 */
	public int getDimension(){
		return this.dimension;
	}
	
	/**
	 * Metoda za kopiranje vektora
	 */
	public IVector copy(){
		
		
		double[] newEl = new double[this.dimension];
		for(int i=0; i<this.dimension; i++){
			newEl[i] = this.elements[i];
		}
		int newDim = this.dimension;
		boolean newRead = this.readOnly;
		
		IVector newVector = new Vector(newEl);
		
		return newVector;
	}
	
	/**
	 * Metoda radi novu instancu istog praznog vektora
	 */
	public IVector newInstance(int dimension){
		double[] dime = new double[dimension];
		for(int i=0; i<dimension; i++){
			dime[i] = 1;
		}
		return new Vector(dime);
	}
	
	/**
	 * Metoda parsia String ulazni niz i od njega radi jedan vektor
	 * Pravilni oblik ulaznog niza: "1 1 1"
	 * @param parse
	 * @return
	 */
	public static Vector parseSimple(String parse){
		String[] values = parse.split(" ");
		double dime[] = new double[values.length];
		int i = 0;
		for(String s: values){
			dime[i] = Double.parseDouble(s);
			i++;
		}
		
		return new Vector(dime);
	}
	
}
