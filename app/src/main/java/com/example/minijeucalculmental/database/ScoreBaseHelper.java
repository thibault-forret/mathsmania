package com.example.minijeucalculmental.database;

import android.content.Context;

public class ScoreBaseHelper extends DataBaseHelper{

    public ScoreBaseHelper(Context context, String dataBaseName, int dataBaseVersion) {
        super(context, dataBaseName, dataBaseVersion);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS " + ScoreDao.tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                ScoreDao.username + " VARCHAR(50) NOT NULL," +
                ScoreDao.scoreUser + " INTEGER NOT NULL" +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return "DROP TABLE IF EXISTS "+ScoreDao.tableName;
    }
}
