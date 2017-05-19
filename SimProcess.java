package asgn02;

public class SimProcess implements IProcess {
//simulates a process
	private int pID;
	private String procName;
	private int totalInst;
	private IRandomValueGenerator randomGen;
	
	public SimProcess(int pID,String procName,
			int totalInst,IRandomValueGenerator randomGen){
		
	this.pID = pID;
	this.procName = procName;
	this.totalInst = totalInst;
	//interface
	this.randomGen = randomGen;
	}
	
	@Override
	public int getPid() {
		return pID;
	}

	@Override
	public String getProcName() {
	return procName;
	}
	@Override
	public ProcessState execute(int i) {
		System.out.println("  process id:" + pID + "   process : " 
				+ procName + "   executing instruction: " + i);
		if(i >= totalInst)return ProcessState.FINISHED;
		else{
			if(randomGen.getTrueWithProb(.15))return ProcessState.BLOCKED;
			else return ProcessState.READY;
		}
		
	}

}
