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

	public int getTemp(){
		return this.temp;
	}

	public void updateTemp(int value) {
		this.temp = value;
		// call rm read. read = value returned
	}

} // class SecureSubject