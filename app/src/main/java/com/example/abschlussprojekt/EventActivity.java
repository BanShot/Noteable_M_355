package com.example.abschlussprojekt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savenbtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                Event event = realm.createObject(Event.class);
                event.setTitle(title);
                Log.d("Tag","Titel: " + title);
                event.setDescription(description);
                event.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Gespeichert", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}