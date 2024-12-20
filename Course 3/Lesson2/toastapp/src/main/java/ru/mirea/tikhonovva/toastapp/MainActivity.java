package ru.mirea.tikhonovva.toastapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextText);
    }
    public void Counter(View view) {
        int text = editText.getText().toString().length();
        Toast toast1 = Toast.makeText(getApplicationContext(),"Тихонов В.А. №24 БСБО-11-21",Toast.LENGTH_SHORT);
        toast1.show();
        Toast toast2 = Toast.makeText(getApplicationContext(),String.format("Кол-во символов: %s",text),Toast.LENGTH_SHORT);
        toast2.show();

    }
}