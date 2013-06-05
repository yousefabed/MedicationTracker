package edu.cgu.ist380.medicationtracker.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	
	 /* database variables */
		private static final String DATABASE_NAME = "meds.db";
		private static final int DATABASE_VERSION = 6;
		
		/* Meds Table */
		 
		public static final String TABLE_MEDS = "meds";
		public static final String MEDS_COLUMN_ID = "_id";
		public static final String MEDS_COLUMN_NAME = "name";
		public static final String MEDS_COLUMN_DESC = "description";
		public static final String MEDS_COLUMN_QTY = "quantity";
		public static final String MEDS_COLUMN_QTY_TYPE = "quantity_type";
		public static final String MEDS_COLUMN_DAILY_OCCURRENCE = "daily_occurrence";
		public static final String MEDS_COLUMN_PERIOD = "period";
		public static final String MEDS_COLUMN_PERIOD_TYPE = "period_type";
		public static final String MEDS_COLUMN_ENABLED = "is_enabled";
	
		// Database creation sql statement
		private static final String DATABASE_CREATE = "create table " + TABLE_MEDS
				+ "(" 
				+ MEDS_COLUMN_ID + " integer primary key autoincrement, "
				+ MEDS_COLUMN_NAME + " text not null," 
				+ MEDS_COLUMN_DESC + " text  null," 
				+ MEDS_COLUMN_QTY + " text not null,"
				+ MEDS_COLUMN_QTY_TYPE + " text not null,"
				+ MEDS_COLUMN_DAILY_OCCURRENCE + " text not null,"
				+ MEDS_COLUMN_PERIOD + " text not null," 
				+ MEDS_COLUMN_PERIOD_TYPE+ " text not null," 
				+ MEDS_COLUMN_ENABLED + " text not null"   // no comma after last column
				+ ")";
		
		
		
	
	public MySQLiteHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}	
		
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		 arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDS);
		    onCreate(arg0);
		Log.w(MySQLiteHelper.class.getName(),
				        "Upgrading database from version " + arg1 + " to "
				            + arg2 + ", which will destroy all old data");   	

	}

}
