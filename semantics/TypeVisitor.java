package semantics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ast.*;

public class TypeVisitor implements Visitor<Value,Env<Value>> {

	@Override
	public Value visit(ASTAdd node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if(left instanceof IntegerValue && right instanceof IntegerValue)
			return right;
		
		throw new TypingException();

	}

	@Override
	public Value visit(ASTAnd node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if(left instanceof BooleanValue && right instanceof BooleanValue)
			return right;

		throw new TypingException();

	}

	@Override
	public Value visit(ASTTrue node, Env<Value> env)
			throws Exception {

		return Values.newBool(true);

	}

	@Override
	public Value visit(ASTFalse node, Env<Value> env)
			throws Exception {

		return Values.newBool(false);
	}

	@Override
	public Value visit(ASTCond node, Env<Value> env)
			throws Exception {

		Value cond = node.cond.accept(this,env);

		if(cond instanceof BooleanValue) {
			Value thenBr = node.then_branch.accept(this,env);
			Value elseBr = node.else_branch.accept(this,env);
			if( !(thenBr instanceof UndefinedValue) &&
					!(elseBr instanceof UndefinedValue) &&
					thenBr.sameType(elseBr) )
				return elseBr;
		}

		throw new TypingException();
	}

	@Override
	public Value visit(ASTDivide node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if(left instanceof IntegerValue && right instanceof IntegerValue)
			return right;

		throw new TypingException();

	}

	@Override
	public Value visit(ASTEquals node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if( !(left instanceof UndefinedValue) &&
				!(right instanceof UndefinedValue) &&
				left.sameType(right) )
			return Values.newBool(false);

		throw new TypingException();

	}

	@Override
	public Value visit(ASTGreater node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if( !(left instanceof UndefinedValue) &&
				!(right instanceof UndefinedValue) &&
				left.sameType(right) )
			return Values.newBool(false);

		throw new TypingException();

	}

	@Override
	public Value visit(ASTGreaterEq node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if( !(left instanceof UndefinedValue) &&
				!(right instanceof UndefinedValue) &&
				left.sameType(right) )
			return Values.newBool(false);

		throw new TypingException();

	}

	@Override
	public Value visit(ASTId node, Env<Value> env)
			throws Exception {

		return env.find(node.id);

	}

	@Override
	public Value visit(ASTLess node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if( !(left instanceof UndefinedValue) &&
				!(right instanceof UndefinedValue) &&
				left.sameType(right) )
			return Values.newBool(false);

		throw new TypingException();

	}

	@Override
	public Value visit(ASTLessEq node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if( !(left instanceof UndefinedValue) &&
				!(right instanceof UndefinedValue) &&
				left.sameType(right) )
			return Values.newBool(false);

		throw new TypingException();

	}

	@Override
	public Value visit(ASTMul node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if(left instanceof IntegerValue && right instanceof IntegerValue)
			return right;

		throw new TypingException();

	}

	@Override
	public Value visit(ASTNot node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this, env);

		if(left instanceof BooleanValue)
			return Values.newBool(false);

