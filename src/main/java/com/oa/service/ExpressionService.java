package com.oa.service;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.model.MathExpression;
import com.oa.repo.ExpressionRepository;

@Service
public class ExpressionService {

	@Autowired
	ExpressionRepository repository;

	public MathExpression createOrUpdateEmployee(MathExpression entity) {
		String expression = entity.getExpression().replaceAll("\\s", "");
		entity.setExpression(expression);
		String resultString = formatExpression(expression);
		if (resultString != "Invalid Expression") {
			entity.setResult(calculateValue(resultString));
		} else {
			entity.setResult(resultString);
		}
		entity = repository.save(entity);
		return entity;
	}

	public String formatExpression(String expression) {
		char[] charArray = expression.toCharArray();
		String ops = "+-*/()";
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < charArray.length; i++) {
			if (i == 0 || i == charArray.length - 1) {
				if ("+-*/".contains(Character.toString(charArray[i]))) {
					return "Invalid Expression";
				} else {
					sb = sb.append(charArray[i]);
				}
			} else {
				if (ops.contains(Character.toString(charArray[i]))) {
					sb = sb.append(" " + charArray[i] + " ");
				} else {
					sb = sb.append(charArray[i]);
				}
			}
		}
		return sb.toString();
	}

	public String calculateValue(String expression) {
		String result = "";
		try {
			char[] tokens = expression.toCharArray();
			Stack<Integer> values = new Stack<Integer>();
			Stack<Character> ops = new Stack<Character>();
			for (int i = 0; i < tokens.length; i++) {
				if (tokens[i] == ' ')
					continue;
				if (tokens[i] >= '0' && tokens[i] <= '9') {
					StringBuffer sbuf = new StringBuffer();
					while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
						sbuf.append(tokens[i++]);
					values.push(Integer.parseInt(sbuf.toString()));
				} else if (tokens[i] == '(')
					ops.push(tokens[i]);
				else if (tokens[i] == ')') {
					while (ops.peek() != '(')
						values.push(applyOp(ops.pop(), values.pop(), values.pop()));
					ops.pop();
				} else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
					while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
						values.push(applyOp(ops.pop(), values.pop(), values.pop()));
					ops.push(tokens[i]);
				}
			}
			while (!ops.empty())
				values.push(applyOp(ops.pop(), values.pop(), values.pop()));
			return values.pop().toString();
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

	public int applyOp(char op, int b, int a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}

	public boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

}
