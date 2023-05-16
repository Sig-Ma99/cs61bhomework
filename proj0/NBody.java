
public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int number = in.readInt();
        double radius = in.readDouble();
        Planet[] planet = new Planet[number];
        for(int i=0;i<number;i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planet[i] = p;
        }
        return planet;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planet = readPlanets(filename);
        double radius = readRadius(filename);
        StdDraw.setScale(-radius, radius);
        /* Clears the drawing window. */
		StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        for(Planet p:planet){
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        double ct = 0;
        while(ct<T){
            double[] xForces = new double[planet.length];
            double[] yForces = new double[planet.length];
            for (int i=0; i<planet.length;i++) {
                xForces[i] = planet[i].calcNetForceExertedByX(planet);
                yForces[i] = planet[i].calcNetForceExertedByY(planet);
                
            }
            for (int i=0; i<planet.length;i++) {
                planet[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for(Planet p: planet){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            ct += dt;
        }
        StdOut.printf("%d\n", planet.length);
        StdOut.printf("%.2e\n", radius);
        for(int i=0; i< planet.length; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                                    planet[i].xxPos, planet[i].yyPos, planet[i].xxVel,
                                    planet[i].yyVel, planet[i].mass, planet[i].imgFileName);
        }

//
        // 
    }
}
