package edu.cgu.ist380.medicationtracker.db;

public class Meds {

	private int id;
	private String name;
	private String description;
	private String qty;
	private String qtyType;
	private String dailyOccurrence;
	
	private String period;
	private String periodType;
	private boolean isEnabled;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getQtyType() {
		return qtyType;
	}
	public void setQtyType(String qtyType) {
		this.qtyType = qtyType;
	}
	public String getDailyOccurrence() {
		return dailyOccurrence;
	}
	public void setDailyOccurrence(String dailyOccurrence) {
		this.dailyOccurrence = dailyOccurrence;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public String toString()
	{
		return 	name +"- " +qty +" " +qtyType +" " + this.dailyOccurrence +" a day for "+this.period +" " +this.periodType;
	}
}
