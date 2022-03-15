public class Body{

	/*  its current x position */
	public double xxPos;

	//its current y position
	public double yyPos;

	//
	public double xxVel;

	public double yyVel;

	public double mass;

	public String imgFileName;

	public static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV,
				double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body samh){
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		double r = Math.hypot(dx, dy);
		return r;
	}

	public double calcForceExertedBy(Body samh){
		double r1 = calcDistance(b);
		double F = G * this.mass * b.mass / (r1*r1);
		return F;
	}

	public double calcForceExertedByX(Body b){
		double Fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
		return Fx;
	}

	public double calcForceExertedByY(Body b){
		double Fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
		return Fy;
	}

	public double calcNetForceExertedByX(Body[] allBoys){
		Body[] allBoys = {samh, rocinante, aegir};
		
	}
}