		throw new TypingException();

	}

	@Override
	public Value visit(ASTNotEquals node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if( !(left instanceof UndefinedValue) &&
				!(right instanceof UndefinedValue) &&
				left.sameType(right) )
			return Values.newBool(false);

		throw new TypingException();

	}

	@Override
	public Value visit(ASTNum node, Env<Value> env)
			throws Exception {

		return Values.newInt(0);

	}

	@Override
	public Value visit(ASTOr node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if(left instanceof BooleanValue && right instanceof BooleanValue)
			return right;

		throw new TypingException();

	}

	@Override
	public Value visit(ASTSub node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if(left instanceof IntegerValue && right instanceof IntegerValue)
			return right;

		throw new TypingException();

	}

	@Override
	public Value visit(ASTDecl node, Env<Value> env)
			throws Exception {

		Env<Value> newEnv = env.beginScope();
		Iterator<Pair<String, ASTNode>> it = node.l.iterator();
		Value v;
		while(it.hasNext()) {
			Pair<String, ASTNode> tmp = it.next();
			if(tmp.equals(node.l.get(0))) {
				v = tmp.getElement1().accept(this, env);
				env.assoc("this", v);
			}
			else
				v = tmp.getElement1().accept(this, newEnv);
			newEnv.assoc(tmp.getElement0(), v);
		}
		v = node.body.accept(this,newEnv);
		newEnv.endScope();
		return v;
	}

	@Override
	public Value visit(ASTIfElse node, Env<Value> env)
			throws Exception {

		Value cond = node.cond.accept(this,env);

		if(cond instanceof BooleanValue) {
			Value thenBr = node.then_branch.accept(this,env);
			Value elseBr = node.else_branch.accept(this,env);
			if( !(thenBr instanceof UndefinedValue) &&
					!(elseBr instanceof UndefinedValue) &&
					thenBr.sameType(elseBr) )
				return elseBr;
		}

		throw new TypingException();

	}

	@Override
	public Value visit(ASTIf node, Env<Value> env)
			throws Exception {

		Value cond = node.cond.accept(this,env);
		if(cond instanceof BooleanValue) {
			Value thenBr = node.then_branch.accept(this,env);
			if( !(thenBr instanceof UndefinedValue) )
				return thenBr;
		}

		throw new TypingException();

	}

	@Override
	public Value visit(ASTWhile node, Env<Value> env)
			throws Exception {

		Value left = node.left.accept(this,env);

		if(left instanceof BooleanValue) {
			Value right = node.right.accept(this,env);

			if( !(right instanceof UndefinedValue) )
				return right;
		}

		throw new TypingException();

	}

	@Override
	public Value visit(ASTPrint node, Env<Value> env)
			throws Exception {

		if(! (node.left==null) )
			node.left.accept(this, env);

		return Values.newUnit();

	}

	@Override
	public Value visit(ASTSeq node, Env<Value> env) 
			throws Exception {
		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);

		if( !(left instanceof UndefinedValue) && !(right instanceof UndefinedValue) )
			return right;

		throw new TypingException();
	}

	@Override
	public Value visit(ASTDeref node, Env<Value> env) 
			throws Exception {

		RefValue ref = Values.toRef(node.exp.accept(this, env));
		return ref.get();

	}

	@Override
	public Value visit(ASTAssign node, Env<Value> env) 
			throws Exception {
		Value left = node.left.accept(this,env);
		Value right = node.right.accept(this,env);
		
		if(left instanceof RefValue && !(right instanceof RefValue))
			if(Values.toRef(left).get().sameType(right))
				return right;

		throw new TypingException();
	}

	@Override
	public Value visit(ASTVar node, Env<Value> env) 
			throws Exception {
		Value left = Values.newRef(node.exp.accept(this, env));

		if(left instanceof UndefinedValue)
			throw new TypingException();
		
		return left;
	}

	@Override
	public Value visit(ASTFun node, Env<Value> env)
			throws Exception {

		return new ClosureValue(node.param, node.body, env);

	}

	@Override
	public Value visit(ASTApp node, Env<Value> env)
			throws Exception {

		ClosureValue cl = Values.toClosure(node.left.accept(this, env));

		Value tmp = null;
		Value v = null;

		List<Value> args = new ArrayList<Value>();
		Iterator<ASTNode> it = node.right.iterator();
		while(it.hasNext())
			args.add(it.next().accept(this, env));

		Env<Value> cl_env_extended = cl.env.beginScope();

		Iterator<Value> itv = args.iterator();
		Iterator<Pair<String, String>> it1 = cl.param.iterator();
		
		if(cl.param.size()>args.size())
			throw new TypingException();
		
		
		Pair<String,String> p;
		String[] argsArr = null;
		String ret = null;
		while(it1.hasNext()) {
			p = it1.next();
			v = itv.next();
			if( !p.getElement1().equals("") ) {
				tmp = Values.fromStringToVal(p.getElement1());
				if(tmp instanceof ClosureValue) {
					if(! (v instanceof ClosureValue) )
						throw new TypingException();
					argsArr = p.getElement1().substring(p.getElement1().indexOf("[")+1, p.getElement1().indexOf("]")).replace(" ", "").split(",",-1);
					ret = p.getElement1().substring(p.getElement1().lastIndexOf(",")+1,p.getElement1().lastIndexOf(")")).replace(" ", "");
					
					/*StringBuilder sb = new StringBuilder();
					for(int i=0;i<argsArr.length;i++)
						sb.append(argsArr[i]+"->");
					sb.append(ret);
					System.out.println(sb);*/
					
					ClosureValue tmp1 = Values.toClosure(v);
					
					if(tmp1.param.size()!=argsArr.length)
						throw new TypingException();
					
					
					for(int i=0;i<tmp1.param.size();i++) {
						if( !tmp1.param.get(i).getElement1().equals("") )
							if( !(tmp1.param.get(i).getElement1().equals(argsArr[i])) )
								throw new TypingException();
					}
					
				}
				if(tmp.sameType(v))
					cl_env_extended.assoc(p.getElement0(), v);
				else
					throw new TypingException();
			}
			else
				cl_env_extended.assoc(p.getElement0(), v);
		}

		v = cl.body.accept(this,cl_env_extended);
		
		cl_env_extended.endScope();
		
		it1 = cl.param.iterator();
		while(it1.hasNext()) {
			p = it1.next();
			if( !p.getElement1().equals("") ) {
				tmp = Values.fromStringToVal(p.getElement1());
				if(tmp instanceof ClosureValue) {
					if( !(v.sameType(Values.fromStringToVal(p.getElement1().substring(p.getElement1().lastIndexOf(",")+1,p.getElement1().lastIndexOf(")")).replace(" ", "")))) )
						throw new TypingException();
				}
			}
		}

		return v;

	}

	@Override
	public Value visit(ASTBlock node, Env<Value> env) throws Exception {
		node.left.accept(this, env);
		return node.right.accept(this, env);
	}

	@Override
	public Value visit(ASTProgBlock node, Env<Value> env) throws Exception {
		node.left.accept(this, env);
		return node.right.accept(this, env);
	}

	@Override
	public Value visit(ASTFree node, Env<Value> env) throws Exception {
		return null;
	}

	@Override
	public Value visit(ASTLabel node, Env<Value> env) throws Exception {
		return Values.newString("");
	}

	@Override
	public Value visit(ASTRecord node, Env<Value> env) throws Exception {

		Env<Value> newEnv = env.beginScope();
		Iterator<Pair<String, ASTNode>> it = node.l.iterator();
		while(it.hasNext()) {
			Pair<String, ASTNode> tmp = it.next();
			newEnv.assoc(tmp.getElement0(), tmp.getElement1().accept(this, newEnv));
		}
		newEnv.endScope();
		return new RecordValue(node.l, newEnv);

	}
	
	@Override
	public Value visit(ASTRecordVal node, Env<Value> env) throws Exception {
		RecordValue cl = Values.toRecord(node.left.accept(this,env));
		return cl.env.find(node.label);
	}

	@Override
	public Value visit(ASTString node, Env<Value> env) throws Exception {
		return Values.newString(node.str);
	}

	@Override
	public Value visit(ASTList node, Env<Value> env)
			throws Exception {

		List<Value> values = new ArrayList<Value>();
		
		for(int i = 0;i<node.list.size();i++) {
			Value v = ((ASTNode) node.list.get(i)).accept(this, env);
			if(values.size()==0?true:v.sameType(values.get(i-1)))
				values.add(v);
			else
				throw new TypingException();
		}

		return new ListValue(values, env);

	}

	@Override
	public Value visit(ASTListVal node, Env<Value> env) throws Exception {

		ListValue l = Values.toList(node.left.accept(this, env));

		int index = Values.toInt(node.right.accept(this,env));

		return l.values.get(index);
	}


	@Override
	public Value visit(ASTForEach node, Env<Value> env) throws Exception {

		Env<Value> newEnv = env.beginScope();
		ListValue left = Values.toList(node.left.accept(this, env));

		for(int i=0;i<left.values.size();i++) {
			if(i==0)
				newEnv.assoc(node.id, left.values.get(i));
			else
				newEnv.update(node.id, left.values.get(i));
			node.right.accept(this,newEnv);
		}
		newEnv.endScope();

		return Values.newUnit();

	}

	@Override
	public Value visit(ASTForEachFilter node, Env<Value> env) throws Exception {

		Env<Value> newEnv = env.beginScope();
		ListValue left = Values.toList(node.left.accept(this, env));

		for(int i=0;i<left.values.size();i++) {
			if(i==0)
				newEnv.assoc(node.id, left.values.get(i));
			else
				newEnv.update(node.id, left.values.get(i));

			if(Values.toBool(node.filter.accept(this, newEnv)))
				node.right.accept(this,newEnv);
		}
		newEnv.endScope();

		return Values.newUnit();

	}

	@Override
	public Value visit(ASTThis node, Env<Value> env) throws Exception {
		
		return env.find("this");		
	}

	@Override
	public Value visit(ASTConcat node, Env<Value> env) throws Exception {

		Value left = node.left.accept(this, env);
		Value right = node.right.accept(this, env);
		
		if( !(left instanceof ListValue) && !(right instanceof ListValue) )
			if( !(left.sameType(right)) )
				throw new TypingException();

		ListValue l;
		ListValue r;

		List<Value> ret = new ArrayList<Value>();

		if(left instanceof ListValue) {
			l = Values.toList(left);
			for(Value v : l.values)
				ret.add(v);
		}
		else
			ret.add(left);
		
		if(right instanceof ListValue) {
			r = Values.toList(right);
			for(Value v : r.values) {
				if(ret.size()==0?true:v.sameType(ret.get(ret.size()-1)))
					ret.add(v);
				else
					throw new TypingException();
			}
		}
		else {
			if(ret.size()==0?true:right.sameType(ret.get(ret.size()-1)))
				ret.add(right);
			else
				throw new TypingException();
		}

		return new ListValue(ret, env);

	}
}