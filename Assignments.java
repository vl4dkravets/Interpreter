import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

public class Assignments {
	// HashMap to hold assignments -> Variable, Value (etc. "x", "2")
	private HashMap <String, String> map;
	
	//scanner to read input from file
	private Scanner scanner;
	
	//2 variables to hold variable and value
	private String variableName;
	private String value;
	
	//holds the expression for further calculations
	private String expression = "";
	private boolean expresFound = false;
	
	
	
	public String lexer(String k) throws FileNotFoundException 
	{	map = new HashMap<String, String>();
		
		//open file, name of which given as a parameter
		File input = new File(k);
		
		//read file via scanner
		scanner = new Scanner(input);
		
		//string to hold a line of input
		String s;
		
		do
		{	s = scanner.nextLine();
			//provide a line to retrieve variable name
			//it will store variable name to global var
			// from there, it goes to getValue(), which will read the line
		    //and store value to global var
			getName(s);
			
			//if input variable value is between 0-9, then store 
			//variable and value to hashmap
			if(value.charAt(0) > 47 && value.charAt(0) < 58)
				map.put(variableName, value);
			//otherwise, in place of value there is an expression
			//then, store variable with temporary value "-"
			else
				map.put(variableName, "-");
			
			//if there's next line of input to read -> go on
		} while(scanner.hasNext());
		
		//from here, it means theValue contains the expression that we need to:
		
		//loop prints assignments
		for(String str: map.keySet())
		{	if(map.get(str) == "-")
				continue;
			else
				System.out.println(str + " = " + map.get(str));
		}
		
		scanner.close();
		
		//returns expression for further evaluation
		return expression;
	}
	
	
	private void getName(String s)
	{	variableName = "";
		String temp = "";
		
		char item;
		int pos = 0;
		for( ; pos < s.length(); pos++)
		{	item = s.charAt(pos);
		
			if(item == 32)
				continue;
			
			else if((item > 96 && item < 123) || item == '_')
			{	temp += item;                     
			}
			//read till equal sign -> then, break out
			else if(item == '=')
				break;
			else if(item > 47 && item < 58)
			{	if(temp.charAt(0) > 96 && temp.charAt(0) < 123)
					temp += item; 
			}
			else
			{	// RunTimeError	
				Interpreter.error();
			}		
		}
		//store variable name
		variableName += temp;
		//then pass the rest of the line to retrieve variable value
		getValue(s.substring(pos+1));

	}

	
	
	
	private void getValue(String s)
	{	value = "";
		char item;
		String temp =""; 
		
		for(int i = 0; i < s.length(); i++)
		{	item = s.charAt(i);
		
			if(item == 32)
				continue;
			
			else if(item > 47 && item < 58)
			{	temp += item;   
			}
			//read till semicolon
			else if(item == ';' || item == '\n')
				break;	
			//if there's is something except digits and semicolon at the end
			// like variables(letters) and operators -> we know it's an expression
			else 
			{	//pass the string with expression
				//returns "-" instead of value
				temp = getExpression(s);
				break;
			}
			
		}
		//it value has more than one digit (10 or more) -> sysntax error
		if(temp.length() > 1)
		{	// RunTimeError	
			Interpreter.error();
		}
		
		//if value was omitted in the input - error
		else if(temp == null || temp.isEmpty())
		{	Interpreter.error();
		}
		//store value to global var
		else
			value = temp;
	}
	
	
	
	private String getExpression(String str)
	{	//set boolean that expression was found
		//store the the line after equal sign into expression var
		if(!expresFound)
		{	expression = str;
			expresFound = true;
		}
		//if there were several expressions - error
		else
		{	// RunTimeError	
			Interpreter.error();
		}	
	
		return "-";
	}
	
	//returns HashMap with assignments
	public HashMap<String, String> returnHashMap()
	{
		return map;	
	}
	
	//find variable which will hold the result of an expression
	//it is stored with value "-"
	public String findUnknownVar()
	{	for (String temp: map.keySet())
			if(map.get(temp) == "-")
				return temp;
	
		// RunTimeError	
		Interpreter.error();
		
		return "Variable not found";		
	}		
	
}


