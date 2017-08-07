package tw.com.abc.myfileio;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv);
        sp=getSharedPreferences("mydata",MODE_PRIVATE);
        editor=sp.edit();
    }

    public void test1(View view){
        editor.putString("filename","My Sample");
        editor.putInt("age",10);
        editor.putBoolean("isSound",false);
        editor.commit();
        Toast.makeText(this,"Save OK!!",Toast.LENGTH_LONG).show();

    }
    public void test2(View view){
        boolean isSound=sp.getBoolean("sound",false);
        String username=sp.getString("username","guest");
        int stage= sp.getInt("stage",0);
        int temp= sp.getInt("temp",100);
        tv.setText("User Name:" + username + "\n" +
                "Stage: " + stage + "\n" +
                "Sound: " + (isSound?"On":"Off") + "\n" +
                "Temp: " + temp);
    }
    public void test3(View view){
        try (FileOutputStream fout= openFileOutput("brad.txt",MODE_PRIVATE)){
            fout.write("電腦A公B會C\n".getBytes());
            fout.write("Brid2\n".getBytes());
            fout.write("Brid3\n".getBytes());
            fout.write("Brid4\n".getBytes());
            Toast.makeText(this,"Save OK!!",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void test4(View view){
        try {
            FileInputStream fin = openFileInput("brad.txt");
            byte[] buf = new byte[4096];
            int len = fin.read(buf);
            String data = new String(buf,0,len);
            fin.close();
            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void test5(View view){
        try {
            FileOutputStream fout = openFileOutput("brad2.txt", MODE_APPEND);
            OutputStreamWriter oout = new OutputStreamWriter(fout);
            oout.write("Hello,World\nLine1\nLine2\n");
            oout.flush();
            fout.close();
            Toast.makeText(this, "Save OK", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.i("brad", e.toString());
        }

    }
    public void test6(View view){
        try {
            FileInputStream fin = openFileInput("brad2.txt");
            InputStreamReader inr = new InputStreamReader(fin);
            BufferedReader br = new BufferedReader(inr);
            String line; StringBuffer sb = new StringBuffer();
            while ( (line = br.readLine()) != null){
                sb.append(line + "\n");
            }
            fin.close();
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.i("brad", e.toString());
        }
    }

    public void test7(View view){
        student s1 = new student(90,80,64);
        Log.i("brad", s1.sum() + ":"  +s1.avg());

        FileOutputStream fout = null;
        try {
            fout = openFileOutput("s1.object", MODE_PRIVATE);
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(s1);
            fout.flush();
            fout.close();
            Toast.makeText(this,"Save OK", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.i("brad", e.toString());
        }
    }

    public void test8(View view){
        try {
            ObjectInputStream oin =
                    new ObjectInputStream(openFileInput("s1.object"));
            student s2 = (student) oin.readObject();
            oin.close();
            Log.i("brad", s2.sum() + ":"  +s2.avg());
        }catch (Exception e){
            Log.i("brad", e.toString());
        }
    }
}
