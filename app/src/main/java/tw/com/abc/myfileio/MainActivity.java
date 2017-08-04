package tw.com.abc.myfileio;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        boolean isSound
    }
    public void test3(View view){

    }
    public void test4(View view){

    }
    public void test5(View view){

    }
}
