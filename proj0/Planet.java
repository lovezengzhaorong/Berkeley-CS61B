public class Planet
{

	/*  its current x position */
	public double xxPos;

	//its current y position
	public double yyPos;

	//its current velocity in the x direction
	public double xxVel;

	public double yyVel;

	public double mass;

	public String imgFileName;

	

	public Planet(double xP, double yP, double xV,
				double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b)
	{
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b)
	{
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		double r = Math.hypot(dx, dy);
		return r;
	}

	public double calcForceExertedBy(Planet b)
	{
		double G = 6.67e-11;
		double r1 = calcDistance(b);
		double F = G * this.mass * b.mass / (r1*r1);
		return F;
	}

	public double calcForceExertedByX(Planet b)
	{
		double Fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
		return Fx;
	}

	public double calcForceExertedByY(Planet b)
	{
		double Fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
		return Fy;
	}

	public double calcNetForceExertedByX(Planet[] allBodys)
	{
		double Fxnet = 0;
		for (Planet object : allBodys) 
		{
			if (this.equals(object)) 
			{
				continue;
			}
			Fxnet += this.calcForceExertedByX(object);
		}
		return Fxnet;
	}

	public double calcNetForceExertedByY(Planet[] allBodys)
	{
		double Fynet = 0;
		for (Planet object : allBodys) 
		{
			if (this.equals(object)) 
			{
				continue;
			}
			Fynet += this.calcForceExertedByY(object);
		}
		return Fynet;
	}

	public void update(double dt, double fX, double fY)
	{
		double a_netx = fX / this.mass;
		double a_nety = fY / this.mass;
		xxVel = this.xxVel + dt * a_netx;
		yyVel = this.yyVel + dt * a_nety;
		xxPos = this.xxPos + dt * xxVel;
		yyPos = this.yyPos + dt * yyVel;
	}

	public void draw()
	{
		
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}