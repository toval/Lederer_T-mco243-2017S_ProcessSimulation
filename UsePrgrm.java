package asgn02;

import java.util.LinkedList;
import java.util.Queue;

public class UsePrgrm {

	public static void main(String[] args) {
		
		IRandomValueGenerator random = new RandomValueGenerator();
		IProcessor processor;
		
		final int QUANTUM = 5;
		int iterations = 3000;
	
		Queue<PCB>ready = new LinkedList<PCB>();
		Queue<PCB>blocked = new LinkedList<PCB>();
		
		for(int i =1;i<=10;i++){
		PCB pcb = new PCB(new SimProcess(i,("proc" + i),350,random));
		//set the registers
		
		pcb.setR1(random.getNextInt());
		pcb.setR2(random.getNextInt());
		pcb.setR3(random.getNextInt());
		pcb.setR4(random.getNextInt());
		
		//add it to the ready list
		ready.add(pcb);
		}
		//setup the processor
		processor = new SimProcessor(random,ready.peek().getProcess(),
				ready.peek().getCurrentInst());
		ProcessState state; 
		int counter = 0;
		
		for(int i =1;i<=iterations;i++){
		
			System.out.print("step " + i);
			state = processor.executeNextInstruction();
			counter++;
			
			if(state.equals(ProcessState.BLOCKED)){
				System.out.println("----Process Blocked----");
				
				updatePcbs(ready,processor);
			
				blocked.add(ready.poll());
		
				contextSwitch(ready,processor,blocked);
				//resetting counter
				counter = 0;
				wakeUp(blocked,random, ready);
			}
			else if(state.equals(ProcessState.FINISHED)){
				
				System.out.println("---Process Finished---");
				
				//process finished so taking it off the list
				ready.poll();
				contextSwitch(ready,processor,blocked);
				counter = 0;
				wakeUp(blocked, random,ready);
			}
			
			else if(counter == QUANTUM){
				System.out.println("---Quantum Expired---");
				updatePcbs(ready,processor);
				ready.add(ready.poll() );
				contextSwitch(ready,processor,blocked);
				counter = 0;
				wakeUp(blocked, random, ready);
			}
			}
	}//end main

	private static void wakeUp(Queue<PCB> blocked, IRandomValueGenerator random,Queue<PCB>ready){
		for(int i =0;i<blocked.size();i++){
			
			if(random.getTrueWithProb(.30)){
			blocked.add(blocked.poll());
		}
		else {
			//put it on ready list
			ready.add(blocked.poll());
			
		}
		}
	}

	private static void updatePcbs(Queue<PCB> ready,IProcessor processor) {
		
			ready.peek().setR1(processor.getRegisterValue(0));
			ready.peek().setR2(processor.getRegisterValue(1));
			ready.peek().setR3(processor.getRegisterValue(2));
			ready.peek().setR4(processor.getRegisterValue(3));
			ready.peek().setCurrentInst(processor.getCurrentInst());
			System.out.println("Context switch saving process " + 
			ready.peek().getProcess().getPid());
			
		
		//display what is now being saved from the processor to the pcb-process
		System.out.println("\tCurrent Instruction: " + ready.peek().getCurrentInst() + 
				" R1: " + ready.peek().getR1() + ", R2: " + ready.peek().getR2() + 
				", R3: " + ready.peek().getR3() + ", R4: " + ready.peek().getR4() + "\n");
		}
		

	private static void contextSwitch(Queue<PCB>ready,IProcessor processor,Queue<PCB>blocked){
		
		if(ready.isEmpty()&& blocked.isEmpty()){
			System.out.println("Finished executing all instructions of all processes!\n"
					+ "exiting application, have a nice day!");
			System.exit(0);
		}
		else{
			
		//now reset the registers on the processor
			processor.setRegisterValue(0, ready.peek().getR1());
			processor.setRegisterValue(1, ready.peek().getR2());
			processor.setRegisterValue(2, ready.peek().getR3());
			processor.setRegisterValue(3, ready.peek().getR4());
			processor.setCurrentInst(ready.peek().getCurrentInst());
			processor.setIprocess(ready.peek().getProcess());
			
			System.out.println("Context switch restoring process: " + 
			ready.peek().getProcess().getPid());
		}
		//displaying what is now on the processor
		System.out.println("\tcurrent instruction: " + processor.getCurrentInst()+ 
				" R1: " + ready.peek().getR1() + ", R2: " + 
				ready.peek().getR2() + ", R3: " + ready.peek().getR3() + 
				", R4: " + ready.peek().getR4() + "\n");
		//}
	}

}//end class
