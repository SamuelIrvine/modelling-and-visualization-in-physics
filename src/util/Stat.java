package util;

import java.util.ArrayList;
import java.util.Random;

import shape.Pair;
import shape.Vector;

public class Stat extends Vector<Stat>{
	
	public static int nBootstrapSamples=100;
	
	private final double[] d;
	private final int l;
	
	public Stat(double[] data){
		d=data.clone();
		l=d.length;
	}
	
	public Stat(Double[] data){
		d=new double[data.length];
		l=d.length;
		for (int i=0;i<d.length;i++){
			d[i]=data[i];
		}
	}
	
	@Override
	public Stat mod(Stat a) {
		Stat mod=new Stat(d);
		for (int i=0;i<d.length;i++){
			mod.d[i]=((mod.d[i]%a.d[i])+a.d[i])%a.d[i];
		}
		return mod;
	}

	@Override
	public double dot(Stat a) {
		double dot=0.0;
		for (int i=0;i<d.length;i++){
			dot+=d[i]*=a.d[i];
		}
		return dot;
	}
	
	public double[] getData(){
		return d;
	}
	
	public Stat(ArrayList<Double> data){
		this(data.toArray(new Double[data.size()]));
	}
	
	public static void main(String[] args0){
		double[] d=new double[100000];
		for (int i=0;i<d.length;i++){
			d[i]=Math.random();
		}
		Stat stat=new Stat(d);
		System.out.println(stat.error(fMean()));
		System.out.println(stat.error(fStdev()));
	}
	
	public Stat abs(){
		double[] abs=new double[l];
		for (int i=0;i<l;i++){
			abs[i]=Math.abs(d[i]);
		}
		return new Stat(abs);
	}
	
	public Stat inverse(){
		double[] inv=new double[l];
		for (int i=0;i<l;i++){
			inv[i]=1.0/d[i];
		}
		return new Stat(inv);
	}
	
	@Override
	public Stat negate(){
		double[] neg=new double[l];
		for (int i=0;i<l;i++){
			neg[i]=-d[i];
		}
		return new Stat(neg);
	}
	
	@Override
	public Stat add(Stat a){
		double[] add=new double[l];
		if (a.l==1){
			for (int i=0;i<l;i++){
				add[i]=d[i]+a.d[0];
			}
		}else{
			for (int i=0;i<l;i++){
				add[i]=d[i]+a.d[i];
			}
		}
		return new Stat(add);
	}
	
	public Stat multiply(Stat a){
		double[] mult=new double[l];
		if (a.l==1){
			for (int i=0;i<l;i++){
				mult[i]=d[i]*a.d[0];
			}
		}else{
			for (int i=0;i<l;i++){
				mult[i]=d[i]*a.d[i];
			}
		}
		return new Stat(mult);
	}
	
	public Stat divide(Stat a){
		return multiply(a.inverse());
	}
	
	public double sum(){
		return fSum().evaluate(this);
	}
	
	public static StatFunction fSum(){
		return new StatFunction(){
			@Override
			public double evaluate(Stat s) {
				double sum=0d;
				for (double v: s.d){
					sum+=v;
				}
				return sum;
			}
		};
	}
	
	public double mean(){
		return fMean().evaluate(this);
	}
	
	public static StatFunction fMean(){
		return new StatFunction(){
			@Override
			public double evaluate(Stat s) {
				return s.sum()/s.l;
			}
		};
	}
	
	public double error(StatFunction function){
		return (l>100)?bootstrap(function):jackknife();
	}
	
	public double bootstrap(StatFunction function){ //Almost a functional....
		Stat[] samples=new Stat[nBootstrapSamples];
		for (int i=0;i<samples.length;i++){
			samples[i]=sample(l);
		}
		double[] errors=new double[samples.length];
			for (int i=0;i<errors.length;i++){
				errors[i]=(double)function.evaluate(samples[i]);
			}
		return Math.sqrt(new Stat(errors).variance());
	}
	
	private double jackknife(){
		//TODO
		return 0d;
	}
	
	public double variance(){
		return fVariance().evaluate(this);
	}
	
	public static StatFunction fVariance(){
		return new StatFunction(){
			@Override
			public double evaluate(Stat s) {
				return (s.multiply(s).mean())-Math.pow(s.mean(), 2);
			}
		};
	}
	
	public double stdev(){
		return Math.sqrt(variance()/(l-1));
	}
	
	public static StatFunction fStdev(){
		return new StatFunction(){
			@Override
			public double evaluate(Stat s) {
				return Math.sqrt(s.variance()/(s.l-1));
			}
		};
	}
	
	public Stat sample(int n){
		return sample(n, new Random());
	}
	
	public Stat sample(int n, Random r){
		double[] sample=new double[n];
		for (int i=0;i<n;i++){
			sample[i]=d[r.nextInt(l)];
		}
		return new Stat(sample);
	}
	
	public Pair<Stat, Stat> split(double weight){
		double[] a=new double[(int)(l*weight)];
		double[] b=new double[l-(int)(l*weight)];
		for (int i=0;i<a.length;i++){
			a[i]=d[i];
		}
		for (int i=a.length;i<l;i++){
			b[i-a.length]=d[i];
		}
		return new Pair<Stat, Stat>(new Stat(a), new Stat(b));
	}

	@Override
	public Stat copy() {
		return new Stat(d);
	}
}
