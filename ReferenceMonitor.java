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
		subjectNameMap.put(subject.getName(), subject);
	}

	public void addObject (SecureObject object, SecurityLevel level){
		// SecureSubject subject = new SecureSubject(name);
		objectMap.put(object, level);
		objectNameMap.put(object.getName(), object);
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
		return subjectMap.get(getSubject(name));
	}

	public SecurityLevel getObjectLabel (String name){
		return objectMap.get(getObject(name));
	}

	public SecureSubject getSubject (String name) {
		return subjectNameMap.get(name);
	}

	public SecureObject getObject (String name) {
		return objectNameMap.get(name);
	}


	public void printState(){
		String objectL = "LObj";
		System.out.println( "The current system state is: ");
		
	}

	// subject write to object
	// check if legal write.  
	// If legal, pass to ObjectManager. Return value of the write.
	// otherwise return 0.
	public int rmWrite (Instruction newInstruction){
		SecureSubject subj = getSubject (newInstruction.getInstructionSubjName());
		SecurityLevel subjLabel = _rm.getSubjectLabel(subj);

		SecureObject obj = getObject (newInstruction.getInstructionObjName());
		SecurityLevel objLabel = _rm.getObjectLabel(obj);

		if (objLabel == subjLabel) {
			ObjectManager objMng = new ObjectManager(obj);
			return objMng.readObj();
		}
		
		return 0;
	
	}

	// subject read Object
	// check if legal read. 
	// If legal, pass to ObjectManager. Return value of the read.
	// otherwise return 0.
	public int rmRead (Instruction newInstruction){
		SecureSubject subj = getSubject (newInstruction.getInstructionSubjName());
		SecurityLevel subjLabel = _rm.getSubjectLabel(subj);

		SecureObject obj = getObject (newInstruction.getInstructionObjName());
		SecurityLevel objLabel = _rm.getObjectLabel(obj);

		if (objLabel == subjLabel) {
			ObjectManager objMng = new ObjectManager(obj);
			return objMng.writeObj( newInstruction.getInstructionValue());
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
