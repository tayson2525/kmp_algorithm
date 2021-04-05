package kmp_algorithm;

//JAVA program for implementation of Naive and KMP pattern
//searching algorithm comparision

class KMP_String_Matching {
	
void search(String txt, String pat)
{
        int M = pat.length();
        int N = txt.length();
        int comparitions = 0;
        /* A loop to slide pat one by one */
        for (int i = 0; i <= N - M; i++) {
        	
            int j;
  
            /* For current index i, check for pattern 
          match */
            for (j = 0; j < M; j++) {
            	//System.out.println("analisando T[" + i + "] com P[" + j + "]");
            	comparitions ++;
                if (txt.charAt(i + j) != pat.charAt(j)) {
                	break;
                }
                    
            }
  
            if (j == M) { // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
            System.out.println("Pattern found at index " + i);
            }
        }
        System.out.println("Número de comparações do algoritmo da força bruta: " + comparitions);
}
	
 void KMPSearch(String pat, String txt)
 {
     int M = pat.length();
     int N = txt.length();

     // create lps[] that will hold the longest
     // prefix suffix values for pattern
     int lps[] = new int[M];
     int j = 0; // index for pat[]

     // Preprocess the pattern (calculate lps[]
     // array)
     computeLPSArray(pat, M, lps);
     
     //for (int i = 0; i > lps.length-1 ; i++) {
     //    System.out.println("Tabela lps, posicao "+ i + ":" + lps[i]);
     //}

     int i = 0; // index for txt[]
     int comparitions2 = 0;
     while (i < N) {
    	 comparitions2 ++;
         if (pat.charAt(j) == txt.charAt(i)) {
             j++;
             i++;
         }
         if (j == M) {
             System.out.println("Found pattern "
                                + "at index " + (i - j));
             j = lps[j - 1];
         }

         // mismatch after j matches
         else if (i < N && pat.charAt(j) != txt.charAt(i)) {
        	 
             // Do not match lps[0..lps[j-1]] characters,
             // they will match anyway
             if (j != 0) {
                 j = lps[j - 1];
             }else {
                 i = i + 1;
             }
         }
     }
     System.out.println("Número de comparações do algoritmo KMP: " + comparitions2);
     
 }

 void computeLPSArray(String pat, int M, int lps[])
 {
     // length of the previous longest prefix suffix
     int len = 0;
     int i = 1;
     lps[0] = 0; // lps[0] is always 0

     // the loop calculates lps[i] for i = 1 to M-1
     while (i < M) {
         if (pat.charAt(i) == pat.charAt(len)) {
             len++;
             lps[i] = len;
             i++;
         }
         else // (pat[i] != pat[len])
         {
             // This is tricky. Consider the example.
             // AAACAAAA and i = 7. The idea is similar
             // to search step.
             if (len != 0) {
                 len = lps[len - 1];

                 // Also, note that we do not increment
                 // i here
             }
             else // if (len == 0)
             {
                 lps[i] = len;
                 i++;
             }
         }
     }
 }

 // Driver program to test above function
 public static void main(String args[])
 {
     String txt = "BABABAEABAEABAEAABAEABAEAAEBA";
     String pat = "ABAEABAEAAE";
	 //String txt = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB";
	 //String pat = "AAAAAAAAAAAAAB";
     new KMP_String_Matching().KMPSearch(pat, txt);
     new KMP_String_Matching().search(txt, pat);
 }
}