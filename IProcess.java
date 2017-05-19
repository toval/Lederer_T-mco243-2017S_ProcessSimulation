package asgn02;

public interface IProcess {

	public abstract int getPid();
	public abstract String getProcName();
	public abstract ProcessState execute(int i);
}
