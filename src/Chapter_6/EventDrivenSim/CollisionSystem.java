package Chapter_6.EventDrivenSim;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import Chapter_6.EventDrivenSim.Particle;

public class CollisionSystem
{

    private class Event implements Comparable<Event>
    {
        private final double time;
        private final Particle a, b;
        private final int countA, countB;
        public Event(double t, Particle a, Particle b)
        { // Create a new event to occur at time t involving a and b.
            this.time = t;
            this.a = a;
            this.b = b;
            if (a != null) countA = a.count(); else countA = -1;
            if (b != null) countB = b.count(); else countB = -1;
        }
        public int compareTo(Event that)
        {
            if (this.time < that.time) return -1;
            else if (this.time > that.time) return +1;
            else return 0;
        }
        public boolean isValid()
        {
            if (a != null && a.count() != countA) return false;
            if (b != null && b.count() != countB) return false;
            return true;
        }
    }

    private MinPQ<Event> pq; // the priority queue
    private double t = 0.0; // simulation clock time
    private Particle[] particles; // the array of particles
    public CollisionSystem(Particle[] particles)
    { this.particles = particles; }
    private void predictCollisions(Particle a, double limit)
    {
        if (a == null) return;
        for (int i = 0; i < particles.length; i++)
        { // Put collision with particles[i] on pq.
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit)
                pq.insert(new Event(t + dt, a, particles[i]));
        }
        double dtX = a.timeToHitVerticalWall();
        if (t + dtX <= limit)
            pq.insert(new Event(t + dtX, null, a)); // <- change put 'null' before 'a' (correction)
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtY <= limit)
            pq.insert(new Event(t + dtY, a, null)); // <- change put 'a' before 'null' (correction)
    }
    public void redraw(double limit, double Hz)
    { // Redraw event: redraw all particles.
        StdDraw.clear();
        for(int i = 0; i < particles.length; i++) particles[i].draw();
        StdDraw.show(20);
        if (t < limit)
            pq.insert(new Event(t + 1.0 / Hz, null, null));
    }
    public void simulate(double limit, double Hz)
    {


        // added line to allow for drawing:
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setPenColor(255,0,0);
        StdDraw.setYscale(0,1);
        StdDraw.setXscale(0,1);


        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++)
            predictCollisions(particles[i], limit);
        pq.insert(new Event(0, null, null)); // Add redraw event.


        while (!pq.isEmpty())
        { // Process one evenlimitt to drive simulation.


            Event event = pq.delMin();
            if (!event.isValid())
                continue;
            for (int i = 0; i < particles.length; i++)
                particles[i].move(event.time - t); // Update particle positions
            t = event.time; // and time.
            Particle a = event.a, b = event.b;
            if (a != null && b != null)
                a.bounceOff(b);
            else if (a != null && b == null) {
                a.bounceOffHorizontalWall();
                redraw(limit, Hz);
            }
            else if (a == null && b != null) {
                b.bounceOffVerticalWall();
                redraw(limit, Hz);

            }
            else if (a == null && b == null)
                redraw(limit, Hz);
            predictCollisions(a, limit);
            predictCollisions(b, limit);
        }
    }
    public static void main(String[] args)
    {

        StdDraw.show(0);
        int N = Integer.parseInt(args[0]);
        Particle[] particles = new Particle[N];
        for (int i = 0; i < N; i++)
            particles[i] = new Particle();
        StdDraw.show(0);

//        int N = 2;
//        Particle par1 = new Particle(0.75, 0.548, -1, 0, 0.01, 1);
//        Particle par2 = new Particle(0.25, 0.5, 1, 0, 0.04, 1);
//        Particle par3 = new Particle(0.55, 0.5, 0.08, 0, 0.02, 1);
//        Particle[] particles = {par1, par2, par3};



//
//        Particle par1 = new Particle(0.5, 0.9, 0, -1.08, 0.03, 1);
//        Particle par2 = new Particle(0.1536, 0.3, 0.8660+0.15, 0.5000, 0.04, 1);
//        Particle par3 = new Particle(0.8464, 0.3, -0.8660, 0.5000, 0.033, 1);
//        Particle[] particles = {par1, par2, par3};

        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10000, 1000);
    }
}