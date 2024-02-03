import com.vane.hanguleditor.HangulEditor;
import com.vane.hanguleditor.japan.JapanRecovery;

public class JapanTest {
    public static void main(String[] args) {
        System.out.println(JapanRecovery.change("귦궫궢\n궇궶궫"));
        System.out.println(HangulEditor.isHangul('하'));
    }
}
