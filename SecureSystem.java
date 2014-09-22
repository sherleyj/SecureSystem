import java.util.*;
import java.io.*;

public class SecureSystem {
	public static SecureSystem sys = new SecureSystem();
	static ReferenceMonitor rm = sys.getReferenceMonitor();

	public static void main (String[] args)  throws IOException {
		
		//SecureSystem sys = new SecureSystem();
		//ReferenceMonitor rm = sys.getReferenceMonitor();

		SecurityLevel low  = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;

		sys.createSubject("Lyle", low);
		sys.createSubject("Hal", high);

		// System.out.println("Lyle: " + rm.getSubjectLabel("Lyle"));
		// System.out.println("Hal: " + rm.getSubjectLabel("Hal"));

		rm.createObject("Lobj", low);
		rm.createObject("Hobj", high);

		// rm.printState();
		// System.out.println("Lobj: " + rm.getObjectLabel("Lobj"));
		// System.out.println("Hobj: " + rm.getObjectLabel("Hobj"));
		// System.out.println("Hobj: " + rm.getObject("Hobj").getValue());

		File inputFile = new File(args[0]);
		FileInputStream insStream = null;
		BufferedReader insBuffer = null;

		try {
			insStream = new FileInputStream (inputFile.getAbsolutePath());
			insBuffer = new BufferedReader (new InputStreamReader(insStream));

			// System.out.println("reading from file: " + inputFile.getName());
			
			String fileLine = insBuffer.readLine();
			while (fileLine != null) {
				Instruction newInstruction = Instruction.parseInstruction(fileLine);
				// System.out.println("fileline: " + fileLine);
				String objName = newInstruction.getInstructionObjName();
				String subjName = newInstruction.getInstructionSubjName();

				/* BAD INSTRUCTION */
				if (newInstruction.getInstructionCommand().equalsIgnoreCase("BadInstruction")) {
					System.out.print("Bad Instruction \n");
					System.out.print("");
					rm.printState();
				}

				/* WRITE command */
				// System.out.println("Instruction command: " + newInstruction.getInstructionCommand());
				 if (newInstruction.isWriteInstruction()){
				 	sys.getReferenceMonitor().executeWrite(newInstruction);
				 	rm.printState();
				 }

				 if (newInstruction.isReadInstruction()){
				 	sys.getReferenceMonitor().executeRead(newInstruction);
				 	rm.printState();
				 }

				fileLine = insBuffer.readLine();
			}

		}
		catch (Exception E) {
			System.out.println("Caught Exception");
			// inputFile.close();
		}

	}

	public void createSubject (String name, SecurityLevel level) {
		SecureSubject newSubject = new SecureSubject(name);
		rm.addSubject(newSubject, level);
	}
	// get instance for singleton ReferenceMonitor
	public ReferenceMonitor getReferenceMonitor() {
		return ReferenceMonitor.getInstance();
	}

}
















