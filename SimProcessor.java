package asgn02;

public class SimProcessor implements IProcessor {

	private IRandomValueGenerator randomGenerator;
	private IProcess process;
	private int[]registers;
	private int currentInst;
	public SimProcessor(IRandomValueGenerator randomGenerator,
			IProcess process,int currentInst){
		this.randomGenerator = randomGenerator;
		this.process = process;
		this.registers = new int[4];
		this.currentInst = currentInst;
	}
	@Override
	public ProcessState executeNextInstruction() {
		ProcessState state = process.execute(currentInst);
		//increment current instruction
		currentInst++;
		return state;
	}

	@Override
	public void setRegisterValue(int i, int val) {
		registers[i] = val;
	}

	@Override
	public int getRegisterValue(int i) {
		return randomGenerator.getNextInt();
	}
	@Override
	public IProcess getIprocess() {
		return this.process;
	}
	@Override
	public void setIprocess(IProcess proc) {
		this.process = proc;
	}
	@Override
	public void setCurrentInst(int val) {
		this.currentInst = val;
	}
	@Override
	public int getCurrentInst() {
		return this.currentInst;
	}

}
