package com.example.roomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText idEt;
    EditText nameEt;
    EditText emailE;
    Button enterBtn;
    Button getDataBtn;
    TextView idTv;
    TextView nameTv;
    TextView emailTv;


    MyAppDatabase myAppDatabase;

    int id;
    String name;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAppDatabase = Room.databaseBuilder(this, MyAppDatabase.class, "userdb").allowMainThreadQueries().build();

        idEt = findViewById(R.id.idEt);
        nameEt = findViewById(R.id.nameEt);
        emailE = findViewById(R.id.emailEt);
        enterBtn = findViewById(R.id.enterBtn);
        getDataBtn = findViewById(R.id.getDataBtn);
        idTv = findViewById(R.id.idTv);
        nameTv = findViewById(R.id.nameTv);
        emailTv = findViewById(R.id.emailTv);


        String idString = idEt.getText().toString().trim();


        try {
            id = 2;

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEt.getText().toString().trim();
                email = emailE.getText().toString().trim();
                User user = new User();
                user.setId(3);
                user.setName(name);
                user.setMail(email);

                myAppDatabase.myDao().addUser(user);

                Toast.makeText(MainActivity.this, "User Added", Toast.LENGTH_LONG).show();

                idEt.setText("");
                nameEt.setText("");
                emailE.setText("");

            }
        });

        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                List<User> users = myAppDatabase.myDao().getAll();
                for (int i = 0; i < users.size(); i++) {
                    idTv.setText("id = " + users.get(i).getId());
                    nameTv.setText("name = " + users.get(i).getName());
                    emailTv.setText("email = " + users.get(i).getMail());
                }
            }
        });

    }
}
