package ast;

import java.util.Iterator;

public class UnparseVisitor implements Visitor<String,Void> {

	@Override
	public String visit(ASTAdd node, Void v) throws Exception {
		return "Plus("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTAnd node, Void v) throws Exception {
		return "And("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTCond node, Void v) throws Exception {
		return "Cond("+node.cond.accept(this,v)+","+node.then_branch.accept(this,v)+","+node.else_branch.accept(this,v)+")";	
	}

	@Override
	public String visit(ASTDivide node, Void v) throws Exception {
		return "Divide("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTEquals node, Void v) throws Exception {
		return "Equal("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTGreater node, Void v) throws Exception {
		return "Greater("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTGreaterEq node, Void v) throws Exception {
		return "GreaterEq("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTId node, Void v) throws Exception {
		return "Id("+node.id+")";
	}

	@Override
	public String visit(ASTLess node, Void v) throws Exception {
		return "Less("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTLessEq node, Void v) throws Exception {
		return "LessEq("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTMul node, Void v) throws Exception {
		return "Mul("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTNot node, Void v) throws Exception {
		return "Not("+node.left.accept(this,v)+")";
	}

	@Override
	public String visit(ASTNotEquals node, Void v) throws Exception {
		return "NotEq("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTNum node, Void v) throws Exception {
		return ""+node.num;
	}

	@Override
	public String visit(ASTOr node, Void v) throws Exception {
		return "Or("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTSub node, Void v) throws Exception {
		return "Sub("+node.left.accept(this,v)+","+node.right.accept(this,v)+")";
	}

	@Override
	public String visit(ASTDecl node, Void v) throws Exception {
		
		StringBuilder sb = new StringBuilder("Decl({");
		
		Iterator<Pair<String, ASTNode>> it = node.l.iterator();
		while(it.hasNext()) {
			Pair<String, ASTNode> tmp = it.next();
			sb.append(tmp.getElement0()+"="+tmp.getElement1().accept(this,null)+((it.hasNext())?";":""));
		}
		sb.append("},"+node.body.accept(this, null)+")");
		
		return sb.toString();
	}

	@Override
	public String visit(ASTIfElse node, Void v) throws Exception {
		return "IfElse("+node.cond.accept(this,v)+","+node.then_branch.accept(this,v)+","+node.else_branch.accept(this,v)+")";
	}

	@Override
	public String visit(ASTIf node, Void v) throws Exception {
		return "If("+node.cond.accept(this,v)+","+node.then_branch.accept(this,v)+")";
	}

	@Override
	public String visit(ASTWhile node, Void v) throws Exception {
		return "While("+node.left.accept(this,null)+","+node.right.accept(this,null)+")";
	}

	@Override
	public String visit(ASTPrint node, Void v) throws Exception {
		if(node.left==null)
			return "Println()";
		else
			return "Print("+node.left.accept(this,null)+")";
	}

	@Override
	public String visit(ASTTrue node, Void v) throws Exception {
		return "true";
	}

	@Override
	public String visit(ASTFalse node, Void v) throws Exception {
		return "false";
	}

	@Override
	public String visit(ASTSeq node, Void v) throws Exception {
		return "Seq("+node.left.accept(this,null)+","+node.right.accept(this,null)+")";
	}
	
	@Override
	public String visit(ASTDeref node, Void v) throws Exception {
		return "Deref("+node.exp.accept(this, null)+")";
	}
	
	@Override
	public String visit(ASTVar node, Void v) throws Exception {
		return "Var("+node.exp.accept(this, null)+")";
	}
	
	@Override
	public String visit(ASTAssign node, Void v) throws Exception {
		return "Assign("+node.left.accept(this,null)+","+node.right.accept(this,null)+")";
	}
	
	@Override
	public String visit(ASTFun node, Void v) throws Exception {
		
		StringBuilder sb = new StringBuilder("Fun({");
		
		Iterator<Pair<String,String>> it = node.param.iterator();
		while(it.hasNext()) {
			Pair<String,String> p = it.next();
			sb.append(p.getElement0()+(p.getElement1().equals("")?"":" : "+p.getElement1())+((it.hasNext())?",":""));
		}
		sb.append("},"+node.body.accept(this,null)+")");
		
		return sb.toString();
	}

	@Override
	public String visit(ASTApp node, Void v) throws Exception {
		
		StringBuilder sb = new StringBuilder("App("+node.left.accept(this,null)+",{");
		
		Iterator<ASTNode> it = node.right.iterator();
		while(it.hasNext())
			sb.append(it.next().accept(this,null)+((it.hasNext())?",":""));
		sb.append("})");
		
		return sb.toString();
		
	}
	
	@Override
	public String visit(ASTBlock node, Void v) throws Exception {
		return "Block("+node.left.accept(this,null)+","+node.right.accept(this,null)+")";
	}
	
	@Override
	public String visit(ASTProgBlock node, Void v) throws Exception {
		return "ProgBlock("+node.left.accept(this,null)+","+node.right.accept(this,null)+")";
	}
	
	@Override
	public String visit(ASTFree node, Void v) throws Exception {
		return "Free("+node.left.accept(this,null)+")";
	}

	@Override
	public String visit(ASTLabel node, Void v) throws Exception {
		return "Label("+node.label+")";
	}

	@Override
	public String visit(ASTRecord node, Void v) throws Exception {
		
		StringBuilder sb = new StringBuilder("Record({");
		
		Iterator<Pair<String, ASTNode>> it = node.l.iterator();
		while(it.hasNext()) {
			Pair<String, ASTNode> tmp = it.next();
			sb.append(tmp.getElement0()+":"+tmp.getElement1().accept(this,null)+((it.hasNext())?";":""));
		}
		sb.append("})");
		
		return sb.toString();
		
	}

	@Override
	public String visit(ASTRecordVal node, Void v) throws Exception {
		return "RecordVal("+node.label+","+node.left.accept(this,null)+")";
	}
	
	@Override
	public String visit(ASTString node, Void v) throws Exception {
		return ""+node.str;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String visit(ASTList node, Void other) throws Exception {
		
		StringBuilder sb = new StringBuilder("List[");
		
		Iterator<ASTNode> it = node.list.iterator();
		while(it.hasNext())
			sb.append(it.next().accept(this,null)+((it.hasNext())?",":""));
		sb.append("]");
		
		return sb.toString();
		
	}
	
	@Override
	public String visit(ASTListVal node, Void v) throws Exception {
		return "ListVal("+node.left.accept(this,null)+"["+node.right.accept(this,null)+"]"+")";
	}
	
	@Override
	public String visit(ASTForEach node, Void v) throws Exception {
		return "ForEach("+node.id+","+node.left.accept(this,null)+","+node.right.accept(this, null)+")";
	}
	
	@Override
	public String visit(ASTForEachFilter node, Void v) throws Exception {
		return "ForEachFilter("+node.id+","+node.left.accept(this,null)+","+node.right.accept(this, null)+","+node.filter.accept(this, null)+")";
	}
	
	@Override
	public String visit(ASTThis node, Void v) throws Exception {
		return "this";
	}

	@Override
	public String visit(ASTConcat node, Void other) throws Exception {
		return "Concat("+node.left.accept(this,null)+","+node.right.accept(this,null)+")";
	}
	
}