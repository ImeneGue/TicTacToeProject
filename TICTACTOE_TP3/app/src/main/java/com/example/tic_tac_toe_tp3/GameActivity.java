package com.example.tic_tac_toe_tp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
     String joueur1Str, joueur2Str;
     String j1,j2;
    private int scoreJ1, scoreJ2;
     int sj1cpt, sj2cpt;
    private Button[][] buttons = new Button[3][3];
    private boolean tourJoueur1 = true;
    private int cptTour;
    int pointsJoueur1,pointsJoueur2;
    String joueur1,joueur2;
    private TextView textViewPlayer1,textViewPlayer2;
    Button menuBtn,buttonReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        joueur1 =  findViewById(R.id.text_view_p1);
//        joueur2 =  findViewById(R.id.text_view_p2);
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        joueur1 = getIntent().getStringExtra("playerOne");
        joueur2 = getIntent().getStringExtra("playerTwo");

        textViewPlayer1.setText(joueur1 +" X : "+ pointsJoueur1);
        textViewPlayer2.setText(joueur2 + " O : "+ pointsJoueur2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        menuBtn=findViewById(R.id.button_menu);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


        if (savedInstanceState != null) {

            joueur1Str = savedInstanceState.getString("joueur1");
            joueur2Str = savedInstanceState.getString("joueur2");
            scoreJ1 = savedInstanceState.getInt("pointsJoueur1");
            scoreJ2 = savedInstanceState.getInt("pointsJoueur2");
            textViewPlayer1.setText(joueur1Str + " X : "+ String.valueOf( scoreJ1));
            textViewPlayer2.setText(joueur2Str + " 0 : "+ scoreJ2);
            startGame();
            miseAJourPoint();
        }

    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (tourJoueur1) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        startGame();
    }

    private void startGame(){
        cptTour++;
        if (checkVainqueur()) {
            if (tourJoueur1) {
                Joueur1Gagne();
            } else {
                Joueur2Gagne();
            }
        } else if (cptTour == 9) {
            egalite();
        } else {
            tourJoueur1 = !tourJoueur1;
        }
    }

    private boolean checkVainqueur() {
        String[][] btnEspace = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btnEspace[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (btnEspace[i][0].equals(btnEspace[i][1])
                    && btnEspace[i][0].equals(btnEspace[i][2])
                    && !btnEspace[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (btnEspace[0][i].equals(btnEspace[1][i])
                    && btnEspace[0][i].equals(btnEspace[2][i])
                    && !btnEspace[0][i].equals("")) {
                return true;
            }
        }
        if (btnEspace[0][0].equals(btnEspace[1][1])
                && btnEspace[0][0].equals(btnEspace[2][2])
                && !btnEspace[0][0].equals("")) {
            return true;
        }
        if (btnEspace[0][2].equals(btnEspace[1][1])
                && btnEspace[0][2].equals(btnEspace[2][0])
                && !btnEspace[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void Joueur1Gagne() {
        pointsJoueur1++;
        sj1cpt= sj1cpt+  pointsJoueur1;
        String won = GameActivity.this.getResources().getString(R.string.won);
        Toast.makeText(this, joueur1 +" "+ won, Toast.LENGTH_SHORT).show();
        miseAJourPoint();
//        resetGame();
        tourJoueur1 = true;
        reset();
    }

    private void Joueur2Gagne() {
        pointsJoueur2++;
        sj2cpt= sj2cpt+  pointsJoueur2;
        String won = GameActivity.this.getResources().getString(R.string.won);
        Toast.makeText(this, joueur2 +" "+ won, Toast.LENGTH_SHORT).show();
        miseAJourPoint();
//        resetGame();
        tourJoueur1 = false;
        reset();

    }

    private void egalite() {
        String draw = GameActivity.this.getResources().getString(R.string.draw);
        Toast.makeText(this, draw , Toast.LENGTH_SHORT).show();
//      resetGame();
        reset();
    }

    private void miseAJourPoint() {
        textViewPlayer1.setText(joueur1 + " X : "+  pointsJoueur1);
        textViewPlayer2.setText(joueur2 + " 0 : "+ pointsJoueur2);
    }

    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
//        pointsJoueur1 =0;
//        pointsJoueur2 =0;
        cptTour = 0;
//        miseAJourPoint();
        if (tourJoueur1){
        tourJoueur1 = true;}
        else
        {tourJoueur1 = false;}
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        sj1cpt =0;
        sj2cpt=0;
        pointsJoueur1 =0;
        pointsJoueur2 =0;
        cptTour = 0;
        //pointsJoueur1 =0;
        //pointsJoueur2 =0;
        miseAJourPoint();
        tourJoueur1 = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("joueur1", joueur1Str);
        savedInstanceState.putString("joueur2", joueur2Str);
        savedInstanceState.putInt("pointsJoueur1", scoreJ1);
        savedInstanceState.putInt("pointsJoueur2", scoreJ2);
    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        if (savedInstanceState != null) {
//            joueur1Str = savedInstanceState.getString("joueur1");
//            joueur2Str = savedInstanceState.getString("joueur2");
//            scoreJ1 = savedInstanceState.getInt("pointsJoueur1");
//            scoreJ2 = savedInstanceState.getInt("pointsJoueur2");
//            textViewPlayer1.setText(joueur1Str + " X : "+ scoreJ1);
//            textViewPlayer2.setText(joueur2Str + " 0 : "+ scoreJ2);
//            startGame();
//            miseAJourPoint();
//            reset();
//        }
//    }
}