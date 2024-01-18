package com.example.tic_tac_toe_tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button unJoueurBtn, deuxJoueursBtn, instrucBtn, fermerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unJoueurBtn = (Button) findViewById(R.id.onePlayer);
        deuxJoueursBtn = (Button) findViewById(R.id.twoPlayers);
        instrucBtn = (Button) findViewById(R.id.aPropos);
        fermerBtn = (Button) findViewById(R.id.fermer);

        unJoueurBtn.setOnClickListener(this);
        deuxJoueursBtn.setOnClickListener(this);
        instrucBtn.setOnClickListener(this);
        fermerBtn.setOnClickListener(this);

    }
        @Override
        public void onClick (View view){
            switch (view.getId()) {
                case R.id.onePlayer:
//                    Toast.makeText(this, "Un seul Joueur", Toast.LENGTH_SHORT).show();
                    String toastMessage =this.getResources().getString(R.string.HintOnePlayer);
                    Toast.makeText(this,toastMessage , Toast.LENGTH_SHORT).show();

                    break;
                case R.id.twoPlayers:
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                    break;

                case R.id.aPropos:
                    String toastMessage2 =this.getResources().getString(R.string.aboutGame);
                    Toast.makeText(this, toastMessage2, Toast.LENGTH_SHORT).show();
                    String linkWiki =this.getResources().getString(R.string.linkWiki);
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(linkWiki));
                    startActivity(i);
                    break;

                case R.id.fermer:
//                    Toast.makeText(this, "fermer.", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }

}