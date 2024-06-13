package com.example.minijeucalculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.minijeucalculmental.database.ScoreBaseHelper;
import com.example.minijeucalculmental.database.ScoreDao;
import com.example.minijeucalculmental.entities.Score;

public class InscriptionActivity extends AppCompatActivity {
    private int scoreUser;
    private TextView scoreUserText;
    private EditText inputUserName;
    private Button buttonSave;
    private ScoreDao scoreDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inscription);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();  // Termine l'activité courante
            }
        };
        getOnBackPressedDispatcher().addCallback(   this, callback);

        scoreDao = new ScoreDao(new ScoreBaseHelper(this, "db-mathsmania-score", 1));

        // Récupère le score envoyé par l'activité précédente
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("SCORE")) {
            scoreUser = intent.getIntExtra("SCORE", 0); // Valeur par défaut 0 si SCORE n'est pas trouvé
        }

        scoreUserText = findViewById(R.id.score_user);
        inputUserName = findViewById(R.id.input_user_name);
        buttonSave = findViewById(R.id.save_score);

        scoreUserText.setText(String.valueOf(scoreUser));

        buttonSave.setOnClickListener(view -> {
            Score score = new Score();
            score.setUsernameElement(inputUserName.getText().toString().trim());
            score.setScoreUserElement(scoreUser);

            scoreDao.create(score);

            Intent intentMain = new Intent(this, MainActivity.class);
            startActivity(intentMain);
        });
    }
}