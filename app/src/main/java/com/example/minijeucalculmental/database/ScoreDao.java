package com.example.minijeucalculmental.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.minijeucalculmental.entities.Score;

public class ScoreDao extends BaseDao<Score>{
    public static String username = "USERNAME";
    public static String scoreUser = "SCORE";
    public static String tableName = "SCORE";

    public ScoreDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected void putValues(ContentValues values, Score entity) {
        values.put(username,entity.getUsernameElement());
        values.put(scoreUser,entity.getScoreUserElement());
    }

    @Override
    protected Score getEntity(Cursor cursor) {
        Score score = new Score();
        Integer indexUsernameElement = cursor.getColumnIndex(username);
        score.setUsernameElement(cursor.getString(indexUsernameElement));
        Integer indexScoreElement = cursor.getColumnIndex(scoreUser);
        score.setScoreUserElement(cursor.getInt(indexScoreElement));
        return score;
    }
}
