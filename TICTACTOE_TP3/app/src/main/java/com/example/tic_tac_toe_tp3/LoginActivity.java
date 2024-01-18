package com.example.tic_tac_toe_tp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
//  final EditText joueur1,joueur2;
    Button jouer;
    String joueur1Str,joueur2Str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText joueur1 = (EditText) findViewById(R.id.joueur1Nom);
        final EditText  joueur2 = (EditText) findViewById(R.id.joueur2Nom);
         jouer = findViewById(R.id.JouerBtn);


        jouer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String getPlayerOneName = joueur1.getText().toString();
                final String getPlayerTwoName = joueur2.getText().toString();

                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {

                    String checkName = LoginActivity.this.getResources().getString(R.string.checkName);
                    Toast.makeText(LoginActivity.this, checkName, Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(LoginActivity.this, GameActivity.class);
                    intent.putExtra("playerOne", getPlayerOneName);
                    intent.putExtra("playerTwo", getPlayerTwoName);
                    startActivity(intent);
                    finish();


                }
            }
        });

        if (savedInstanceState != null) {
            String joueur1Str = savedInstanceState.getString("joueur1");
            String joueur2Str = savedInstanceState.getString("joueur2");
            joueur1.setText(String.valueOf(joueur1Str));
            joueur2.setText(String.valueOf(joueur2Str));
        }


    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("playerOne",getPlayerOneName);
//        savedInstanceState.get
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("joueur1", joueur1Str);
        outState.putString("joueur2", joueur2Str);
}
}




