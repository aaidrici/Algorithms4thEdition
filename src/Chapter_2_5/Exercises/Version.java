package Chapter_2_5.Exercises;



public class Version implements Comparable<Version> {

    private int major;
    private int minor;
    private int patch;

    public Version(int maj, int min, int pat){
        major = maj;
        minor = min;
        patch = pat;
    }

    public int compareTo(Version that){
        if (this.major != that.major) return this.major - that.major;
        if (this.minor != that.minor) return this.minor - that.minor;
        return this.patch - that.patch;
    }

    public static void main(String[] args){

    }
}
