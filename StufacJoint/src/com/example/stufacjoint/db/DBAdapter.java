package com.example.stufacjoint.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * 
 * @author Deni
 * @version 1.4
 */
public class DBAdapter {

	/******************** if debug is set true then it will show all Logcat message ************/
	public static final boolean DEBUG = true;

	/******************** Logcat TAG ************/
	public static final String LOG_TAG = "DBAdapter";

	/******************** Table Fields ************/
	public static final String KEY_ID = "_id";

	public static final String KEY_USER_NAME = "ime";

	public static final String KEY_USER_LAST_NAME = "prezime";

	public static final String KEY_USER_EMAIL = "mail";

	public static final String KEY_PASSWORD = "lozinka";

	/******************** Database Name ************/
	public static final String DATABASE_NAME = "DB_sqllite";

	/******************** Database Version (Increase one if want to also upgrade your database) ************/
	public static final int DATABASE_VERSION = 1;// started at 1

	/** Table names */
	public static final String USER_TABLE = "tbl_user";

	/******************** Set all table with comma seperated like USER_TABLE,ABC_TABLE ************/
	private static final String[] ALL_TABLES = { USER_TABLE };

	/** Create table syntax */
	private static final String USER_CREATE = "create table tbl_user(_id integer primary key autoincrement, ime text not null, prezime text not null, mail text not null, lozinka text not null);";

	/******************** Used to open database in syncronized way ************/
	private static DataBaseHelper DBHelper = null;

	/**
	 * Metoda za DB Adapter
	 */
	public DBAdapter() {
	}

	/******************* Initialize database *************/
	public static void init(Context context) {
		if (DBHelper == null) {
			if (DEBUG)
				Log.i("DBAdapter", context.toString());
			DBHelper = new DataBaseHelper(context);
		}
	}

	/********************** Main Database creation INNER class ********************/
	private static class DataBaseHelper extends SQLiteOpenHelper {
		public DataBaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			if (DEBUG)
				Log.i(LOG_TAG, "new create");
			try {
				db.execSQL(USER_CREATE);

			} catch (Exception exception) {
				if (DEBUG)
					Log.i(LOG_TAG, "Exception onCreate() exception");
			}
		}
		/**
		 * 
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			if (DEBUG)
				Log.w(LOG_TAG, "Upgrading database from version" + oldVersion
						+ "to" + newVersion + "...");

			for (String table : ALL_TABLES) {
				db.execSQL("DROP TABLE IF EXISTS " + table);
			}
			onCreate(db);
		}

	} // Inner class closed

	/********************** Open database for insert,update,delete in syncronized manner ********************/
	private static synchronized SQLiteDatabase open() throws SQLException {
		return DBHelper.getWritableDatabase();
	}

	/************************ General functions **************************/

	/*********************** Escape string for single quotes (Insert,Update) ************/
	private static String sqlEscapeString(String aString) {
		String aReturn = "";

		if (null != aString) {
			// aReturn = aString.replace("'", "''");
			aReturn = DatabaseUtils.sqlEscapeString(aString);
			// Remove the enclosing single quotes ...
			aReturn = aReturn.substring(1, aReturn.length() - 1);
		}

		return aReturn;
	}

	/*********************** UnEscape string for single quotes (show data) ************/
	@SuppressWarnings("unused")
	private static String sqlUnEscapeString(String aString) {

		String aReturn = "";

		if (null != aString) {
			aReturn = aString.replace("''", "'");
		}

		return aReturn;
	}

	/********************************************************************/

	/**
	 * All Operations (Create, Read, Update, Delete)
	 */
	// Adding new contact

