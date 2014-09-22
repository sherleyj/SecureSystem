public class Instruction {

    // An instruction can have one of the following forms:
    //    READ subj obj 
    //    WRITE subj obj value
    // Anything else is considered to be a bad instruction 
    // and is rejected. 

    // LOCAL FIELDS:

    String command, subjName, objName;
    int value;


    // CONSTRUCTORS AND CONSTANTS:

    public Instruction (String com, String subj, String obj) {
	command = com;
	subjName = subj.toLowerCase();
	objName = obj.toLowerCase();
    } // Instruction

    public Instruction (String com, String subj, String obj, int val) {
	command = com;
	subjName = subj.toLowerCase();
	objName = obj.toLowerCase();
	value = val;
    } // Instruction

    static final Instruction BAD_INSTRUCTION
	// This is used as the representation of any illegal instruction in 
	// the instruction stream.
	= new Instruction("BadInstruction", "NoSubject", "NoObject");


    // ACCESSORS:

    public boolean isReadInstruction () {
	return (command.equalsIgnoreCase("Read"));
    } // isReadInstruction

    public boolean isWriteInstruction () {
	return (command.equalsIgnoreCase("Write"));
    } // isReadInstruction

    public String getInstructionCommand () {
	return command;
    } // getInstructionCommand

    public String getInstructionObjName () {
	return objName.toLowerCase();
    } // getInstructionObjName

    public String getInstructionSubjName () {
	return subjName.toLowerCase();
    } // getInstructionSubjName

    public int getInstructionValue () {
	// This will only be valid for a WRITE
	return value;
    } // getInstructionValue


    // OTHER FUNCTIONS:

    public static Instruction parseInstruction (String line) {
        String delims = "[ ]+";
        String[] tokens = line.split(delims);
        Instruction newInstrustion;

        if ( (tokens[0].equalsIgnoreCase("Write") && tokens.length != 4) ||
             (tokens[0].equalsIgnoreCase("Read") && tokens.length != 3) ||
             (!tokens[0].equalsIgnoreCase("Read") && !tokens[0].equalsIgnoreCase("Write")) ) {
            return BAD_INSTRUCTION;
        }

        if (tokens[0].equalsIgnoreCase("Write")) {
            newInstrustion = new Instruction(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
            return newInstrustion;
        }

        if (tokens[0].equalsIgnoreCase("Read")) {
            newInstrustion = new Instruction(tokens[0], tokens[1], tokens[2]);
            return newInstrustion;
        }

       return BAD_INSTRUCTION; 
    } // parseInstruction
	

    public void printInstruction () {
        
    } // printInstruction

} // class Instruction

