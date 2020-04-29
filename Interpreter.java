import java.io.FileNotFoundException;

public class Interpreter {
	
	//private Stack <Integer> stack = new Stack<Integer>();
	public static void main(String[] args) throws FileNotFoundException 
	{	Assignments assign1 = new Assignments();
		Optimize optim;
		//String to hold the expression that has to be calculated
		String expression;
		
		try {
		//pass a name of input file -> return the expression for calculation
		expression = assign1.lexer(args[0]);
		}
		catch(Exception e) {
		 System.out.println("Couln't open the input file");
		 expression = "";
		}
		
		// if there was no expression given, exit
		if(expression == null || expression.isEmpty()) {}
		else
		{	//otherwise, we are going to optimize the input
			//provide for optimize class the expression & HashMap with assignments
			optim = new Optimize(expression, assign1.returnHashMap());
			
			//returns the optimized expression
			String str = optim.optimize(expression);
			
			//class to evaluate the expression
			ExpEvaluator ee = new ExpEvaluator(str);
			//returns the final result
			int res = ee.eval();
			//obtain variable that should hold the result from HashMap
			String var = assign1.findUnknownVar();
			
			//display last element in HashMap
			displayRes(res, var);
			
			
		}

	}
	//function displays final result
	private static void displayRes(int x, String name)
	{	
		System.out.println(name + " = " + x);		
	}
	
	public static void error()
	{	
		throw new RuntimeException("syntax error");
	}
}