import java.util.*;

/*
Singleton design pattern used for ReferenceMonitor so only one instance
exists.
*/

public class ReferenceMonitor {
	private static ReferenceMonitor _rm;
	//maintain two mappings from subject/object names to security labels.
	//dictionary? //Dictionary obsolete.  Use Map.
	Map<SecureSubject, SecurityLevel> subjectMap = new HashMap<SecureSubject, SecurityLevel>();
	Map<String, SecureSubject> subjectNameMap = new HashMap<String, SecureSubject>();

	Map<SecureObject, SecurityLevel> objectMap = new HashMap<SecureObject, SecurityLevel>();
		Map<String, SecureObject> objectNameMap = new HashMap<String, SecureObject>();


	private ReferenceMonitor ()
		{}

	public static ReferenceMonitor getInstance () {
		if (_rm == null) {
			_rm = new ReferenceMonitor ();
		}
		return _rm;

	}

	public void addSubject (SecureSubject subject, SecurityLevel level){
		// SecureSubject subject = new SecureSubject(name);
		subjectMap.put(subject, level);
		subjectNameMap.put(subject.getName().toLowerCase(), subject);
	}

	public void addObject (SecureObject object, SecurityLevel level){
		// SecureSubject subject = new SecureSubject(name);
		objectMap.put(object, level);
		objectNameMap.put(object.getName().toLowerCase(), object);
	}

	public void createObject (String name, SecurityLevel level) {
		SecureObject newObject = new SecureObject(name);
		addObject(newObject, level);
	}

	public SecurityLevel getSubjectLabel (SecureSubject subj){
		return subjectMap.get(subj);
	}

	public SecurityLevel getObjectLabel (SecureObject obj){
		return objectMap.get(obj);
	}

	public SecurityLevel getSubjectLabel (String name){
		return subjectMap.get(getSubject(name.toLowerCase()));
	}

	public SecurityLevel getObjectLabel (String name){
		return objectMap.get(getObject(name.toLowerCase()));
	}

	public SecureSubject getSubject (String name) {
		return subjectNameMap.get(name.toLowerCase());
	}

	public SecureObject getObject (String name) {
		return objectNameMap.get(name.toLowerCase());
	}


	public void printState(){
		System.out.println( "The current system state is: ");
		System.out.println( "LObj has value: " + _rm.getObject("LObj").getValue());
		System.out.println("HObj has value: " + _rm.getObject("HObj").getValue()); 
		System.out.println("Lyle has recently read: " + _rm.getSubject("Lyle").getTemp());
		System.out.println("Hal has recently read: " + _rm.getSubject("Hal").getTemp());
		System.out.println("");
		
	}

	// subject write to object
	// check if legal write.  
	// If legal, pass to ObjectManager. Return value of the write.
	// otherwise return 0.
	public int executeWrite (Instruction newInstruction){
		String subjName = newInstruction.getInstructionSubjName();
		String objName = newInstruction.getInstructionObjName();

		SecureSubject subj = getSubject (subjName);
		SecurityLevel subjLabel = _rm.getSubjectLabel(subj);

		SecureObject obj = getObject (objName);
		SecurityLevel objLabel = _rm.getObjectLabel(obj);

		int value = newInstruction.getInstructionValue();

		System.out.println(subjName + " writes value " + value + " to " + objName);
		if (objLabel.compareTo(subjLabel) >= 0) {
			ObjectManager objMng = new ObjectManager(obj);
			// System.out.println(objMng.object.getName());
			return objMng.writeObj(value);
		}
		
		return 0;
	}

	// subject read Object
	// check if legal read. 
	// If legal, pass to ObjectManager. Return value of the read.
	// otherwise return 0.
	public int executeRead (Instruction newInstruction){
		String subjName = newInstruction.getInstructionSubjName();
		String objName = newInstruction.getInstructionObjName();

		SecureSubject subj = getSubject (subjName);
		SecurityLevel subjLabel = _rm.getSubjectLabel(subj);

		SecureObject obj = getObject (objName);
		SecurityLevel objLabel = _rm.getObjectLabel(obj);

		System.out.println(subjName + " reads " + objName);
		if (objLabel.compareTo(subjLabel) <= 0 ) {
			ObjectManager objMng = new ObjectManager(obj);
			// System.out.println(objMng.object.getName());

			int value = objMng.readObj();
			subj.updateTemp(value);
			return value;
		}
		else {
			subj.updateTemp(0);
		}
		
		return 0;		
	}

	private class ObjectManager{
		SecureObject object;

		private ObjectManager(SecureObject object){
			this.object = object;
		}

		private int readObj(){
			return this.object.value;
		}

		private int writeObj(int value){
			this.object.value = value;
			return value;
		}

	}

} // class ReferenceMonitor
