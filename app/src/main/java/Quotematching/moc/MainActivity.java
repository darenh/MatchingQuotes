package Quotematching.moc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Node;
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
    EditText Type1;
    private Tries tries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tries = new Tries();
        test = (Button) findViewById(R.id.test);
        Text = (TextView) findViewById(R.id.Text);
        Type1 = (EditText) findViewById(R.id.editText);
        test.setOnClickListener(this);


    }


    public void onClick(View v) {
        int i;
        if (v == (Button) findViewById(R.id.test)) {
            String store = " ";
            String x = Type1.getText().toString();

            try {
                InputStream text = getAssets().open("Quotes.txt");
                int size = text.available();
                byte[] buffer = new byte[size];
                text.read(buffer);
                text.close();
                store = new String(buffer);
                Text.setText(store);
            } catch (IOException ex) {
                // store = "oops";
                ex.printStackTrace();
            }
            String output = store;
            output = output.replace("\n", " ");
            output = output.replace("―", " ");
            output = output.replace(".", ". ");
            output = output.replace("”", "");
            output = output.replace("“", " ");
            output = output.replace(",", " ");
            output = output.replace(";", " ");
            output = output.replace("'", "");
            output = output.replace("-", " ");
            output = output.replace(":", " ");
            output = output.replace("\n", " ");
            output = output.replace("’", "");
            output = output.replace("!", ". ");
            output = output.replace("‘", "");
            output = output.replace("–", " ");
            output = output.replace("?", ". ");
            int flag = 0;
            output = output.toLowerCase();
            String s = Tries.makeTrie(output, x);
            Text.setText(s);
        }
    }


}
