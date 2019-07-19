import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;

public class WordNet {
   // classe para guardar o valor lido de synsets
   public class Word {
      String synset;
      String gloss;
      Bag<Integer> hypernyms;


      public Word (String synset, String gloss) {
         if (synset == null || gloss == null) throw new IllegalArgumentException("argumento para Word é null");
         this.synset = synset;
         this.gloss = gloss;
         this.hypernyms = new Bag<Integer>();
      }

      void add (Integer number) {
         if (number == null) throw new IllegalArgumentException("argumento para add é null");
         this.hypernyms.add(number);
      }

   }


   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
      In syn;
      In hyper;
      ST<Integer, Word> st = new ST<Integer, Word>();

      try {
         syn = new In(synsets);
         while (!syn.isEmpty()) {
            String line = syn.readLine();
            String[] words = line.split(",");
            Word w = new Word(words[1], words[2]);
            st.put(Integer.valueOf(words[0]), w);   
         }
        for (Integer s : st.keys())
            StdOut.println(s + " " + st.get(s).synset);

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


   }

   // all WordNet nouns
   //public Iterable<String> nouns() {}

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
