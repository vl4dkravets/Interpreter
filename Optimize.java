import java.util.HashMap;

public class Optimize {
	private String exp;
	private HashMap <String, String> values;
	
	Optimize(String expression, HashMap <String, String> map)
	{	// initialize the expression for calculation
		exp = expression;
		//store the HashMap with assignments
		values = map;
	}
	
	public String optimize(String expression)
	{	
		removeExtraSpaces();
		
		removeSemicolonAtTheEnd();
		
		//function changes multiple operators for single ones (etc. ++ is + and --- is -)
		exp = checkForMultipleOperators(exp);
		
		//plugs in actual values from HashMap instead of variables
		exp = swapVariables(exp);
		
		//returns optimized expression
		return exp;
	}
	
	
	private String checkForMultipleOperators(String str)
	{			
		String temp = "";
		char c1, c2;	

		//looks for adjacent operators & checks them
		for(int i = 1; i < str.length(); i++)
		{	c1 = str.charAt(i-1);
			c2 = str.charAt(i);
			
			if((c1 == '+' || c1 == '-') &&(c2 == '+' || c2 == '-'))
			{	 temp = str.substring(i-1, i+1);
				 str = changeSigns(temp, str);
				 i--;
			}
		}
		
		return str;
	}

	//swaps adjacent operators for single ones 
	//and replace them into equation
	private String changeSigns(String temp, String main)
	{
		if (temp.contentEquals("--"))
			main = main.replace(temp, "+");
			
		else if (temp.contentEquals("++"))
			main = main.replace(temp, "+");
		
		else if (temp.contentEquals("+-"))
			main = main.replace(temp, "-");
			
		else if(temp.contentEquals("-+"))
			main = main.replace(temp, "-");
		
		return main;
	}
	
	private String swapVariables(String str)
	{
		for(String temp: values.keySet())
			str = str.replaceAll(temp, values.get(temp));
		
		return str;
	}
	
	private void removeExtraSpaces() 
	{	//replace all whitespaces in the expression
		exp = exp.replaceAll("\\s+","");
	}

	private void removeSemicolonAtTheEnd()
	{	//replace all semicolon in the expression
		exp = exp.replaceAll(";","");
	}
}
