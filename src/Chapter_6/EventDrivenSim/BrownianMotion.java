package Chapter_6.EventDrivenSim;


import edu.princeton.cs.algs4.StdDraw;


public class BrownianMotion {

    public static void main(String[] args){

        int N = 300; // number of small particles
        int N_b = 1; // number of large particles

        Particle[] particles = new Particle[N + N_b];

        for (int i = 0; i < N; i++)
            particles[i] = new Particle();

        double s = 0.02;
        for (int i = 0; i < N_b; i++)
            particles[N + i] = new Particle(
        Math.random()*(1-2*s)+s,
        Math.random()*(1-2*s)+s,
        2*(Math.random()-0.5)*2 * 10,
        2*(Math.random()-0.5)*2 * 10,
        s,
        1
        );

        CollisionSystem syst = new CollisionSystem(particles);
        syst.simulate(10000, 50);




    }
}
