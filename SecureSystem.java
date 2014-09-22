import java.util.*;
import java.io.*;

public class SecureSystem {
//	public static SecureSystem sys = new SecureSystem();
	
	public static void main (String[] args)  throws IOException {
		
		SecureSystem sys = new SecureSystem();
		ReferenceMonitor rm = sys.getReferenceMonitor();

		SecurityLevel low  = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;

		sys.createSubject("Lyle", low);
		sys.createSubject("Hal", high);

		System.out.println("Lyle: " + rm.getSubjectLabel("Lyle"));
		System.out.println("Hal: " + rm.getSubjectLabel("Hal"));

		sys.getReferenceMonitor().createObject("Lobj", low);
		sys.getReferenceMonitor().createObject("Hobj", high);

		rm.printState();
		System.out.println("Lobj: " + rm.getObjectLabel("Lobj"));
		System.out.println("Hobj: " + rm.getObjectLabel("Hobj"));


		File inputFile = new File(args[0]);
		FileInputStream insStream = null;
		BufferedReader insBuffer = null;
		try {
			insStream = new FileInputStream(inputFile.getAbsolutePath());
			insBuffer = new BufferedReader (new InputStreamReader(insStream));

			System.out.println("reading from file: " + inputFile.getName());
			
			String fileLine = insBuffer.readLine();
			while (fileLine != null){
				Instruction newInstruction = Instruction.parseInstruction(fileLine);
				System.out.println("fileline: " + fileLine);
				// System.out.println("Instruction command: " + newInstruction.getInstructionCommand());
				// if (newInstruction.isWriteInstruction ()){
				// 	sys.getReferenceMonitor().rmWrite(newInstruction);
				// 	System.out.println(sys.getReferenceMonitor().getObject(newInstruction.getInstructionObjName()).getValue() );
				// }


				fileLine = insBuffer.readLine();
			}

		}
		catch (Exception E) {
			System.out.println("trouble creating input stream");
			// inputFile.close();
		}
		
		// String fileName = inputFile.getName();
		// System.out.println(fileName);

		// String filePath = inputFile.getAbsolutePath();
		// System.out.println(filePath);

	}

	public void createSubject (String name, SecurityLevel level) {
		SecureSubject newSubject = new SecureSubject(name);
		ReferenceMonitor.getInstance().addSubject(newSubject, level);
	}
	// get instance for singleton ReferenceMonitor
	public ReferenceMonitor getReferenceMonitor() {
		return ReferenceMonitor.getInstance();
	}

}
















