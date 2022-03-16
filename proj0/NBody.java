public class NBody
{
	public static double readRadius(String file_name)
	{
		In in = new In(file_name);

		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}

	public static Planet[] readPlanets(String file_name)
	{
		In in = new In(file_name);

		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] fiveplanets = new Planet[num];

		for (int i = 0;i < num ;i++ ) 
		{
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			Planet planet = new Planet(xxPos, yyPos, xxVel,
								   yyVel, mass, imgFileName);

			fiveplanets[i] = planet;
		}

		return fiveplanets;
	}

	public static void main(String[] args) 
	{
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] fiveplanets = readPlanets(filename);

		/* drawing background */
		StdDraw.setScale(-radius, radius);
		String imageBackground = "images/starfield.jpg";
		StdDraw.picture(0, 0, imageBackground);

		//drawing every planet in its position
		for (Planet planet : fiveplanets) 
		{
			planet.draw();
		}

		StdDraw.enableDoubleBuffering();
		int time = 0;
		int num_body = fiveplanets.length;

		while(time <= T)
		{
			double[] xForces = new double [num_body];
			double[] yForces = new double [num_body];

			for(int i = 0; i < num_body; i++)
			{
				xForces[i] = fiveplanets[i].calcNetForceExertedByX(fiveplanets);
				yForces[i] = fiveplanets[i].calcNetForceExertedByY(fiveplanets);
			}
			int contr = 0;
			for(Planet element : fiveplanets)
			{
				element.update(dt, xForces[contr], yForces[contr]);
				contr ++;
			}

			StdDraw.picture(0, 0, imageBackground);
			for(Planet planet : fiveplanets)
			{
				planet.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", num_body);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < num_body; i++) 
		{
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          fiveplanets[i].xxPos, fiveplanets[i].yyPos, fiveplanets[i].xxVel,
                          fiveplanets[i].yyVel, fiveplanets[i].mass, fiveplanets[i].imgFileName);   
		}
	}
}