	public static void addUserData(Profil uData) {
		final SQLiteDatabase db = open();

		String name = sqlEscapeString(uData.getIme());
		String lastname = sqlEscapeString(uData.getPrezime());
		String email = sqlEscapeString(uData.getMail());
		String password = sqlEscapeString(uData.getLozinka());

		ContentValues cVal = new ContentValues();
		cVal.put(KEY_USER_NAME, name);
		cVal.put(KEY_USER_LAST_NAME, lastname);
		cVal.put(KEY_USER_EMAIL, email);
		cVal.put(KEY_PASSWORD, password);
		db.insert(USER_TABLE, null, cVal);
		db.close(); // Closing database connection
	}
	/**
	 * Metoda za dohvaćanje jednog kontakta
	 * @param mail - mailadresa
	 * @return password - lozinka
	 */
	// Getting single contact
	public static String getProfilData(String mail) {
		Log.e("UserData", "Radi");
		final SQLiteDatabase db = open();

		Cursor cursor = db.query(USER_TABLE,
				new String[] { KEY_ID, KEY_USER_NAME, KEY_USER_LAST_NAME,
						KEY_USER_EMAIL, KEY_PASSWORD }, KEY_USER_EMAIL + "=?",
				new String[] { String.valueOf(mail) }, null, null, null, null);

		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
			return "Ne postoji korisnik...";
		}
		cursor.moveToNext();

		String password = cursor.getString(cursor.getColumnIndex("lozinka"));
		cursor.close();
		return password;
	}
/**
 * Dohvaćanje podataka za punjenje profila
 * @param mail - mailadresa
 * @return podaci - vraća profilne podatke korisnika, 
 * 					ili poruku da ne postoji korisnik
 */
	public static String getProfilDataFUll(String mail) {
		Log.e("UserData", "Radi");
		final SQLiteDatabase db = open();

		Cursor cursor = db.query(USER_TABLE,
				new String[] { KEY_ID, KEY_USER_NAME, KEY_USER_LAST_NAME,
						KEY_USER_EMAIL, KEY_PASSWORD }, KEY_USER_EMAIL + "=?",
				new String[] { String.valueOf(mail) }, null, null, null, null);

		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
			return "Ne postoji korisnik...";
		}
		cursor.moveToNext();

		String podaci = cursor.getString(cursor.getColumnIndex("ime")) + ","
				+ cursor.getString(cursor.getColumnIndex("prezime")) + ","
				+ cursor.getString(cursor.getColumnIndex("mail"));
		cursor.close();
		return podaci;
	}
	/**
	 * Dohvaćanje podataka o svim korisnicima
	 * @return contactList - lista kontakta
	 */
	// Getting All Contacts
	public static List<Profil> getAllUserData() {
		List<Profil> contactList = new ArrayList<Profil>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + USER_TABLE;

		final SQLiteDatabase db = open();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Profil data = new Profil();
				data.setId(Integer.parseInt(cursor.getString(0)));
				data.setIme(cursor.getString(1));
				data.setPrezime(cursor.getString(2));
				data.setMail(cursor.getString(3));
				data.setLozinka(cursor.getString(4));
				// Adding contact to list
				contactList.add(data);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}
	/**
	 * Azuriranje pojedinacnog kontakta
	 * @param data - profilni podaci kontakta
	 * @return - db.update- ažurirane podatke u bazi
	 */
	// Updating single contact
	public static int updateUserData(Profil data) {
		final SQLiteDatabase db = open();

		ContentValues values = new ContentValues();
		values.put(KEY_USER_NAME, data.getIme());
		values.put(KEY_USER_LAST_NAME, data.getPrezime());
		values.put(KEY_USER_EMAIL, data.getMail());
		values.put(KEY_PASSWORD, data.getLozinka());

		// updating row
		return db.update(USER_TABLE, values, KEY_ID + " = ?",
				new String[] { String.valueOf(data.getId()) });
	}
	/**
	 * Brisanje pojedniacnog kontakta
	 * @param data - profilni podaci kontakta koji se brišu
	 */
	// Deleting single contact
	public static void deleteUserData(Profil data) {
		final SQLiteDatabase db = open();
		db.delete(USER_TABLE, KEY_ID + " = ?",
				new String[] { String.valueOf(data.getId()) });
		db.close();
	}
	/**
	 * prebrojavanje kontakata zapisanih u bazi
	 * 
	 * @return broj kontakta
	 */
	// Getting contacts Count
	public static int getUserDataCount() {
		String countQuery = "SELECT  * FROM " + USER_TABLE;
		final SQLiteDatabase db = open();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
