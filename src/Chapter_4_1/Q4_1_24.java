package Chapter_4_1;

import java.util.Arrays;
import Chapter_4_1.Exercises.GraphProperties;
public class Q4_1_24 {




    public static void main(String[] args){

        String filename = "Chapter_4_1/movies.txt";
        String delim = "/";
        SymbolGraph sg = new SymbolGraph(filename, delim);

        Digraph g = sg.G();
        ConnectedComponent cc = new ConnectedComponent(g);

        // Total number of component
        System.out.printf("Total number of component = %d\n", cc.numberOfComponent());

        // size of the largest component
        int largestComponentId = 0;
        int vertexIdPartOfTheLargestComponent = 0;
        Integer[] ComponentsSize = new Integer[cc.numberOfComponent()];
        Arrays.fill(ComponentsSize,0);
        int maxval = 0;
        for (int i = 0; i<g.V(); i++){ ComponentsSize[cc.id(i)]++; }
        for (int i = 0; i < cc.numberOfComponent(); i++) {
            maxval = Math.max(maxval, ComponentsSize[i]);
            if (maxval < ComponentsSize[i]){
                maxval = ComponentsSize[i];
                largestComponentId = i;
            }
        }
        System.out.printf("Size of the largest component = %d\n", maxval);

        // number of components of size less than 10
        int count = 0;
        for (int i = 0; i<cc.numberOfComponent(); i++){
            if (ComponentsSize[i] < 10) count ++;
        }
        System.out.printf("Number of components of size less than 10 = %d\n", count);

        // facts about the largest component
        Digraph largestComponentGraph = new Digraph(maxval);
        GraphProperties gp = new GraphProperties(g, 0);
        System.out.printf("Radius = %d\n", gp.radius());
        System.out.printf("Diameter = %d\n", gp.diameter());
//        System.out.printf("Center = %d\n", gp.center());
//        System.out.printf("Girth = %d\n", gp.girth());
    }

}
//        result:
//        Radius = 9
//        Diameter = 18
//        Center = 2057