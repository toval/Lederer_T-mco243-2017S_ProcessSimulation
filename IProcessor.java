package asgn02;

public interface IProcessor {

	public abstract IProcess getIprocess();
	public abstract void setIprocess(IProcess proc);
	public abstract int getCurrentInst();
	public abstract void setCurrentInst(int i);
	public abstract ProcessState executeNextInstruction();
	public abstract void setRegisterValue(int i,int val);
	public abstract int getRegisterValue(int i);
}
