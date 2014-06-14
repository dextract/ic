import ast.*;
import semantics.*;
import parser.*;

public class Interpreter {
	public static void main(String args[]) throws Exception {
		Parser parser = new Parser(System.in);
		while (true) {
			System.out.print("> ");
			try {
				ASTNode exp = parser.main();
				System.out.println("Unparsed: "+exp.accept(new UnparseVisitor(),null));
				Value typeCheck = exp.accept(new TypeVisitor(),new EnvImpl<Value>());
				if(typeCheck instanceof UndefinedValue)
					throw new TypingException();
				System.out.println("Typecheck: "+typeCheck.getClass().getSimpleName());
				System.out.println("Evaled: "+exp.accept(new EvalVisitor(), new EnvImpl<Value>()));
			} catch (EvaluationException e) {
				System.out.println("Evaluation Exception");
			} catch (DivideByZeroException e) {
				System.out.println("Divide by zero");
			} catch (UnkownIdentifierException e) {
				System.out.println("Uknown identifier: "+e.getId());
			} catch (TypingException e) {
				System.out.println("Typing error");
				//e.printStackTrace();
			} catch (UnknownTypeException e) {
				System.out.println("Uknown type");

			} catch (Error e) {
				System.out.println("Parsing error");
				System.out.println(e.getMessage());
				break;
			} catch (Exception e) {
				System.out.println("NOK.");
				e.printStackTrace();
				break;
			}
		}
	}
}