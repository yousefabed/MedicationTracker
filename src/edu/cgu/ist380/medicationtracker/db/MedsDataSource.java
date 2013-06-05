package edu.cgu.ist380.medicationtracker.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MedsDataSource {
	// Database fields
		  private SQLiteDatabase database;
		  private MySQLiteHelper dbHelper;
		  private String[] allColumns = { 
				  MySQLiteHelper.MEDS_COLUMN_ID,
				  MySQLiteHelper.MEDS_COLUMN_NAME,
				  MySQLiteHelper.MEDS_COLUMN_DESC,
				  MySQLiteHelper.MEDS_COLUMN_QTY,
				  MySQLiteHelper.MEDS_COLUMN_QTY_TYPE,
				  MySQLiteHelper.MEDS_COLUMN_DAILY_OCCURRENCE,
				  MySQLiteHelper.MEDS_COLUMN_PERIOD,
				  MySQLiteHelper.MEDS_COLUMN_PERIOD_TYPE,
				  MySQLiteHelper.MEDS_COLUMN_ENABLED
		      };
	public MedsDataSource(Context context) {
		  try{
	    dbHelper = new MySQLiteHelper(context);
		  }
		  catch (Exception e)
		  {
			    Log.e(MedsDataSource.class.getName(), "Error opening the db "+ e.getMessage());
		  }
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }
	  
	  /*
	   *  This method takes an object of type Meds and insert it to the database
	   *  Note that the return type is also Meds, meaning that the inserted 
	   *  object will be returned form the database
	   */
	  public Meds createMed(Meds meds) {
	    ContentValues values = new ContentValues();
	    
	    values.put(   MySQLiteHelper.MEDS_COLUMN_NAME,meds.getName());
		values.put( MySQLiteHelper.MEDS_COLUMN_DESC,meds.getDescription());
	    values.put( MySQLiteHelper.MEDS_COLUMN_QTY,meds.getQty());
	    values.put( MySQLiteHelper.MEDS_COLUMN_QTY_TYPE,meds.getQtyType());
	    values.put(  MySQLiteHelper.MEDS_COLUMN_DAILY_OCCURRENCE,meds.getDailyOccurrence());
	    values.put(  MySQLiteHelper.MEDS_COLUMN_PERIOD,meds.getPeriod());
	    values.put(  MySQLiteHelper.MEDS_COLUMN_PERIOD_TYPE,meds.getPeriodType());
	    values.put(MySQLiteHelper.MEDS_COLUMN_ENABLED, meds.isEnabled());
	    
	    /* call the insert method on the database
	     * 
	     * Since the method only retuns a number of type "long", I need to downcasted to int to be able to update 
	     * the id in my meds object.  meds.setId((int)insertedId);
	     */
	    long insertedId = database.insert(MySQLiteHelper.TABLE_MEDS,null, values);
	    meds.setId((int)insertedId);
	    Log.i(MedsDataSource.class.getName(), "Record : Med with id:" + meds.getId() +" was inserted to the db.");
	    return meds;
	  }
 
	  public void deleteMeds(Meds meds) {
	    long id = meds.getId();
	    database.delete(MySQLiteHelper.TABLE_MEDS, MySQLiteHelper.MEDS_COLUMN_ID
	        + " = " + id, null);
	    Log.i(MedsDataSource.class.getName(), "Record : Med with id:" + meds.getId() +" was deleted from the db.");
		  
	  }
 
	  public List<Meds> getAllMeds() {
	    List<Meds> medsList = new ArrayList<Meds>();
 
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_MEDS,
	        allColumns, null, null, null, null, null);
 
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Meds meds = cursorToMeds(cursor);
	      medsList.add(meds);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return medsList;
	  }
 
	  private Meds cursorToMeds(Cursor cursor) {
	    Meds meds = new Meds();
	    // get the values from the cursor 
	    long id =  cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.MEDS_COLUMN_ID));
	    String name=cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MEDS_COLUMN_NAME));
	    String period = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MEDS_COLUMN_PERIOD));
	    String periodType = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MEDS_COLUMN_PERIOD_TYPE));
	    String desc = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MEDS_COLUMN_DESC));
	    String occurrence = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MEDS_COLUMN_DAILY_OCCURRENCE));
	    String qty =cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MEDS_COLUMN_QTY));
	    String qtyType = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MEDS_COLUMN_QTY_TYPE));
	    String enabled =cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.MEDS_COLUMN_ENABLED));
	    meds.setId((int) id);
	    meds.setName(name);
	    meds.setPeriod(period);
	    meds.setPeriodType(periodType);
	    meds.setDescription(desc);
	    meds.setDailyOccurrence(occurrence);
	    meds.setQty(qty);
	    meds.setQtyType(qtyType);
	    meds.setEnabled(Boolean.valueOf(enabled));
	    return meds;
	  }
}

