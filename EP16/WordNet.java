import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

public class WordNet {
   ST<String, Integer> st = new ST<String, Integer>();
   Digraph G;

   // classe para guardar o valor lido de synsets
   public class Word {
      String synset;
      String gloss;

   }

   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
      In syn;
      In hyper;

      try {
         syn = new In(synsets);
         while (!syn.isEmpty()) {
            String line = syn.readLine();
            String[] words = line.split(",");
            st.put(words[1], Integer.valueOf(words[0]));   
         }
        //for (Integer s : st.keys())
        //    StdOut.println(s + " " + st.get(s).synset);

      }
      catch (IllegalArgumentException e) {
            System.out.println(e);
        }

      try {
         hyper = new In(hypernyms);
         G = new Digraph(82192);
         while (!hyper.isEmpty()) {
            String line = hyper.readLine();
            String[] vals = line.split(",");
            for (int i = 1; i < vals.length; i++){
               G.addEdge(Integer.valueOf(vals[0]), Integer.valueOf(vals[i]));
               G.addEdge(Integer.valueOf(vals[i]), Integer.valueOf(vals[0]));
               //StdOut.println(vals[0] + " " + vals[i]);
               } 
         }
         //for (Integer s : st.keys()) {
         //   StdOut.print(s + " "); 
         //   for (Integer j : st.get(s).hypernyms)
         //      StdOut.print(j + " ");
         //   StdOut.println();
         //}
         //StdOut.println(G);
      }
      catch (IllegalArgumentException e) {
            System.out.println(e);
      }
   }

   // all WordNet nouns
   //public Iterable<String> nouns() {}

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
      return st.contains(word);
   }

   // a synset (second field of synsets.txt) that is a shortest common ancestor
   // of noun1 and noun2 (defined below)
   public String sca(String noun1, String noun2) {
      Integer int1 = st.get(noun1);
      Integer int2 = st.get(noun2);
      int s = int1;
      int v = int2;
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);

            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("->" + x);
                }
                StdOut.println();
            }
      return "a";
   }

   // distance between noun1 and noun2 (defined below)
   public int distance(String noun1, String noun2) {
      Integer int1 = st.get(noun1);
      Integer int2 = st.get(noun2);
      BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, int1);
      return bfs.distTo(int2);
   }

   // unit testing (required)
   public static void main(String[] args) {
      WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
      StdOut.println("'apple' é palavra? \t\t\t" + w.isNoun("apple"));
      StdOut.println("'äpple' é palavra? \t\t\t" + w.isNoun("äpple"));
      StdOut.println("distância de 'apple' a 'orange':\t" + w.distance("apple", "orange"));
      w.sca("apple", "orange");
   }

}
