UTEID: sj8346;
FIRSTNAME: Sherley;
LASTNAME: Jones;
CSACCOUNT: sj8346;
EMAIL: sherleyjones@utexas.edu; 

There are 6 java files: SecureSystem.java, SecureObject.java, SecureSubject.java, 
SecurityLevel.java, ReferenceMonitor.java, and Instruction.java.  

In SecureObject.java I have the Secure Object class. getValue is public so that 
I can print it out. Otherwise I would just make it private.

In SecureSubject.java I have the SecureSubject class.

In Instruction.java I have the Instruction class. SecureSystem calls parseInstruction() which 
parses a line from input and made into Instruction objects.  SecureSystem then passes this 
instruction to the ReferenceMonitor which executes the instruction command 
(passes it to ObjectManager) if it follows BLP.

In ReferenceMonitor.java I have defined the ReferenceMonitor class.  I made this class follow
a the singleton design pattern so there is only one instance of the class ReferenceMonitor. 
In this class I keep the mapping from Security subjects/objects to their security labels.
I also keep a mapping from the security objects/subjects names to the actual object.  

In SecurityLevel.java I define the SecurityLevel class with a defined "dominates" relation assuming
that levels are linearly ordered. I used enum to accomplish this.

In SecureSystem.java is defined the SecureSystem app.  



To complie my program, you need to use "javac *.java". To run my program, 
you need to use "java SecureSystem instructionList"

I finished all of the assignment.  

Bad Instruction 
The current system state is: 
LObj has value: 0
HObj has value: 0
Lyle has recently read: 0
Hal has recently read: 0

Bad Instruction 
The current system state is: 
LObj has value: 0
HObj has value: 0
Lyle has recently read: 0
Hal has recently read: 0

lyle writes value 10 to lobj
The current system state is: 
LObj has value: 10
HObj has value: 0
Lyle has recently read: 0
Hal has recently read: 0

hal reads lobj
The current system state is: 
LObj has value: 10
HObj has value: 0
Lyle has recently read: 0
Hal has recently read: 10

lyle writes value 20 to hobj
The current system state is: 
LObj has value: 10
HObj has value: 20
Lyle has recently read: 0
Hal has recently read: 10

hal writes value 200 to lobj
The current system state is: 
LObj has value: 10
HObj has value: 20
Lyle has recently read: 0
Hal has recently read: 10

hal reads hobj
The current system state is: 
LObj has value: 10
HObj has value: 20
Lyle has recently read: 0
Hal has recently read: 20

lyle reads lobj
The current system state is: 
LObj has value: 10
HObj has value: 20
Lyle has recently read: 10
Hal has recently read: 20

lyle reads hobj
The current system state is: 
LObj has value: 10
HObj has value: 20
Lyle has recently read: 0
Hal has recently read: 20

Bad Instruction 
The current system state is: 
LObj has value: 10
HObj has value: 20
Lyle has recently read: 0
Hal has recently read: 20

Bad Instruction 
The current system state is: 
LObj has value: 10
HObj has value: 20
Lyle has recently read: 0
Hal has recently read: 20

Bad Instruction 
The current system state is: 
LObj has value: 10
HObj has value: 20
Lyle has recently read: 0
Hal has recently read: 20