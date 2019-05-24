package Quotematching.moc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button test;
    TextView Text;
    private Tries tries;
    public static final int Quotes = 5;
    // keeps track of quotes stored.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tries = new Tries();
        test = (Button) findViewById(R.id.test);
        Text = (TextView) findViewById(R.id.Text);
        test.setOnClickListener(this);


    }


    public void onClick(View v) {
        int i;
        if (v == (Button) findViewById(R.id.test)) {
            String store = " ";

            try {
                InputStream text = getAssets().open("Quotes.txt");
                int size = text.available();
                byte[] buffer = new byte[size];
                text.read(buffer);
                text.close();
                store = new String(buffer);
            } catch (IOException ex) {
                // store = "oops";
                ex.printStackTrace();
            }
            String output = store;
            for (i = 0; i < Quotes; i++)
            {
                String[] split = output.split("\n\n"); // String array, each element is text between dots
                String beforeNewLine = split[0];
                // exact spot to get each string.
                // enter(beforeNewLine);
                output = output.substring(output.indexOf("\n\n") + 1);
                output = output.trim();
            }
        }
    }

   /* public void enter(String s){
        int i;
        // s += ' ';
        s = s.replace("\n", "");
        s = s.replace("―", "");
        s = s.replace(".", "");
        s = s.replace("”", "");
        s = s.replace("“", "");
        int x = s.length();
        for (i = 0; x > 0; x--){
            String[] split = s.split(" "); // String array, each element is text between dots
            String beforeNewLine = split[0];
            tries.insert(beforeNewLine);
            Text.setText(beforeNewLine);
            s = s.substring(s.indexOf(" ") + 1);
            s = s.trim();
        }
    } */


}
