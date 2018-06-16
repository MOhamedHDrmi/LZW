package lzw;
import java.util.*;

/**
 *
 * @author HDrmi
 */
public class LZW {

    /**
     * @param args the command line arguments
     */
    public static Map<String, Integer> dictionary = new HashMap<>();
    public static Map<Integer, String> dictdec = new HashMap<>();
    public static ArrayList<Integer> tags = new ArrayList<>();
    public String Source;
    public void compressdictionary() {
        dictionary.clear();
        for (int i = 0; i < 128; ++i) {
            char c = (char) i;
            dictionary.put(String.valueOf(c), i);
        }
    }
    public void decompressdictionary() {
        dictdec.clear();
        for (int i = 0; i < 128; ++i) {
            char c = (char) i;
            dictdec.put(i,""+(char)i);
        }
    }
    public void compress(String str) {
        this.Source=str;
        compressdictionary();
        int cnt = 128, i = 0;
        String tmp = "" + Source.charAt(i);
        i++;
        while (i < Source.length()) {
            char c = Source.charAt(i);
            if (!dictionary.containsKey(tmp + c)) {
                tags.add(dictionary.get(tmp));
                dictionary.put(tmp + c, cnt++);
                tmp = "" + c;
            } else {
                tmp = tmp + c;
            }
            i++;
        }
        tags.add(dictionary.get(tmp));
    }   
    public String decopmress(){
        decompressdictionary();
        int cnt=128;
        String tmp=""+(char)(int)tags.remove(0);
        String Result=tmp;
        for(int n : tags){
            String entry;
            if(dictdec.containsKey(n)){
                entry=dictdec.get(n);
            }else{
                entry=tmp+tmp.charAt(0);
            }
            Result+=entry;
            dictdec.put(cnt++, tmp+entry.charAt(0));
            tmp=entry;
        }
        return Result;
    }
//            "ABAABABBAABAABAAAABABBBBBBBB"

}
