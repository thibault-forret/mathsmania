package com.example.minijeucalculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button replyButton;
    private EditText replyInput;
    private TextView numberOfScore;
    private TextView numberOfLife;
    private TextView question;
    private int score;
    private int lives;
    private int currentAnswer;
    private int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();  // Termine l'activité courante
            }
        };
        getOnBackPressedDispatcher().addCallback(   this, callback);

        replyButton = findViewById(R.id.reply_button);
        replyInput = findViewById(R.id.input_reply);

        question = findViewById(R.id.question);
        numberOfScore = findViewById(R.id.number_of_score);
        numberOfLife = findViewById(R.id.number_of_life);

        difficulty = 1;

        initializeGame();

        replyButton.setOnClickListener(view -> checkAnswer());
    }

    private void initializeGame() {
        score = 0;
        lives = 5;
        numberOfScore.setText(String.valueOf(score));
        numberOfLife.setText(String.valueOf(lives));

        generateQuestion();
    }

    private void generateQuestion() {
        Random random = new Random();
        int a, b;
        String operator = "";

        if(score < 5)
            difficulty = 1;
        else
            if(score < 10)
                difficulty = 2;
            else
                if(score < 15)
                    difficulty = 3;
                else
                    if(score < 20)
                        difficulty = 4;
                    else
                        difficulty = 5;

        switch (difficulty) {
            case 1:
                // Niveau 1 : De 1 à 10
                a = random.nextInt(10) + 1;
                b = random.nextInt(10) + 1;
                break;
            case 2:
                // Niveau 2 : De 10 à 100 - Sans multiplication
                a = random.nextInt(91) + 10;
                b = random.nextInt(91) + 10;
                break;
            case 3:
                // Niveau 3 : De 100 à 1000 - Sans multiplication
                a = random.nextInt(901) + 100;
                b = random.nextInt(901) + 100;
                break;
            case 4:
                // Niveau 4 : De 10 à 100 - Avec multiplication
                a = random.nextInt(91) + 10;
                b = random.nextInt(91) + 10;
                break;
            case 5:
                // Niveau 5 : De 100 à 1000 - Avec multiplication
                a = random.nextInt(901) + 100;
                b = random.nextInt(901) + 100;
                break;
            default:
                // Niveau 1 : De 1 à 10
                a = random.nextInt(10) + 1;
                b = random.nextInt(10) + 1;
        }

        int operatorType;

        if(difficulty == 2 || difficulty == 3)
            operatorType = random.nextInt(2);
        else
            // L'opérateur devient la multiplication pour tous les calculs
            operatorType = 2;

        switch (operatorType) {
            case 0:
                operator = "+";
                currentAnswer = a + b;
                break;
            case 1:
                operator = "-";
                // Evitez les résultats négatifs
                if(a < b) {
                    int temp = a;
                    a = b;
                    b =  temp;
                }
                currentAnswer = a - b;
                break;
            case 2:
                operator = "*";
                currentAnswer = a * b;
                break;
        }

        String questionText = a + " " + operator + " " + b + " = ?";
        question.setText(questionText);
    }

    private void checkAnswer() {
        String reponseUtilisateur = replyInput.getText().toString().trim();
        // Si la réponse n'est pas vide
        if (!reponseUtilisateur.isEmpty()) {
            int userAnswer = Integer.parseInt(reponseUtilisateur);
            if (userAnswer == currentAnswer) {
                // Réponse correcte, score + 1
                score++;
                numberOfScore.setText(String.valueOf(score));
            } else {
                // Réponse incorrecte, vie - 1
                lives--;
                numberOfLife.setText(String.valueOf(lives));
                if (lives <= 0) {
                    // ********************
                    // **** FIN DU JEU ****
                    // ********************

                    // Envoies sur la page pour enregistrer le score
                    Intent intent = new Intent(this, InscriptionActivity.class);
                    intent.putExtra("SCORE", score); // Ajoute le score à l'Intent
                    startActivity(intent);
                    finish();
                }
            }
            // Générer une nouvelle question après chaque réponse
            generateQuestion();
            // Effacer le champ de réponse
            replyInput.getText().clear();
        } else {
            Toast.makeText(this, "Veuillez entrer une réponse!", Toast.LENGTH_SHORT).show();
        }
    }
}