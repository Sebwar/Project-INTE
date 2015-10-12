import java.sql.Date;


public abstract class Discount {
	
	public abstract int apply(OrderLine orderLine);
}
