
import java.util.Stack;

public class ExpEvaluator {
    private String s;
    private int currIndex;
    private int n;
    private char inputToken;
    
    private Stack<Character> operators = new Stack<Character>();

    public ExpEvaluator(String s){
        this.s = s;
        currIndex = 0;
        n = s.length();
        nextToken();
    }

    void nextToken(){
        char c;
        do {
            if (currIndex == n){
                inputToken = '$';
                return;
            }
            c = s.charAt(currIndex++);
        } while (Character.isWhitespace(c));
        inputToken = c;
    }

   void match(char token){
       if (inputToken == token){
           nextToken();
       } else {
           throw new RuntimeException("syntax error");
       }
   }

   int eval(){
       int x = exp();
       if (inputToken == '$'){
           return x;
       }
       else return 0;
   }

   int exp(){
       int x = term();
       while (inputToken == '+' || inputToken == '-'){
           char op = inputToken;
           nextToken();
           int y = term();
           x = apply(op, x, y);
       }
       return x;
   }

   int term(){
       int x = factor();
       while (inputToken == '*' || inputToken == '/'){
           char op = inputToken;
           nextToken();
           int y = factor();
           x = apply(op, x, y);
       }
       return x;
   }

   int factor(){
       int x;
       //addition to handle unary operators
       switch(inputToken) {
		   case '+':
		   case '-':	
		   	operators.push(inputToken);
		   	nextToken();     
       }
       
       switch (inputToken){
           case '(':
               nextToken();
               x = exp();
               match(')');
               return x;      
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                x = inputToken - '0';
                nextToken();
                return x;
            default:
                throw new RuntimeException("syntax error");
       }
   }

    int apply(char op, int x, int y){
        int z = 0;
        switch (op){
            case '+': z = x + y; break;
            case '-': z = x - y; break;
            case '*': z = x * y; break;
            case '/': z = x / y; break;
        }
        
        //addition to handle unary operators
        while(!(operators.empty()))
		{op = operators.pop();
		        switch (op){
		            case '+': z = 0 + z; break;
		            case '-': z = 0 - z; break;
		        }
		}
	  
        
        
        return z;
    }

    /*
     public static void main(String []args){
         ExpEvaluator ee = new ExpEvaluator("-(3+2)-(2+1)$");
         int i = ee.eval();
         System.out.println(i);
     }
     */
}