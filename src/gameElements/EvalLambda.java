package gameElements;

public class EvalLambda implements Eval {
	
	private float lambda = 0.5f;
	
	public float getLambda() {
		return lambda;
	}

	public void setLambda(float lambda) {
		this.lambda = lambda;
	}

	// TP5 question 2.2
	public float getEval(Player player, Board b) {
		Eval e1 = new Eval1();
		Eval e0 = new Eval0(); 
		 
		return lambda*e0.getEval(player, b)+(1-lambda)*e1.getEval(player, b);
	}


}
