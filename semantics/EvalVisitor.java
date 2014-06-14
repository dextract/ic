package semantics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ast.*;

public class EvalVisitor implements Visitor<Value,Env<Value>> {

	@Override
	public Value visit(ASTAdd node, Env<Value> env) throws Exception {
		return Values.newInt(Values.toInt(node.left.accept(this,env)) + Values.toInt(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTAnd node, Env<Value> env) throws Exception {
		return Values.newBool(Values.toBool(node.left.accept(this,env)) && Values.toBool(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTCond node, Env<Value> env) throws Exception {
		return (Values.toBool(node.cond.accept(this,env)) ? node.then_branch.accept(this,env) : node.else_branch.accept(this,env));
	}

	@Override
	public Value visit(ASTDivide node, Env<Value> env) throws Exception {
		int divisor = Values.toInt(node.right.accept(this,env));
		if(divisor==0)
			throw new DivideByZeroException();
		return Values.newInt(Values.toInt(node.left.accept(this,env)) / divisor);
	}

	@Override
	public Value visit(ASTEquals node, Env<Value> env) throws Exception {
		return Values.newBool(node.left.accept(this,env).equals(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTGreater node, Env<Value> env) throws Exception {
		return Values.newBool(Values.toInt(node.left.accept(this,env)) > Values.toInt(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTGreaterEq node, Env<Value> env) throws Exception {
		return Values.newBool(Values.toInt(node.left.accept(this,env)) >= Values.toInt(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTId node, Env<Value> env) throws Exception {
		return env.find(node.id);
	}

	@Override
	public Value visit(ASTLess node, Env<Value> env) throws Exception {
		return Values.newBool(Values.toInt(node.left.accept(this,env)) < Values.toInt(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTLessEq node, Env<Value> env) throws Exception {
		return Values.newBool(Values.toInt(node.left.accept(this,env)) <= Values.toInt(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTMul node, Env<Value> env) throws Exception {
		return Values.newInt(Values.toInt(node.left.accept(this,env)) * Values.toInt(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTNot node, Env<Value> env) throws Exception {
		return Values.newBool(!(Values.toBool(node.left.accept(this,env))));
	}

	@Override
	public Value visit(ASTNotEquals node, Env<Value> env) throws Exception {
		return Values.newBool(!(node.left.accept(this,env).equals(node.right.accept(this,env))));
	}

	@Override
	public Value visit(ASTNum node, Env<Value> env) throws Exception {
		return Values.newInt(node.num);
	}

	@Override
	public Value visit(ASTOr node, Env<Value> env) throws Exception {
		return Values.newBool(Values.toBool(node.left.accept(this,env)) || Values.toBool(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTSub node, Env<Value> env) throws Exception {
		return Values.newInt(Values.toInt(node.left.accept(this,env)) - Values.toInt(node.right.accept(this,env)));
	}

	@Override
	public Value visit(ASTDecl node, Env<Value> env) throws Exception {
		Env<Value> newEnv = env.beginScope();
		Iterator<Pair<String, ASTNode>> it = node.l.iterator();
		Value v;
		while(it.hasNext()) {
			Pair<String, ASTNode> tmp = it.next();
			//newEnv.assoc(tmp.getElement0(), null);
			//newEnv.update(tmp.getElement0(), tmp.getElement1().accept(this, newEnv));
			if(tmp.equals(node.l.get(0))) {
				v = tmp.getElement1().accept(this, env);
				env.assoc("this", v);
			}
			//newEnv.assoc(tmp.getElement0(), tmp.getElement1().accept(this, env));
			else
				v = tmp.getElement1().accept(this, newEnv);
			//newEnv.assoc(tmp.getElement0(), tmp.getElement1().accept(this, newEnv));
			newEnv.assoc(tmp.getElement0(), v);
		}
		v = node.body.accept(this,newEnv);
		newEnv.endScope();
		return v;
	}

	@Override
	public Value visit(ASTIfElse node, Env<Value> env) throws Exception {
		if( Values.toBool(node.cond.accept(this,env))) 
			return node.then_branch.accept(this,env);
		else 
			return node.else_branch.accept(this,env); 
	}

	@Override
	public Value visit(ASTIf node, Env<Value> env) throws Exception {
		if( Values.toBool(node.cond.accept(this,env))) 
			return node.then_branch.accept(this,env);
		return null;
	}

	@Override
	public Value visit(ASTWhile node, Env<Value> env) throws Exception {
		while(Values.toBool(node.left.accept(this, env)))
			node.right.accept(this, env);
		return BooleanValue.false_value;
	}

	@Override
	public Value visit(ASTPrint node, Env<Value> env) throws Exception {
		if(node.left==null)
			System.out.println();
		else
			System.out.println(node.left.accept(this, env));

		return Values.newUnit();
	}

	@Override
	public Value visit(ASTTrue node, Env<Value> other) throws Exception {
		return BooleanValue.true_value;
	}

	@Override
	public Value visit(ASTFalse node, Env<Value> other) throws Exception {
		return BooleanValue.false_value;
	}

	@Override
	public Value visit(ASTSeq node, Env<Value> env) throws Exception {
		node.left.accept(this, env);
		return node.right.accept(this, env);
	}

	@Override
	public Value visit(ASTDeref node, Env<Value> env) throws Exception {
		RefValue ref = Values.toRef(node.exp.accept(this, env));
		return ref.get();
	}

	@Override
	public Value visit(ASTVar node, Env<Value> env) throws Exception {
		return Values.newRef(node.exp.accept(this, env));
	}

	@Override
	public Value visit(ASTAssign node, Env<Value> env) throws Exception {
		RefValue ref = Values.toRef(node.left.accept(this, env));
		Value v = node.right.accept(this, env);
		ref.set( v );
		return v;
	}

	@Override
	public Value visit(ASTFun node, Env<Value> env) throws Exception {
		return new ClosureValue(node.param, node.body, env);
	}

	@Override
	public Value visit(ASTApp node, Env<Value> env) throws Exception {

		ClosureValue cl = Values.toClosure(node.left.accept(this, env));

		List<Value> args = new ArrayList<Value>();
		Iterator<ASTNode> it = node.right.iterator();
		while(it.hasNext())
			args.add(it.next().accept(this, env));

		Env<Value> cl_env_extended = cl.env.beginScope();

		Iterator<Value> itv = args.iterator();
		Iterator<Pair<String, String>> it1 = cl.param.iterator();
		while(it1.hasNext())
			cl_env_extended.assoc(it1.next().getElement0(), itv.next());

		Value v = cl.body.accept(this,cl_env_extended);
		cl_env_extended.endScope();
		return v;

	}

	public Value visit(ASTBlock node, Env<Value> env) throws Exception {
		node.left.accept(this, env);
		return node.right.accept(this, env);
	}

	public Value visit(ASTProgBlock node, Env<Value> env) throws Exception {
		node.left.accept(this, env);
		return node.right.accept(this, env);
	}

	@Override
	public Value visit(ASTFree node, Env<Value> env) throws Exception {
		System.gc();
		return null;
	}

	@Override
	public Value visit(ASTLabel node, Env<Value> env) throws Exception {
		return Values.newString(node.label);
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
		RecordValue cl = Values.toRecord(node.left.accept(this, env));
		return cl.env.find(node.label);
	}

	@Override
	public Value visit(ASTString node, Env<Value> env) throws Exception {
		return Values.newString(node.str);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Value visit(ASTList node, Env<Value> env)
			throws Exception {

		List<Value> values = new ArrayList<Value>();
		
		Iterator<ASTNode> it = node.list.iterator();
		while(it.hasNext())
			values.add(it.next().accept(this, env));

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
			for(Value v : r.values)
				ret.add(v);
		}
		else
			ret.add(right);

		return new ListValue(ret, env);

	}

}