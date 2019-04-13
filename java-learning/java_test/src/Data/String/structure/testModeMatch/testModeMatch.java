package Data.String.structure.testModeMatch;

//模式匹配
//两个字符串进行比较 如果使用一般的比较算法 最坏的情况会进行比较 n * m次
// 但是采用 KMP 算法 ， 可将其优化到 n + m次
public class testModeMatch {
    public static void main(String[] args){
        String str = "abaabcac";
        for(int i : get_next(str)){
            System.out.println(i);
        }
    }
    public static int[] get_next(String str) {
        int[] next = new int[str.length() + 1];

        next[1] = 0;
        next[2] = 1;
        int i = 2;
        int j = 1;

        while (i < str.length()) {
            if (str.charAt(i-1) == str.charAt(j-1)) {
                ++i;
                ++j;
                if (str.charAt(i-1) == str.charAt(j-1) && j != 0) {
                        next[i] = next[j];
                } else {
                    next[i] = j;
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }
}

class MatchString{
    String myString;

    public MatchString(String str){
        myString = str;
    }

    public int match(String str){
        // 得到模式串的next数组
        int[] next = get_next(str);

        return 0;
    }

    public static int[] get_next(String str) {
        int[] next = new int[str.length() + 1];

        next[1] = 0;
        int i = 1;
        int j = 0;

        while (i < str.length()) {
            if (j == 0 || str.charAt(i) == str.charAt(j)) {
                ++i;
                ++j;
                if (str.charAt(i) == str.charAt(j)) {
                    next[i] = next[j];
                } else {
                    next[i] = j;
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
