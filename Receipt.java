import java.sql.Date;

public class Receipt {

	private String checkID;
	private int tableNumber;
	private float totalAmount;
	private boolean isPaid;
	private Date date;
	
	// Constructor
	public Receipt() {
		
	}
	
	// Functions
	public String getCheckID() {
		return checkID;
	}
	
	public int getTableNumber() {
		return tableNumber;
	}
	
	public float getTotalAmount() {
		return totalAmount;
	}
	
	public boolean isPaid() {
		return isPaid;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}
	
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void setPaid(boolean paid) {
		isPaid = paid;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}
