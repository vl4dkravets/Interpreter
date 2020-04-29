The program meant to interpret mathmatical expressions which are provided as strings.
Also, the expression may contain variables, therefore user should provide correct variable 
names and its values.
Additionally, the code performs series of checks which are meant to notify a user about 
an error in given input.
The example of an input can be like this:
x = 2;
y = 3;
z = -+(x--y)+x;

Output:
x = 2;
y = 3;
z = -3;

The input should be provide in a file, and the name of the file is taken as the command-line
argument upon executing the main class called Interpeter

In more detail, all the code consists of 4 classes.
First, it starts from the main class Interpeter, which takes input file name as a parameter.
Then, it passes the name as a string to the next class called Assigments. In there, a file is
opened using provided name, and then scanner scans it line by line. Each line goes through
3 function which store variable name(the part before '=' equal sign), varible value (starting 
from equal sign & till the end). Extra, there's a check whether instead of value there's 
the expression. Next, variable and value are stored into HashMap as strings. Scanning continues
untill the End-Of-File is reached. Afterwards, it prints the assignments - first part of output.

If the expression was given, it is stored in string meant for value storage, and the variable
to hold expression's result is temporarily stored to HashMap with value "-". Thus, if expression
was provided, calculations continue by passing expression back to Interpreter.

Next, from main(), it calls the class called Optimize that takes String expression to compute
and HashMaps which is returned from Assignment class via separate fucntion.
Inside of Optimize class, there are several functions which prepare the expression to fascilitate
its evaluation. Namely, the extra whitespaces are removed, semicolon at the end is removed,
if expression contains mutliple operators (like ++, -+), a function will replace them for 
appropriate single ones. And at the end, variable names in expression are swapped with their
string representation of values from HashMap.

After, optimized expression is returend back to main, from where class ExpExaluator takes it
and begins to compute the result of the expression. The class is based on the rules for the 
"language" it is meant to interpret. Briefly, in may contain words or letters, digits from 0 to 9,
and able to perform 5 operations: addition, subtraction, multiplication, division, 
and negation of a value inside paranthesis.

This class, ExpEvaluator, was written with help of my class and my professor in college.

The only thing changed was a slight modification from myself. As the class operates on the rules
in format - operand1 operator operand2, initially it could not perform negation
of numbers. Thus, I added stack structure that takes record of the operators which simingly
don't follow the rules for the language. And then checks/computes values to which a sign belongs to.

Finally, the numeric result of expression is returned back to main(), then variable name 
of the expression is retrieved from HashMap. And both variable and value are printed to the 
console as final part of the output.

