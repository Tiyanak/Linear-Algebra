package hr.fer.zemris.linearna;

import javax.naming.OperationNotSupportedException;


/**
 * Abstraktna klasa koja sadrzi osnovne operacije sa vektorima
 * @author Tiyanak
 *
 */
public abstract class AbstractVector implements IVector{

	/**
	 * Prazan konstruktor
	 */
	public AbstractVector(){
	}
	
	/**
	 * Abstraktne metode implementirane u klasi Vector koje predstavljaju osnovna svojstva vektora
	 * dohvat i upis vrijednosti dimenzija vektora, kopiranje vektora, izrada novog vektora
	 */
	public abstract double get(int number);
	public abstract IVector set(int numberOne, double numberTwo);
	public abstract int getDimension();
	public abstract IVector copy();
	public abstract IVector newInstance(int number);

	@Override
	public IVector copyPart(int n){
		if(this.getDimension() == n){
			return this.copy();
		}else if(this.getDimension() < n){
			double[] dime = new double[n];
			for(int i=0; i<this.getDimension(); i++){
				dime[i] = this.get(i);
			}
			for(int j=this.getDimension(); j<n; j++){
				dime[j] = 0;
			}
			return new Vector(dime);
		}else{
			double[] dime = new double[n];
			for(int i=0; i<n; i++){
				dime[i] = this.get(i);
			}
			return new Vector(dime);
		}
	}
	
	/**
	 * Metoda za zboj 2 vektora te zapis rezultata u prvi vektor
	 * @param IVector vector - vector kao drugi operand pri zbrajanju
	 */
	@Override
	public IVector add(IVector vector) throws OperationNotSupportedException{
		if(this.getDimension() != vector.getDimension()){
			throw new OperationNotSupportedException();
		}
		for(int i=this.getDimension()-1; i>=0; i--){
			this.set(i, get(i)+vector.get(i));
		}
		return this;
	}
	
	/**
	 * Metoda za zbroj 2 vektora te zapis rezultata u novi vektor
	 * @param IVector vector - vector kao drugi operand pri zbrajanju
	 */
	@Override
	public IVector nAdd(IVector vector) throws OperationNotSupportedException{
		return this.copy().add(vector);
	}
	
	/**
	 * Metoda za oduzimanje 2 vektora i zapis rezultata u prvi vektor
	 * @param IVector vector - vector kao drugi operand pri oduzimanju
	 */
	@Override
	public IVector sub(IVector vector) throws OperationNotSupportedException{
		if(this.getDimension() != vector.getDimension()){
			throw new OperationNotSupportedException();
		}
		for(int i=this.getDimension()-1; i>=0; i--){
			this.set(i, get(i)-vector.get(i));
		}
		return this;
	}
	
	/**
	 * Metoda za oduzimanje 2 vektora i zapis rezultata u novi vektor
	 * @param IVector vector - vector kao drugi operand pri oduzimanju
	 */
	@Override
	public IVector nSub(IVector vector) throws OperationNotSupportedException{
		return this.copy().sub(vector);
	}
	
	/**
	 * Metoda za mnozenje vektora brojem te zapis rezultata u taj isti vektor
	 * @param double number - broj kojim se vektor mnozi
	 */
	@Override
	public IVector scalarMultiply(double number){
		for(int i=this.getDimension()-1; i>=0; i--){
			this.set(i, this.get(i)*number);
		}
		return this;
	}
	
	/**
	 * Metoda za mnozenje vektora brojem te zapis rezultata u novi vektor
	 * @param double number - broj kojim se vektor mnozi
	 */
	@Override
	public IVector nScalarMultiply(double number){
		return this.copy().scalarMultiply(number);
	}
	
	/**
	 * Metoda raèuna duljinu vektora (double)
	 */
	@Override
	public double norm(){
		double len = 0;
		for(int i=this.getDimension()-1; i>=0; i--){
			len += (this.get(i) * this.get(i));
		}
		return Math.sqrt(len);
	}
	
	/**
	 * Metoda raèuna jedinièni vektor te vraca rezultat u taj isti vektor
	 */
	@Override
	public IVector normalize(){
		double norm = this.norm();
		for(int i=0; i<this.getDimension(); i++){
			double value = this.get(i);
			this.set(i, value/norm);
		}
		return this;
	}
	
	/**
	 * Metoda za izraèun jediniènog vektora te zapir rezultata u novi vektor
	 */
	@Override
	public IVector nNormalize(){
		return this.copy().normalize();
	}
	
	@Override
	public double cosine(IVector vector) throws OperationNotSupportedException{
		double scalarProd = this.scalarProduct(vector);
		double len1 = this.norm();
		double len2 = vector.norm();
		double cosTheta = scalarProd/(len1*len2);
		
		return cosTheta;
	}
	
	/**
	 * Metoda za mnozenje 2 vektora istih dimenzija te zapis rezultata u prvi vektor A = B * C
	 * @param IVector vector - vector kao drugi operand pri mnozenju
	 */
	@Override
	public double scalarProduct(IVector vector) throws OperationNotSupportedException{
		if(this.getDimension() != vector.getDimension()){
			throw new OperationNotSupportedException();
		}
		double scalarSum = 0;
		for(int i=this.getDimension()-1; i>=0; i--){
			scalarSum += (this.get(i) * vector.get(i));
		}
		return scalarSum;
	}
	
	/**
	 * Metoda za vektorski produkt 2 vektora te zapis u novi vektor A = B x C
	 * @param IVector vector - vector kao drugi operand pri vektorskom umnosku
	 */
	@Override
	public IVector nVectorProduct(IVector vector) throws OperationNotSupportedException{
		if(this.getDimension() != 3){
			throw new OperationNotSupportedException();
		}
		double[] dims = new double[3];
		
		dims[0] = this.get(1)*vector.get(2) - vector.get(1)*this.get(2);
		dims[1] = (-1)*this.get(0)*vector.get(2)+vector.get(0)*this.get(2);
		dims[2] = this.get(0)*vector.get(1)-vector.get(0)*this.get(1);
		
		IVector newVector = new Vector(dims);
		
		return newVector;
		
		
	}
	
	@Override
	public IVector nFromHomogeneus(){
		double[] dime = new double[this.getDimension()-1];
		double homo = this.get(this.getDimension()-1);
		for(int i=0; i<this.getDimension()-1; i++){
			dime[i] = this.get(i) / homo;
		}
		return new Vector(dime);
	}
	
	@Override
	public IMatrix toRowMatrix(boolean toRow){
		MatrixVectorView matrix = new MatrixVectorView(this, true);
		return matrix.copy();
	}
	
	@Override
	public IMatrix toColumnMatrix(boolean toColumn){
		MatrixVectorView matrix = new MatrixVectorView(this, false);
		return matrix.copy();
	}
	
	@Override
	public double[] toArray(){
		double[] dime = new double[this.getDimension()];
		for(int i=0; i<this.getDimension(); i++){
			dime[i] = this.get(i);
		}
		return dime;
	}
	
	/**
	 * Metoda vraca lijepi zapis vektora
	 * @return String
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		String[] dimensions = {"i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "v", "z"};
		
		sb.append("V = ");
		sb.append(this.get(0));
		sb.append(dimensions[0]);
		for(int i=1; i<this.getDimension(); i++){
			sb.append(" + ");
			sb.append(this.get(i));
			sb.append(dimensions[i]);
		}
				
		return sb.toString();
	}
	
	
}
