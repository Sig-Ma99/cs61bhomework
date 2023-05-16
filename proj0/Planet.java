public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double constantG = 6.67e-11;


    public Planet(double xP, double yP, double xV,
                double yV, double m, String img){
                xxPos = xP;
                yyPos = yP;
                xxVel = xV;
                yyVel = yV;
                mass = m;
                imgFileName = img;    
                }
    
    public Planet(Planet p){
        this.xxPos=p.xxPos; this.yyPos=p.yyPos;
        this.xxVel=p.xxVel; this.yyVel = p.yyVel; 
        this.mass = p.mass; this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double distance = (this.xxPos-p.xxPos) * (this.xxPos-p.xxPos) + (this.yyPos-p.yyPos) * (this.yyPos-p.yyPos);
        return Math.sqrt(distance);
    }

    public double calcForceExertedBy(Planet p){
        return constantG * this.mass * p.mass /(calcDistance(p)*calcDistance(p));
    }

    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p)*(p.xxPos-this.xxPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p)*(p.yyPos-this.yyPos)/calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double allForce = 0;
        for(int i=0;i<planets.length;i++){
            if(!this.equals(planets[i])){
                allForce+= calcForceExertedBy(planets[i])*(planets[i].xxPos-this.xxPos)/calcDistance(planets[i]);
            }
        }
        return allForce;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double allForce = 0;
        for(int i=0;i<planets.length;i++){
            if(!this.equals(planets[i])){
                allForce+= calcForceExertedBy(planets[i])*(planets[i].yyPos-this.yyPos)/calcDistance(planets[i]);
            }
        }
        return allForce;
    }

    public void update(double period, double xForce, double yForce){
        double accelerationX = xForce/this.mass;
        double accelerationY = yForce/this.mass;
        this.xxVel = this.xxVel + period*accelerationX;
        this.yyVel = this.yyVel + period*accelerationY;
        this.xxPos = this.xxPos + period * this.xxVel;
        this.yyPos = this.yyPos + period * this.yyVel;

    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "/images/"+imgFileName);
        StdDraw.show();
    }


}
