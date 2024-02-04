import com.vane.hanguleditor.HangulEditor;
import com.vane.hanguleditor.HangulSplitItem;
import com.vane.hanguleditor.english.EnglishSound;

public class EnglishTest {
    public static void main(String[] args) {
//        System.out.println(EnglishSound.changeType1("annyeong"));
        System.out.println(EnglishSound.engToKor("hello"));
        System.out.println(EnglishSound.engToKor("apple"));
        System.out.println(EnglishSound.ipaToKor("Hello"));
        System.out.println(EnglishSound.ipaToKor("apple"));
    }
}
