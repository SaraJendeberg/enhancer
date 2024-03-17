package com.motherbrain.enhancer.matchers;

import lombok.Getter;
import org.apache.commons.text.similarity.LevenshteinDistance;

@Getter
public class StringMatcher {
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

    /*public boolean matches(String s1, String s2){
        return preTreat(s1).equals(preTreat(s2));
    }*/

    public boolean matches(String s1, String s2){
        return levenshteinDistance.apply(preTreat(s1), preTreat(s2)) <= 1;
    }

    private String preTreat(String s) {
        return s.replace("AB", "").toLowerCase().strip();
    }

    /** exactly matches
     * FUNDING MATCHES : 97
     * ORGANISATION MATCHES : 206
     * **/

    /** lower case
     * FUNDING MATCHES : 112
     * ORGANISATION MATCHES : 228
     * **/

    /** lower case and stripped
     * FUNDING MATCHES : 114
     * ORGANISATION MATCHES : 231
     * **/

    /** levenshteinDistance <= 3 //Lots of false matches!!!!
     * FUNDING MATCHES : 291
     * ORGANISATION MATCHES : 351
     */

    /** levenshteinDistance <= 2 //Less false matches
     * FUNDING MATCHES : 243
     * ORGANISATION MATCHES : 330
     */

    /** levenshteinDistance <= 1 //Unsure if still false matches like UTA : XTA?
     * FUNDING MATCHES : 172
     * ORGANISATION MATCHES : 275
     */

    /** levenshteinDistance <= 1 and AB removed and lowercase and stripped
     * FUNDING MATCHES : 188
     * ORGANISATION MATCHES : 297
     */
}
