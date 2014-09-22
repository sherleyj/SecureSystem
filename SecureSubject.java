public class SecureSubject {
	String name;
	int temp;
	// SecurityLevel label;

	public SecureSubject (String name){
		this.name = name;
		this.temp = 0;
		// this.label = level;
	}

	public String getName() {
		return this.name;
	}

	public int read() {
		this.temp = 0;
		// call rm read. read = value returned
		return 0;
	}

	public int write() {
		return 0;
		// call rm write
	}
} // class SecureSubject