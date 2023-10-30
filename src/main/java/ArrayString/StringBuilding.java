package ArrayString;

//To convert any char position to int needs to subtract 'a' like
// 'a' - 'a' = 0
// 'b' - 'a' = 1
// 'z' - 'a' = 25 etc



public class StringBuilding {
    //Efficient string building
    public String stringBuilding(String[] arr) {
        StringBuilder builder  = new StringBuilder();
        for (String s:
                arr) {
            builder.append(s);
        }

        return builder.toString();
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] counter = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }

        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}
