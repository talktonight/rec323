import edu.princeton.cs.algs4.In;

public class WordNet {

   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
      In syn;
      In hyper;
      try {
         syn = new In(synsets);
         while (!syn.isEmpty()) {
            String line = in.readLine();
            System.out.println(line);
         }

      }
      catch (IllegalArgumentException e) {
            System.out.println(e);
        }

      try {
         hyper = new In(hypernyms);
      }
      catch (IllegalArgumentException e) {
            System.out.println(e);
        }
      while (!syn.isEmpty()) {

      }


   }

   // all WordNet nouns
   public Iterable<String> nouns() {}

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
      return false;
   }

   // a synset (second field of synsets.txt) that is a shortest common ancestor
   // of noun1 and noun2 (defined below)
   public String sca(String noun1, String noun2) {
      return "a";
   }

   // distance between noun1 and noun2 (defined below)
   public int distance(String noun1, String noun2) {
      return 0;
   }

   // unit testing (required)
   public static void main(String[] args) {
      WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
   }

}
