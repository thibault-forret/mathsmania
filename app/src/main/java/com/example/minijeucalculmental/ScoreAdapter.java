package com.example.minijeucalculmental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minijeucalculmental.entities.Score;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private List<Score> scores;

    public ScoreAdapter(List<Score> scores) {
        this.scores = scores;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score score = scores.get(position);

        String username = score.getUsernameElement();
        String scoreUser = score.getScoreUserElement().toString();

        String scoreLabel = String.format("%s - %s", username, scoreUser);

        holder.textViewScore.setText(scoreLabel);
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewScore;

        public ScoreViewHolder(View view) {
            super(view);
            textViewScore = view.findViewById(R.id.textViewScore);
            // Initialize other views here as needed
        }
    }
}

