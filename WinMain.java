import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WinMain extends JFrame {

    private JPanel contentPane;
    private String target = "https://lightbox.sakura.ne.jp/demo/json/syain_json.ph";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WinMain frame = new WinMain();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public WinMain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // 中央表示
        setLocationRelativeTo(null);
        // タイトル設定
        setTitle("WindowBuilder");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("実行");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WinMain.get(target);
            }
        });
        btnNewButton.setBounds(160, 50, 90, 20);
        contentPane.add(btnNewButton);
    }

    protected static void get(String target) {
        try {

            // ターゲット
            URL url = new URL( target );
            // 接続オブジェクト
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            // GET メソッド
            http.setRequestMethod("GET");
            // 接続
            http.connect();

            // UTF-8 でリーダーを作成
            InputStreamReader isr = new InputStreamReader(http.getInputStream(), "utf8");
            // 行単位で読み込む為の準備
            BufferedReader br = new BufferedReader(isr);
            String line_buffer;
            // BufferedReader は、readLine が null を返すと読み込み終了
            while ( null != (line_buffer = br.readLine() ) ) {
                // 出力
                System.out.println( line_buffer );
            }

            // 閉じる
            br.close();		// BufferedReader
            isr.close();	// InputStreamReader
            http.disconnect();		// HttpURLConnection

        }
        catch( Exception e ) {
            System.out.println( e.toString() );
        }
    }
}
