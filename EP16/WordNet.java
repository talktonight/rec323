import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

public class WordNet {
   Digraph G;
   String[] aff = new String[82192];

   public int binarySearch (String word) {
      if (word == null) throw new IllegalArgumentException("palavra é null");
      return binarySearch (word, 0, 82192 - 1);
   }

   private int binarySearch (String word, int low, int high) {
      int mid, cmp;
      if (low > high)   return -1;
      mid = (low + high) / 2;
      cmp = word.compareTo(aff[mid]);
      if (cmp == 0)  return mid;
      if (cmp < 0)   return binarySearch(word, low, mid -1);
      else              return binarySearch(word, mid + 1, high);
   }

   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
      if (synsets == null || hypernyms == null) throw new IllegalArgumentException("ao menos um dos arquvios é null");
      In syn;
      In hyper;

      try {
         syn = new In(synsets);
         int k = 0;
         while (!syn.isEmpty()) {
            String line = syn.readLine();
            String[] words = line.split(",");
            aff[k] = words[1];
            k += 1; 
         }

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
      if (word == null) throw new IllegalArgumentException("palavra é null");
      return binarySearch(word) >= 0;
   }

   // a synset (second field of synsets.txt) that is a shortest common ancestor
   // of noun1 and noun2 (defined below)
   public String sca(String noun1, String noun2) {
      if (noun1 == null || noun2 == null) throw new IllegalArgumentException("palavra é null");
      if (!isNoun(noun1) || !isNoun(noun2)) throw new IllegalArgumentException("ao menos uma das palavras não pertence ao WordNet");
      Integer int1 = binarySearch(noun1);
      Integer int2 = binarySearch(noun2);
      int s = int1;
      int v = int2;
      String result = "";
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);

            if (bfs.hasPathTo(v)) {
                result += aff[s] + " to "  + aff[v] + "(" + bfs.distTo(v) + "):  \t\t\t";
                for (int x : bfs.pathTo(v)) {
                    if (x == s) result += aff[x];
                    else        result += "->" + aff[x];
                }
            }
      return result;
   }

   // distance between noun1 and noun2 (defined below)
   public int distance(String noun1, String noun2) {
      if (noun1 == null || noun2 == null) throw new IllegalArgumentException("ao menos uma das palavras é null");
      if (!isNoun(noun1) || !isNoun(noun2)) throw new IllegalArgumentException("ao menos uma das palavras não pertence ao WordNet");
      Integer int1 = binarySearch(noun1);
      Integer int2 = binarySearch(noun2);
      BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, int1);
      return bfs.distTo(int2);
   }

   // unit testing (required)
   public static void main(String[] args) {
      WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
      StdOut.println("'apple' é palavra? \t\t\t" + w.isNoun("apple"));
      StdOut.println("'äpple' é palavra? \t\t\t" + w.isNoun("äpple"));
      StdOut.println("distância de 'apple' a 'orange':\t" + w.distance("apple", "orange"));
      StdOut.println(w.sca("apple", "orange"));
   }

}
