package com.example.myapplication;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "EggProject.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "players";
    public static final String UID_PLAYERS = "id";
    // primary Key,automatic ID
    public static final String PLAYER_NAME = "name";
    // name of theplayer
    public static final String PASSWORD = "password"; // password
    public static final String NUM_OF_GAMES = "num_of_games";
    //number of wins
    //הנתונים מאגר את מאתחל :constructor //
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // creates the tables:
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + UID_PLAYERS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + PLAYER_NAME + " TEXT, " + PASSWORD + " TEXT, " + NUM_OF_GAMES +
                        " INTEGER );");
    }
  //  גירסה כשמעדכנים חדשה ויוצר הישנה הטבלה את מוחק //
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    //קיים פתוח נתונים למסד וסיסמה שם עם שחקן מוסיף //
    public void addPlayer(String name, String password, SQLiteDatabase db)
    {
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_NAME, name);
        cv.put(PASSWORD, password);
        cv.put(NUM_OF_GAMES, 1);
        db.insert(TABLE_NAME, null, cv);
    }
    //נתונים מסד של וסגירה פתיחה תוך וסיסמה שם עם שחקן מוסיף //
    public void addPlayer(String name, String password, int games)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PLAYER_NAME, name);
        cv.put(PASSWORD, password);
        cv.put(NUM_OF_GAMES, games);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }
   // קיים לא הוא אם חדש שחקן מכניס או 1 -ב השחקן של המשחקים מדפר את מגדיל //
    public void updateGames(String name, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (!existPlayer(name, db))
            addPlayer(name, password, db);
        else
        {
            int currentGames = getGames(name, db)+1;
            cv.put(NUM_OF_GAMES, currentGames);
            db.update(TABLE_NAME, cv, PLAYER_NAME + " = '" + name + "'", null);
        }
        db.close();
    }
   // שחקן של המשחקים מספר את מחזיק //
    public int getGames(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int games = 0;
        Cursor c = db.rawQuery("SELECT " + NUM_OF_GAMES + " FROM " + TABLE_NAME +
                " WHERE " + PLAYER_NAME + " = '" + name + "'", null);
        if (c != null && c.moveToFirst())
        {
            games = c.getInt(0);
        }
        if (c != null)
        {
            c.close();
        }
        db.close();
        return games;
    }
    public int getGames(String name, SQLiteDatabase db)
    {
        int games = 0;
        Cursor c = db.rawQuery("SELECT " + NUM_OF_GAMES + " FROM " + TABLE_NAME +
                " WHERE " + PLAYER_NAME + " = '" + name + "'", null);
        if (c != null && c.moveToFirst())
        {
            games = c.getInt(0);
        }
        if (c != null)
        {
            c.close();
        }
        return games;
    }
   // קיים שחקן של הסיסמה את מחזיר //

    public String getPassword(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String password = "";
        Cursor c = db.rawQuery("SELECT " + PASSWORD + " FROM " + TABLE_NAME + " WHERE " + PLAYER_NAME + " = '" + name + "'", null);
        if (c != null && c.moveToFirst())
        {
            password = c.getString(0);
        }
        if (c != null)
        {
            c.close();
        }
        db.close();
        return password;
    }
    public String getPassword(String name, SQLiteDatabase db)
    {
        String password = "";
        Cursor c = db.rawQuery("SELECT " + PASSWORD + " FROM " + TABLE_NAME + " WHERE " + PLAYER_NAME + " = '" + name + "'", null);
        if (c != null && c.moveToFirst())
        {
            password = c.getString(0);
        }
        if (c != null)
        {
            c.close();
        }
        return password;
    }
    //קיים השחקן אם בודק //
    public boolean existPlayer(String name, SQLiteDatabase db)
    {
        boolean found = false;
        Cursor c = db.rawQuery("SELECT " + PASSWORD + " FROM " + TABLE_NAME + " WHERE " + PLAYER_NAME + " = '" + name + "'", null);
        if (c != null && c.moveToFirst())
        {// if moveToFirst() returns false - c is empty
            found = true;
        }
        if (c != null)
        {
            c.close();
        }
        return found;
    }
    public boolean existPlayer(String name)
    {
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                PLAYER_NAME +"= ? " , new String[]{name});
        if (c != null && c.moveToFirst())
        {// if moveToFirst() returns false - c is empty

            found = true;
        }
        if (c != null)
        {
            c.close();
        }
        db.close();
        return found;
    }
    //השחקן לשם תואמת הסיסמה אם בודק //
    public boolean checkPassword(String name, String password)
    {
        if(!existPlayer(name))
            return true;
        if (password.equals(getPassword(name)))
            return true;
        return false;
    }
    // returns the list of player names / leader names
    public ArrayList<String> getPlayersNames()
    {
        ArrayList<String> players = new ArrayList<String>();
// Select All Query
// String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
// looping through all rows and adding to list
        if (c != null && c.moveToFirst())
        {
            int nameIndex = c.getColumnIndex(PLAYER_NAME);
            while (!c.isAfterLast())
            {
                players.add(c.getString(nameIndex));
                c.moveToNext();
            }
        }
        if (c != null)
        {
            c.close();
        }
        db.close();
        return players;
    }
    public ArrayList<Integer> getAllGames()
    {
        ArrayList<Integer> games = new ArrayList<Integer>();
// Select All Query
//String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
// looping through all rows and adding to list
        if (c != null && c.moveToFirst())
        {
            int gameIndex = c.getColumnIndex(NUM_OF_GAMES);
            while (!c.isAfterLast())

            {
                games.add(c.getInt(gameIndex));
                c.moveToNext();
            }
        }
        if (c != null)
        {
            c.close();
        }
        db.close();
        return games;
    }
    // returns all info of pleyers / leaders
    public ArrayList<Player> getPlayersInfo()
    {
        ArrayList<Player> players = new ArrayList<Player>();
// Select All Query
// String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Player p;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
// looping through all rows and adding to list
        if (c != null && c.moveToFirst())
        {
            int nameIndex = c.getColumnIndex(PLAYER_NAME);
            int scoreIndex = c.getColumnIndex(NUM_OF_GAMES);
            int passwordIndex = c.getColumnIndex(PASSWORD);
            while (!c.isAfterLast()) {
                p = new Player(c.getString(nameIndex), c.getString(passwordIndex),
                        c.getInt(scoreIndex));
                players.add(p);
                c.moveToNext();
            }
        }
        if (c != null)
        {
            c.close();
        }
        db.close();
        return players;
    }
    public void removePlayer(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, PLAYER_NAME + " = '" + name + "'", null);
        db.close();
    }
    // clear all table of players / leaders
    public void clearPlayers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();

    }
}