package Chapter_6.EventDrivenSim;

import edu.princeton.cs.algs4.StdDraw;


import java.awt.*;

public class Particle {


    private double rx, ry, vx, vy, s, mass;
    private int count;

    public Particle(){
        this.s =  0.0025 + Math.random() * 0.0025;
        this.rx = Math.random()*(1-2*s)+s;
        this.ry = Math.random()*(1-2*s)+s;
        this.vx = 2*(Math.random()-0.5)*2 * 10;
        this.vy = 2*(Math.random()-0.5)*2 * 10;
        this.mass = 1;
        this.count = 0;
    }

    public Particle(double rx, double ry, double vx, double vy, double s, double mass){
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.s = s;
        this.mass = mass;
        this.count = 0;
    }

    public double weight(){return mass; }
    public double x_speed(){return vx; }
    public double y_speed(){return vy;}



    public void draw(){
        StdDraw.setPenColor(255 ,0,0);
        StdDraw.filledCircle(rx,ry,s);
    }

    public void move(double dt){
        rx += vx * dt;
        ry += vy * dt;
    }
    public int count(){
        return this.count;
    }

    Double timeToHit(Particle b){

        if (this == b)
            return Double.POSITIVE_INFINITY;

        double vxb = b.vx - vx; // b's x-speed relative to this
        double vyb = b.vy - vy; // b's y-speed relative to this
        double rxb0 = b.rx - rx; // b's x-position relative to this
        double ryb0 = b.ry - ry; // b's y-position relative to this


        // if dot(v,d) < 0, there is no collision. (where v = <vxb,vyb>, d = <-rxb0,-ryb0>)
        double v_dot_d = - (rxb0 * vxb + ryb0 * vyb);
        if (v_dot_d < 0) return Double.POSITIVE_INFINITY;

        // distance between centers at collision
        double R = s + b.s;

        double p_x = - rxb0 - vxb * v_dot_d / (vxb*vxb + vyb*vyb);
        double p_y = - ryb0 - vyb * v_dot_d / (vxb*vxb + vyb*vyb);
        if (p_x*p_x + p_y*p_y > R*R)  return Double.POSITIVE_INFINITY;


        // distance between centers
        double D = Math.sqrt(rxb0*rxb0 + ryb0*ryb0);

        // angle between b's relative speed vector and b's relative position vector
        double theta = Math.acos((rxb0 * vxb + ryb0 * vyb )/(D*Math.sqrt(vxb*vxb + vyb*vyb)));

        if (theta > Math.PI/2) theta = Math.PI - theta;

        double root1 = quadroot(1.0, -2*D*Math.cos(theta), D*D-R*R, true);
        double root2 = quadroot(1.0, -2*D*Math.cos(theta), D*D-R*R, false);

        double distBeforeCollision = Math.min(root1, root2);

        if (distBeforeCollision < 0) distBeforeCollision = Math.max(root1, root2);


        return distBeforeCollision / Math.sqrt(vxb*vxb + vyb*vyb);

    }
    Double timeToHitHorizontalWall(){
        double BOTTOM_WALL_POS = 0;
        double TOP_WALL_POS = 1;
        if (vy > 0) return (TOP_WALL_POS - ry - s) / vy;
        if (vy < 0) return (BOTTOM_WALL_POS - ry + s) / vy;
        return Double.POSITIVE_INFINITY;
    }
    Double timeToHitVerticalWall(){
        double LEFT_WALL_POS = 0;
        double RIGHT_WALL_POS = 1;
        if (vx > 0) return (RIGHT_WALL_POS - rx - s) / vx;
        if (vx < 0) return (LEFT_WALL_POS - rx + s) / vx;
        return Double.POSITIVE_INFINITY;
    }
    void bounceOff(Particle b){

        // collision plane angle defines Y-axis of other frame
        double angle = Math.atan((b.ry-this.ry)/(b.rx-this.rx));

        // Change coordinates to facilitate problem resolution
        double vt1x = Math.cos(-angle)*this.vx - Math.sin(-angle)*this.vy;
        double vt1y = Math.sin(-angle)*this.vx + Math.cos(-angle)*this.vy;
        double vt2x = Math.cos(-angle)*b.vx - Math.sin(-angle)*b.vy;
        double vt2y = Math.sin(-angle)*b.vx + Math.cos(-angle)*b.vy;

        // compute momentum & energy before impact
        double mom_x0 = this.mass*vt1x + b.mass*vt2x;
        double E0 = 0.5*this.mass*(vt1x*vt1x + vt1y*vt1y) + 0.5*b.mass*(vt2x*vt2x + vt2y*vt2y);

        // solve for vt1x after the impact:
        double A = 0.5*this.mass  + 0.5*this.mass*this.mass/b.mass;
        double B = - mom_x0*this.mass / (b.mass * b.mass);
        double C = 0.5*this.mass*vt1y*vt1y + 0.5*b.mass*vt2y*vt2y + mom_x0*mom_x0/(2*b.mass) - E0;


        double root1 = quadroot(A,B,C,true);
        double root2 = quadroot(A,B,C,false);
        double vt1x_afterCollision;

        if(vt1x > vt2x) vt1x_afterCollision = Math.min(root1, root2);
        else vt1x_afterCollision = Math.max(root1, root2);

        double vt2x_afterCollision = (mom_x0 - vt1x_afterCollision * this.mass) / b.mass;

        // change coordinates back to initial frame
        this.vx = Math.cos(angle)*vt1x_afterCollision - Math.sin(angle)*vt1y;
        this.vy = Math.sin(angle)*vt1x_afterCollision + Math.cos(angle)*vt1y;
        b.vx = Math.cos(angle)*vt2x_afterCollision - Math.sin(angle)*vt2y;
        b.vy = Math.sin(angle)*vt2x_afterCollision + Math.cos(angle)*vt2y;

        count++;
        b.count++;
    }

    void bounceOffHorizontalWall(){
        vy *= -1;
        count++;

    }
    void bounceOffVerticalWall(){
        vx *= -1;
        count++;
    }


    private double quadroot(double a, double b, double c, boolean pos){
        // returns the quadratic root of ax^2 + bx + c = 0;
        double sqrt = Math.sqrt(b*b - 4*a*c);
        double pos_root = (-b + sqrt) / (2*a);
        double neg_root = (-b - sqrt) / (2*a);
        if (pos) return pos_root;
        else return neg_root;
    }




    public static void main(String[] args){


    }

}
