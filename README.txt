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

In ReferenceMonitor.java I have defined the ReferenceMonitor class.  I made this class follow the singleton design pattern so there is only one instance of the class ReferenceMonitor. 
In this class I keep the mapping from Security subjects/objects to their security labels.
I also keep a mapping from the security objects/subjects names to the actual object.  

In SecurityLevel.java I define the SecurityLevel class with a defined "dominates" relation assuming
that levels are linearly ordered. I used enum to accomplish this.

In SecureSystem.java is defined the SecureSystem app.  

To compile my program, you need to use "javac *.java". To run my program, 
you need to use "java SecureSystem instructionList"  I made each test its own file.
So to run each test use "java SecureSystem Test2", "java SecureSystem Test3", "java SecureSystem Test4"

I finished all of the assignment.  

[Test Cases]
[Input of Test1]
write Hal HObj 
read Hal 
write Lyle LObj 10
read Hal LObj 
write Lyle HObj 20
write Hal LObj 200
read Hal HObj
read Lyle LObj
read Lyle HObj
foo Lyle LObj
Hi Lyle,This is Hal
The missile launch code is 1234567


[Ouput of Test 1]
reading from file: instructionList

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


[Input of Test2]
write Lyle HObj 40
write Lyle LObj 50
read Hal HObj
read Lyle LObj
write Lyle HObj 20
read Lyle HObj


[Output of Test2]
reading from file: Test2

lyle writes value 40 to hobj
The current system state is: 
LObj has value: 0
HObj has value: 40
Lyle has recently read: 0
Hal has recently read: 0

lyle writes value 50 to lobj
The current system state is: 
LObj has value: 50
HObj has value: 40
Lyle has recently read: 0
Hal has recently read: 0

hal reads hobj
The current system state is: 
LObj has value: 50
HObj has value: 40
Lyle has recently read: 0
Hal has recently read: 40

lyle reads lobj
The current system state is: 
LObj has value: 50
HObj has value: 40
Lyle has recently read: 50
Hal has recently read: 40

lyle writes value 20 to hobj
The current system state is: 
LObj has value: 50
HObj has value: 20
Lyle has recently read: 50
Hal has recently read: 40

lyle reads hobj
The current system state is: 
LObj has value: 50
HObj has value: 20
Lyle has recently read: 0
Hal has recently read: 40


[Input of Test3]
write Hal HObj 10
write Hal LObj 20
read Hal LObj
write Lyle LObj 30
write Lyle HObj 40
read Lyle LObj
read Hal HObj
read Lyle HObj


[Output of Test3]
reading from file: Test3

hal writes value 10 to hobj
The current system state is: 
LObj has value: 0
HObj has value: 10
Lyle has recently read: 0
Hal has recently read: 0

hal writes value 20 to lobj
The current system state is: 
LObj has value: 0
HObj has value: 10
Lyle has recently read: 0
Hal has recently read: 0

hal reads lobj
The current system state is: 
LObj has value: 0
HObj has value: 10
Lyle has recently read: 0
Hal has recently read: 0

lyle writes value 30 to lobj
The current system state is: 
LObj has value: 30
HObj has value: 10
Lyle has recently read: 0
Hal has recently read: 0

lyle writes value 40 to hobj
The current system state is: 
LObj has value: 30
HObj has value: 40
Lyle has recently read: 0
Hal has recently read: 0

lyle reads lobj
The current system state is: 
LObj has value: 30
HObj has value: 40
Lyle has recently read: 30
Hal has recently read: 0

hal reads hobj
The current system state is: 
LObj has value: 30
HObj has value: 40
Lyle has recently read: 30
Hal has recently read: 40

lyle reads hobj
The current system state is: 
LObj has value: 30
HObj has value: 40
Lyle has recently read: 0
Hal has recently read: 40

[Input of Test4]
write Hal HObj 10
write Hal LObj 20
write Lyle HObj 30
write Lyle LObj 40 
read Hal LObj
read Hal HObj
read Lyle LObj
read Lyle HObj


[Output of Test4]
reading from file: Test4

hal writes value 10 to hobj
The current system state is: 
LObj has value: 0
HObj has value: 10
Lyle has recently read: 0
Hal has recently read: 0

hal writes value 20 to lobj
The current system state is: 
LObj has value: 0
HObj has value: 10
Lyle has recently read: 0
Hal has recently read: 0

lyle writes value 30 to hobj
The current system state is: 
LObj has value: 0
HObj has value: 30
Lyle has recently read: 0
Hal has recently read: 0

lyle writes value 40 to lobj
The current system state is: 
LObj has value: 40
HObj has value: 30
Lyle has recently read: 0
Hal has recently read: 0

hal reads lobj
The current system state is: 
LObj has value: 40
HObj has value: 30
Lyle has recently read: 0
Hal has recently read: 40

hal reads hobj
The current system state is: 
LObj has value: 40
HObj has value: 30
Lyle has recently read: 0
Hal has recently read: 30

lyle reads lobj
The current system state is: 
LObj has value: 40
HObj has value: 30
Lyle has recently read: 40
Hal has recently read: 30

lyle reads hobj
The current system state is: 
LObj has value: 40
HObj has value: 30
Lyle has recently read: 0
Hal has recently read: 30
