package ex07;

import java.sql.Statement;
import java.util.List;

import geometry.Envelope;
import viewer.base.DatabaseLayer;
import viewer.symbols.Symbol;
import viewer.symbols.SymbolFactory;

public class DatabaseLayerExtention extends DatabaseLayer {

	private String where;
	private String orderby;
	private int limit;

	public DatabaseLayerExtention(Statement st, SymbolFactory factory, String tablename, String geocolumnname,
			String where, String orderby, int limit) {
		super(st, factory, tablename, geocolumnname);
		this.where = where;
		this.orderby = orderby;
		this.limit = limit;
	}

	@Override
	public List<Symbol> query(Envelope searchEnv) {
		double x1 = searchEnv.getxMin();
		double x2 = searchEnv.getxMax();
		double y1 = searchEnv.getyMin();
		double y2 = searchEnv.getyMax();
		System.out.println(x1 + " " + x2 + " " + y1 + " " + y2);
		String query = "SELECT *, ST_asText(" + getGeocolname() + ") as geom_wkt FROM " + getTablename();
		if (!where.isBlank()) {
			query += " WHERE " + this.where; 
		}
		if (!orderby.isBlank() && limit != 0) {
			query += " ORDER BY " + this.orderby + " ASC " + " LIMIT " + this.limit;
		}
		System.out.println(query);
//		System.out.println("Testing the debuging in the dblayerExtention");
		return executeQuery(query);

	}

}
