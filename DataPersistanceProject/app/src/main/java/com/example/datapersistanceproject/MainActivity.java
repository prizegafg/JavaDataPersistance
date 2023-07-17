package com.example.datapersistanceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Button btnCreate, btnEdit, btnRead, btnDelete;
    EditText edtInput;
    TextView txtResult;

    String input;
    String edittedInput;
    String fileName = "data " + input + ".txt";
    File data = new File(getFilesDir(), fileName);
    FileOutputStream outputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = edtInput.getText().toString();
                // Add to Create new file
                try {
                    data.createNewFile();
                    outputStream = new FileOutputStream(data, true);
                    outputStream.write(input.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    edittedInput = "";
                    input = "";
                    edtInput.setText(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    data.createNewFile();
                    outputStream = new FileOutputStream(data, false);
                    outputStream.write(edittedInput.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.exists()) {
                    StringBuilder text = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(data));
                        String line = br.readLine();
                        while (line != null) {
                            text.append(line);
                            line = br.readLine();
                        }
                        br.close();
                    } catch (IOException e) {
                        Toast.makeText(MainActivity.this, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    txtResult.setText(text.toString());
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.exists()) {
                    data.delete();
                }

            }
        });

    }
}