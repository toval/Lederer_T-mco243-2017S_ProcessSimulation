package asgn02;

public class PCB {

	private IProcess process;
	private int currentInst = 1;
	public PCB(IProcess process){
	
		this.process = process;
	}
	public int getCurrentInst(){
		return this.currentInst;
	}
	public void setCurrentInst(int current){
		this.currentInst = current;
	}
	public IProcess getProcess(){

		return process;
	}

	private int r1;
	private int r2;
	private int r3;
	private int r4;
	

	
	public int getR1() {
		return r1;
	}
	public void setR1(int val) {
		this.r1 = val;
	}
	public int getR2() {
		return r2;
	}
	public void setR2(int val) {
		this.r2 = val;
	}
	public int getR3() {
		return r3;
	}
	public void setR3(int val) {
		this.r3 = val;
	}
	public int getR4() {
		return r4;
	}
	public void setR4(int val) {
		this.r4 = val;
	}
	
	

}
