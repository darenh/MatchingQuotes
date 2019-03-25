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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = (Button) findViewById(R.id.test);
        Text = (TextView) findViewById(R.id.Text);
        test.setOnClickListener(this);


    }


    public void onClick(View v) {
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
            String output = store.substring(0, store.indexOf("\n\n"));
            Text.setText(output);
        }
}

    }
