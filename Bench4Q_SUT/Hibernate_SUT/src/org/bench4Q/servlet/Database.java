/**
 * =========================================================================
 * 					Bench4Q version 1.0.0
 * =========================================================================
 * 
 * Bench4Q is available on the Internet at http://forge.ow2.org/projects/jaspte
 * You can find latest version there. 
 * 
 * Distributed according to the GNU Lesser General Public Licence. 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by   
 * the Free Software Foundation; either version 2.1 of the License, or any
 * later version.
 * 
 * SEE Copyright.txt FOR FULL COPYRIGHT INFORMATION.
 * 
 * This source code is distributed "as is" in the hope that it will be
 * useful.  It comes with no warranty, and no author or distributor
 * accepts any responsibility for the consequences of its use.
 *
 *
 * This version is a based on the implementation of TPC-W from University of Wisconsin. 
 * This version used some source code of The Grinder.
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *  * Initial developer(s): Zhiquan Duan.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * 
 */
package org.bench4Q.servlet;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bench4Q.hibernate.AddressDAO;
import org.bench4Q.hibernate.Author;
import org.bench4Q.hibernate.AuthorDAO;
import org.bench4Q.hibernate.CcXacts;
import org.bench4Q.hibernate.Country;
import org.bench4Q.hibernate.CountryDAO;
import org.bench4Q.hibernate.CustomerDAO;
import org.bench4Q.hibernate.HibernateSessionFactory;
import org.bench4Q.hibernate.Item;
import org.bench4Q.hibernate.ItemDAO;
import org.bench4Q.hibernate.Orders;
import org.bench4Q.hibernate.ShoppingCart;
import org.bench4Q.hibernate.ShoppingCartLine;
import org.bench4Q.hibernate.ShoppingCartLineId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
@SuppressWarnings(value={"unused","rawtypes","unchecked"})
public class Database {
//	static Date date = new Date(System.currentTimeMillis());

//	public static String dsName;

//	private static InitialContext ctx;
//
//	private static void initial() {
//		DBHelper dbhelper = DBHelper.getInstance();
//		dsName = dbhelper.getProperty("dsName");
//		try {
//			ctx = new InitialContext();
//		} catch (javax.naming.NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static Connection getConnection() {
//		Connection conn = null;
//		try {
//			if (ctx == null)
//				initial();
//			DataSource ds = null;
//			ds = (DataSource) ctx.lookup(dsName);
//			conn = ds.getConnection();
//			conn.setAutoCommit(false);
//		} catch (NamingException ne) {
//			ne.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
//
//	public static void closeConnection(Connection con) {
//		try {
//			if (con != null) {
//				con.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void closeStmt(PreparedStatement stmt) {
//		try {
//			if (stmt != null) {
//				stmt.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void closeStmt(Statement stmt) {
//		try {
//			if (stmt != null) {
//				stmt.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void closeResultSet(ResultSet rs) {
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public static String[] getName(int c_id) {
		String name[] = new String[2];
//		Connection con = null;
//		PreparedStatement get_name = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		try {
			// Prepare SQL
			// out.println("About to call getConnection!");
			// out.flush();
//			con = getConnection();
			// out.println("About to preparestatement!");
			// out.flush();
			
			CustomerDAO customerDAO = new CustomerDAO();
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			org.bench4Q.hibernate.Customer customerHib = customerDAO.findById(c_id);
//			get_name = con.prepareStatement("SELECT c_fname,c_lname FROM customer WHERE c_id = ?");
//
//			// Set parameter
//			get_name.setInt(1, c_id);
//			// out.println("About to execute query!");
//			// out.flush();
//
//			rs = get_name.executeQuery();
//
//			// Results
//			rs.next();
//			name[0] = rs.getString("c_fname");
//			name[1] = rs.getString("c_lname");
			name[0] = customerHib.getCFname();
			name[1] = customerHib.getCLname();
			
			tx.commit();

//			con.commit();

		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(get_name);
//			closeConnection(con);
			if (session != null) {
				session.close();
			}
		}
		return name;
	}

	public static Book getBook(int i_id) {
		Book book = null;
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		
		Session session = null;
		Transaction tx = null;
		
		
		try {
			ItemDAO itemDAO = new ItemDAO();
			AuthorDAO authorDAO = new AuthorDAO();
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
//			con = getConnection();
//			statement = con
//					.prepareStatement("SELECT * FROM item,author WHERE item.i_a_id = author.a_id AND i_id = ?");
			
			Item item = itemDAO.findById(i_id);
			Author author = authorDAO.findById(item.getIAId());
			
			book = new Book(item, author);
			
			tx.commit();
			
//			// Set parameter
//			statement.setInt(1, i_id);
//			rs = statement.executeQuery();
//			// Results
//			rs.next();
//			book = new Book(rs);
//			con.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(statement);
//			closeConnection(con);
			if(session != null) {
				session.close();
			}
		}
		return book;
	}
	
	public static Customer getCustomer(String UNAME) {
		org.bench4Q.servlet.Customer cust = null;
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			// Prepare SQL
//			con = getConnection();
//			statement = con
//					.prepareStatement("SELECT * FROM customer, address, country WHERE customer.c_addr_id = address.addr_id AND address.addr_co_id = country.co_id AND customer.c_uname = ?");
//
//			// Set parameter
//			statement.setString(1, UNAME);
//			rs = statement.executeQuery();
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			org.bench4Q.hibernate.CustomerDAO customerDAO = new org.bench4Q.hibernate.CustomerDAO();
			
			List listCustomerHib = customerDAO.findByCUname(UNAME);
			
			if(!listCustomerHib.isEmpty()) {
				
				org.bench4Q.hibernate.Customer customerHib = (org.bench4Q.hibernate.Customer) listCustomerHib
						.get(0);

				AddressDAO addressDAO = new AddressDAO();
				CountryDAO countryDAO = new CountryDAO();

				org.bench4Q.hibernate.Address addressHib = addressDAO
						.findById(customerHib.getCAddrId());
				org.bench4Q.hibernate.Country countryHib = countryDAO
						.findById(addressHib.getAddrCoId());
				
				cust = new Customer(customerHib, addressHib, countryHib);
				
				tx.commit();

			} else {
				tx.rollback();
				System.err.println("ERROR: NULL returned in getCustomer!");
				return null;
			}

			// Results
//			if (rs.next())
//				cust = new Customer(rs);
//			else {
//				System.err.println("ERROR: NULL returned in getCustomer!");
//				return null;
//			}
//			con.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(statement);
//			closeConnection(con);
			if(session != null) {
				session.close();
			}
		}
		return cust;
	}
	
	public static Vector doSubjectSearch(String search_key) {
		Vector vec = new Vector();
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			// Prepare SQL
//			con = getConnection();
//			statement = con
//					.prepareStatement("SELECT * FROM item, author WHERE item.i_a_id = author.a_id AND item.i_subject = ? ORDER BY item.i_title FETCH FIRST 50 ROWS ONLY");
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from Item item where item.ISubject = ? order by item.ITitle ");
			query.setCacheable(true);
			query.setString(0, search_key);
			query.setFirstResult(0);
			query.setMaxResults(50);

			// Set parameter
//			statement.setString(1, search_key);
//			rs = statement.executeQuery();
			
			AuthorDAO authorDAO = new AuthorDAO();
			
			List list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Item item = (Item)iter.next();
				Author author = authorDAO.findById(item.getIAId());
				vec.addElement(new Book(item, author));
			}

			// Results
//			while (rs.next()) {
//				vec.addElement(new Book(rs));
//			}
//			con.commit();
			
			tx.commit();
		} catch (java.lang.Exception ex) {
			if(tx!=null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(statement);
//			closeConnection(con);
			if(session!= null){
				session.close();
			}
		}
		return vec;
	}

	public static Vector doTitleSearch(String search_key) {
		Vector vec = new Vector();
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		
		Session session = null;
		Transaction tx = null;
		
		try {
//			// Prepare SQL
//			con = getConnection();
//			statement = con
//					.prepareStatement("SELECT * FROM item, author WHERE item.i_a_id = author.a_id AND item.i_title LIKE ? ORDER BY item.i_title FETCH FIRST 50 ROWS ONLY");
//
//			// Set parameter
//			statement.setString(1, search_key + "%");
//			rs = statement.executeQuery();
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();

			Query query = session
					.createQuery("from Item item where item.ITitle LIKE ? order by item.ITitle ");
			query.setCacheable(true);
			query.setString(0, search_key + "%");
			query.setFirstResult(0);
			query.setMaxResults(50);

			AuthorDAO authorDAO = new AuthorDAO();
			
			List list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Item item = (Item)iter.next();
				Author author = authorDAO.findById(item.getIAId());
				vec.addElement(new Book(item, author));
			}
			
			tx.commit();

//			// Results
//			while (rs.next()) {
//				vec.addElement(new Book(rs));
//			}
//			con.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeResultSet(rs);
//			closeStmt(statement);
//			closeConnection(con);
		}
		return vec;
	}

	public static Vector doAuthorSearch(String search_key) {
		Vector vec = new Vector();
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		
		Session session = null;
		Transaction tx = null;
		
		try {
			// Prepare SQL
//			con = getConnection();
//			statement = con
//					.prepareStatement("SELECT * FROM author, item WHERE author.a_lname LIKE ? AND item.i_a_id = author.a_id ORDER BY item.i_title FETCH FIRST 50 ROWS ONLY");
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();

			Query query = session
					.createQuery("from Item item, Author author where item.IAId=author.AId and author.ALname LIKE ? order by item.ITitle ");
			query.setCacheable(true);
			query.setString(0, search_key + "%");
			query.setFirstResult(0);
			query.setMaxResults(50);
			
			List list = query.list();
			if (list != null) {
				int len = list.size();
				for (int i = 0; i < len; i++) {
					Object[] objArr = (Object[]) list.get(i);
					Item item = (Item) objArr[0];
					Author author = (Author) objArr[1];
					vec.addElement(new Book(item, author));
				}
			}

//			// Set parameter
//			statement.setString(1, search_key + "%");
//			rs = statement.executeQuery();
			
			tx.commit();

//			// Results
//			while (rs.next()) {
//				vec.addElement(new Book(rs));
//			}
//			con.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(statement);
//			closeConnection(con);
			if(session != null) {
				session.close();
			}
		}
		return vec;
	}

	public static Vector getNewProducts(String subject) {
		Vector vec = new Vector(); // Vector of Books
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			// Prepare SQL
//			con = getConnection();
//			statement = con.prepareStatement("SELECT i_id, i_title, a_fname, a_lname "
//					+ "FROM item, author " + "WHERE item.i_a_id = author.a_id "
//					+ "AND item.i_subject = ? " + "ORDER BY item.i_pub_date DESC,item.i_title "
//					+ "FETCH FIRST 50 ROWS ONLY");
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();

			Query query = session
					.createQuery("from Item item where item.ISubject = ? order by item.IPubDate DESC,item.ITitle ");
			query.setCacheable(true);
			query.setString(0, subject);
			query.setFirstResult(0);
			query.setMaxResults(50);			
			
			// Set parameter
//			statement.setString(1, subject);
//			rs = statement.executeQuery();
			
			AuthorDAO authorDAO = new AuthorDAO();
			
			List list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Item item = (Item)iter.next();
				Author author = authorDAO.findById(item.getIAId());
				vec.addElement(new ShortBook(item.getIId(), item.getITitle(), author.getAFname(), author.getALname()));
			}
			
			tx.commit();
			// Results
//			while (rs.next()) {
//				vec.addElement(new ShortBook(rs));
//			}
//			con.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeResultSet(rs);
//			closeStmt(statement);
//			closeConnection(con);
		}
		return vec;
	}

	public static Vector getBestSellers(String subject) {
		Vector vec = new Vector(); // Vector of Books
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			// Prepare SQL
//			con = getConnection();
//			// The following is the original, unoptimized best sellers query.
//			statement = con.prepareStatement("SELECT i_id, i_title, a_fname, a_lname "
//					+ "FROM item, author, order_line " + "WHERE item.i_id = order_line.ol_i_id "
//					+ "AND item.i_a_id = author.a_id "
//					+ "AND order_line.ol_o_id > (SELECT MAX(o_id)-3333 FROM orders)"
//					+ "AND item.i_subject = ? " + "GROUP BY i_id, i_title, a_fname, a_lname "
//					+ "ORDER BY SUM(ol_qty) DESC " + "FETCH FIRST 50 ROWS ONLY ");
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();

			String queryString = "select item.IId, item.ITitle, author.AFname, author.ALname "
					+ "from Item item, Author author, OrderLine order_line "
					+ "where item.IId = order_line.olIId "
					+ "and item.IAId = author.AId "
					+ "and order_line.id.olOId > (select max(orders.OId)-3333 from Orders orders)"
					+ "and item.ISubject ='"
					+ subject
					+ "' "
					+ "group by item.IId,item.ITitle,author.AFname,author.ALname "
					+ "order by sum(order_line.olQty) desc";
			Query queryObject = session.createQuery(queryString);
			queryObject.setCacheable(true);
			List list = queryObject.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Object[] arr = (Object[]) iter.next();
				vec.addElement(new ShortBook((Integer) arr[0], (String) arr[1],
						(String) arr[2], (String) arr[3]));
			}

			tx.commit();

			// Set parameter
//			statement.setString(1, subject);
//			rs = statement.executeQuery();
//
//			// Results
//			while (rs.next()) {
//				//by zxw tmp
//				//vec.addElement(new ShortBook(rs));
//			}
//			con.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeResultSet(rs);
//			closeStmt(statement);
//			closeConnection(con);
		}
		return vec;
	}

	public static void getRelated(int i_id, Vector i_id_vec, Vector i_thumbnail_vec) {
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			// Prepare SQL
//			con = getConnection();
//			statement = con
//					.prepareStatement("SELECT J.i_id,J.i_thumbnail from item I, item J where (I.i_related1 = J.i_id or I.i_related2 = J.i_id or I.i_related3 = J.i_id or I.i_related4 = J.i_id or I.i_related5 = J.i_id) and I.i_id = ?");
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();

			String queryString = "select J.IId,J.IThumbnail from Item I, Item J where (I.IRelated1 = J.IId or I.IRelated2 = J.IId or I.IRelated3 = J.IId or I.IRelated4 = J.IId or I.IRelated5 = J.IId) and I.IId ="
					+ i_id;
			Query queryObject = session.createQuery(queryString);
			queryObject.setCacheable(true);
			List list = queryObject.list();
			Iterator iter = list.iterator();

			i_id_vec.removeAllElements();
			i_thumbnail_vec.removeAllElements();

			while (iter.hasNext()) {
				Object[] arr = (Object[]) iter.next();
				i_id_vec.addElement(arr[0]);
				i_thumbnail_vec.addElement(arr[1]);
			}

			tx.commit();

			// Set parameter
//			statement.setInt(1, i_id);
//			rs = statement.executeQuery();
//
//			// Clear the vectors
//			i_id_vec.removeAllElements();
//			i_thumbnail_vec.removeAllElements();

			// Results
//			while (rs.next()) {
//				i_id_vec.addElement(new Integer(rs.getInt(1)));
//				i_thumbnail_vec.addElement(rs.getString(2));
//			}
//			con.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeResultSet(rs);
//			closeStmt(statement);
//			closeConnection(con);
		}
	}

	public static void adminUpdate(int i_id, double cost, String image, String thumbnail) {
//		Connection con = null;
//		PreparedStatement statement = null;
//		PreparedStatement related = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		try {
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			// Prepare SQL
//			con = getConnection();
//			statement = con
//					.prepareStatement("UPDATE item SET i_cost = ?, i_image = ?, i_thumbnail = ?, i_pub_date = CURRENT DATE WHERE i_id = ?");
			String updateString = "UPDATE Item item SET item.ICost = ?, item.IImage = ?, item.IThumbnail = ?, item.IPubDate = ? WHERE item.IId = ?";
			Query updateObject = session.createQuery(updateString);
//			updateObject.setCacheable(true);
			updateObject.setDouble(0, cost);
			updateObject.setString(1, image);
			updateObject.setString(2, thumbnail);
			updateObject.setDate(3, new java.util.Date());
			updateObject.setInteger(4, i_id);
			updateObject.executeUpdate();

			// Set parameter
//			statement.setDouble(1, cost);
//			statement.setString(2, image);
//			statement.setString(3, thumbnail);
//			statement.setInt(4, i_id);
//			statement.executeUpdate();
//			statement.close();
			String queryString = "SELECT order_line.olIId "
					+ "FROM Orders orders, OrderLine order_line "
					+ "WHERE orders.OId = order_line.id.olOId "
					+ "AND NOT (order_line.olIId = ?) "
					+ "AND orders.OCId IN (SELECT orders.OCId "
					+ "                      FROM Orders orders, OrderLine order_line "
					+ "                      WHERE orders.OId = order_line.id.olOId "
					+ "                      AND orders.OId > (SELECT MAX(orders.OId)-10000 FROM Orders orders)"
					+ "                      AND order_line.olIId = ?) "
					+ "GROUP BY order_line.olIId " + "ORDER BY SUM(order_line.olQty) DESC ";
			Query query = session.createQuery(queryString);
			query.setCacheable(true);
			query.setFirstResult(0);
			query.setMaxResults(5);
			query.setInteger(0, i_id);
			query.setInteger(1, i_id);

			// Set parameter
//			related.setInt(1, i_id);
//			related.setInt(2, i_id);
//			rs = related.executeQuery();

			int[] related_items = new int[5];
			// Results
			int counter = 0;
			int last = 0;
			
			List list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				last = (Integer)iter.next();
				related_items[counter] = last;
				counter++;
			}

			// This is the case for the situation where there are not 5 related
			// books.
			for (int i = counter; i < 5; i++) {
				last++;
				related_items[i] = last;
			}

			{
				// Prepare SQL
//				statement = con
//						.prepareStatement("UPDATE item SET i_related1 = ?, i_related2 = ?, i_related3 = ?, i_related4 = ?, i_related5 = ? WHERE i_id = ?");
				String updateString1 = "UPDATE Item item SET IRelated1 = ?, IRelated2 = ?, IRelated3 = ?, IRelated4 = ?, IRelated5 = ? WHERE IId = ?";
				Query updateObject1 = session.createQuery(updateString1);
//				updateObject1.setCacheable(true);
				updateObject1.setInteger(0, related_items[0]);
				updateObject1.setInteger(1, related_items[1]);
				updateObject1.setInteger(2, related_items[2]);
				updateObject1.setInteger(3, related_items[3]);
				updateObject1.setInteger(4, related_items[4]);
				updateObject1.setInteger(5, i_id);
				updateObject1.executeUpdate();


				// Set parameter
//				statement.setInt(1, related_items[0]);
//				statement.setInt(2, related_items[1]);
//				statement.setInt(3, related_items[2]);
//				statement.setInt(4, related_items[3]);
//				statement.setInt(5, related_items[4]);
//				statement.setInt(6, i_id);
//				statement.executeUpdate();
			}
//			con.commit();
			tx.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(statement);
//			closeStmt(related);
//			closeConnection(con);
			if(session != null) {
				session.close();
			}
		}
	}

	public static String GetUserName(int C_ID) {
		String u_name = null;
//		Connection con = null;
//		PreparedStatement get_user_name = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			// Prepare SQL
//			con = getConnection();
//			get_user_name = con.prepareStatement("SELECT c_uname FROM customer WHERE c_id = ?");
			String queryString = "SELECT customer.CUname FROM Customer customer WHERE customer.CId = ?";

			// Set parameter
//			get_user_name.setInt(1, C_ID);
//			rs = get_user_name.executeQuery();
			Query query = session.createQuery(queryString);
			query.setCacheable(true);
			query.setInteger(0, C_ID);

			// Results
			List list  =query.list();
			
//			rs.next();
//			u_name = rs.getString("c_uname");
			u_name=(String)list.get(0);
//			con.commit();
			tx.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(get_user_name);
//			closeConnection(con);
			if(session != null) {
				session.close();
			}
		}
		return u_name;
	}

	public static String GetPassword(String C_UNAME) {
		String passwd = null;
//		Connection con = null;
//		PreparedStatement get_passwd = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
//			con = getConnection();
//			get_passwd = con.prepareStatement("SELECT c_passwd FROM customer WHERE c_uname = ?");
			String queryString = "SELECT customer.CPasswd FROM Customer customer WHERE customer.CUname = ?";
			Query query = session.createQuery(queryString);
			query.setCacheable(true);
			query.setString(0, C_UNAME);

			// Set parameter
//			get_passwd.setString(1, C_UNAME);
//			rs = get_passwd.executeQuery();

			// Results
//			rs.next();
			List list = query.list();
			
//			passwd = rs.getString("c_passwd");
			passwd = (String)list.get(0);
//			con.commit();
			tx.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeResultSet(rs);
//			closeStmt(get_passwd);
//			closeConnection(con);
		}
		return passwd;
	}

	// This function gets the value of I_RELATED1 for the row of
	// the item table corresponding to I_ID
	
	private static int getRelated1(Session session, int I_ID) throws Exception {
		int related1 = -1;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		
		try {
//			statement = con.prepareStatement("SELECT i_related1 FROM item where i_id = ?");
//			statement.setInt(1, I_ID);
//			rs = statement.executeQuery();
//			if (rs.next()) {
//				related1 = rs.getInt(1);// Is 1 the correct index?
//			} else
//				related1 = 10;

			String queryString = "SELECT item.IRelated1 FROM Item item where item.IId = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setCacheable(true);
			queryObject.setInteger(0, I_ID);
			List list = queryObject.list();
			if (list != null && !list.isEmpty()) {
				related1 = (Integer) list.get(0);
			} else {
				related1 = 10;
			}

		} catch (java.lang.Exception ex) {
			System.out.println("I_ID is " + I_ID);
//			ex.printStackTrace();
			throw ex;
		} finally {
//			closeResultSet(rs);
//			closeStmt(statement);
		}
		return related1;
	}

	public static Order GetMostRecentOrder(String c_uname, Vector order_lines) {
//		Connection con = null;
//		PreparedStatement get_most_recent_order_id = null;
//		PreparedStatement get_order = null;
//		PreparedStatement get_order_lines = null;
//		ResultSet rs = null;
//		ResultSet rs2 = null;
//		ResultSet rs3 = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			order_lines.removeAllElements();
			int order_id;
			Order order;

			// Prepare SQL
//			con = getConnection();
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();

			// *** Get the o_id of the most recent order for this user
			String str_get_most_recent_order_id = "SELECT orders.OId "
					+ "FROM Customer customer, Orders orders " + "WHERE customer.CId = orders.OCId "
					+ "AND customer.CUname = ? " + "ORDER BY orders.ODate, orders.OId DESC ";
			Query queryGetMostRecentOrderId = session.createQuery(str_get_most_recent_order_id);
			queryGetMostRecentOrderId.setCacheable(true);
			queryGetMostRecentOrderId.setFirstResult(0);
			queryGetMostRecentOrderId.setMaxResults(1);
			queryGetMostRecentOrderId.setString(0, c_uname);
			
			List listMostRecentOrderId = queryGetMostRecentOrderId.list();

//			// Set parameter
//			get_most_recent_order_id.setString(1, c_uname);
//			rs = get_most_recent_order_id.executeQuery();

			if (listMostRecentOrderId!=null&&!listMostRecentOrderId.isEmpty()) {
//				order_id = rs.getInt("o_id");
//				Object[] arrMostRecentOrderId=(Object[])listMostRecentOrderId.get(0);
				order_id = (Integer)listMostRecentOrderId.get(0);
			} else {
				tx.commit();
				return null;
			}

			// *** Get the order info for this o_id
//			String str_get_order = "SELECT orders.*, customer.*, "
//					+ "  cc_xacts.cx_type, " + "  ship.addr_street1 AS ship_addr_street1, "
//					+ "  ship.addr_street2 AS ship_addr_street2, "
//					+ "  ship.addr_state AS ship_addr_state, "
//					+ "  ship.addr_zip AS ship_addr_zip, " + "  ship_co.co_name AS ship_co_name, "
//					+ "  bill.addr_street1 AS bill_addr_street1, "
//					+ "  bill.addr_street2 AS bill_addr_street2, "
//					+ "  bill.addr_state AS bill_addr_state, "
//					+ "  bill.addr_zip AS bill_addr_zip, " + "  bill_co.co_name AS bill_co_name "
//					+ "FROM customer, orders, cc_xacts," + "  address AS ship, "
//					+ "  country AS ship_co, " + "  address AS bill,  " + "  country AS bill_co "
//					+ "WHERE orders.o_id = ? " + "  AND cx_o_id = orders.o_id "
//					+ "  AND customer.c_id = orders.o_c_id "
//					+ "  AND orders.o_bill_addr_id = bill.addr_id "
//					+ "  AND bill.addr_co_id = bill_co.co_id "
//					+ "  AND orders.o_ship_addr_id = ship.addr_id "
//					+ "  AND ship.addr_co_id = ship_co.co_id "
//					+ "  AND orders.o_c_id = customer.c_id";
			
			String str_get_order = "SELECT orders, customer, "
				+ "  cc_xacts.cxType, " + "  ship, "
				+ "  ship_co, "
				+ "  bill, "
				+ "  bill_co "
				+ "FROM Customer customer, Orders orders, CcXacts cc_xacts," + "  Address ship, "
				+ "  Country ship_co, " + "  Address bill,  " + "  Country bill_co "
				+ "WHERE orders.OId = ? " + "  AND cc_xacts.cxOId = orders.OId "
				+ "  AND customer.CId = orders.OCId "
				+ "  AND orders.OBillAddrId = bill.addrId "
				+ "  AND bill.addrCoId = bill_co.coId "
				+ "  AND orders.OShipAddrId = ship.addrId "
				+ "  AND ship.addrCoId = ship_co.coId "
				+ "  AND orders.OCId = customer.CId";
			Query queryGetOrder=session.createQuery(str_get_order);
			queryGetOrder.setCacheable(true);
			queryGetOrder.setInteger(0, order_id);

			// Set parameter
//			get_order.setInt(1, order_id);
//			rs2 = get_order.executeQuery();
			
			List listGetOrder = queryGetOrder.list();

			// Results
//			if (!rs2.next()) {
//				// FIXME - This case is due to an error due to a database
//				// population error
//				con.commit();
//				return null;
//			}
			
			if(listGetOrder==null||listGetOrder.isEmpty()){
				tx.commit();
				return null;
			}
			
			Object[] arrGetOrder = (Object[])listGetOrder.get(0);
			order = new Order((org.bench4Q.hibernate.Orders) arrGetOrder[0],
					(org.bench4Q.hibernate.Customer) arrGetOrder[1],
					(String) arrGetOrder[2],
					(org.bench4Q.hibernate.Address) arrGetOrder[3],
					(org.bench4Q.hibernate.Country) arrGetOrder[4],
					(org.bench4Q.hibernate.Address) arrGetOrder[5],
					(org.bench4Q.hibernate.Country) arrGetOrder[6]);

			// *** Get the order_lines for this o_id
//			get_order_lines = con.prepareStatement("SELECT * "
//					+ "FROM order_line, item " + "WHERE ol_o_id = ? "
//					+ "AND ol_i_id = i_id");
			String str_get_order_lines = "SELECT order_line, item " + "FROM OrderLine order_line, Item item "
					+ "WHERE order_line.id.olOId = ? " + "AND order_line.olIId = item.IId";
			Query queryGetOrderLines =session.createQuery(str_get_order_lines);
			queryGetOrderLines.setCacheable(true);
			queryGetOrderLines.setInteger(0, order_id);

			// Set parameter
//			get_order_lines.setInt(1, order_id);
//			rs3 = get_order_lines.executeQuery();
			
			List listGetOrderLines = queryGetOrderLines.list();

			// Results
//			while (rs3.next()) {
//				order_lines.addElement(new OrderLine(rs3));
//			}
			
			if (listGetOrderLines != null) {
				Iterator iterGetOrderLines = listGetOrderLines.iterator();
				while (iterGetOrderLines.hasNext()) {
					Object[] arrGetOrderLines = (Object[]) iterGetOrderLines
							.next();
					order_lines
							.addElement(new OrderLine(
									(org.bench4Q.hibernate.OrderLine) arrGetOrderLines[0],
									(org.bench4Q.hibernate.Item) arrGetOrderLines[1]));
				}
			}

//			con.commit();
			tx.commit();
			return order;
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeResultSet(rs);
//			closeResultSet(rs2);
//			closeResultSet(rs3);
//			closeStmt(get_most_recent_order_id);
//			closeStmt(get_order);
//			closeStmt(get_order_lines);
//			closeConnection(con);
		}
		return null;
	}

	// ********************** Shopping Cart code below *************************

	// Called from: TPCW_shopping_cart_interaction
	public static int createEmptyCart() {
//		Connection con = null;
//		Statement insert_cart = null;
//		ResultSet rs = null;
		int SHOPPING_ID = 0;
		Session session = null;
		Transaction tx = null;
		try {
//			con = getConnection();
//			insert_cart = null;
//			rs = null;
//			insert_cart = con.createStatement();
//			insert_cart.executeUpdate(
//					"INSERT INTO shopping_cart (sc_time) VALUES (CURRENT TIMESTAMP )",
//					Statement.RETURN_GENERATED_KEYS);
//			rs = insert_cart.getGeneratedKeys();
//			if (rs.next()) {
//				SHOPPING_ID = rs.getInt(1);
//			}
//			// System.out.println(SHOPPING_ID);
//			con.commit();
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
						
			Timestamp timeStamp = new Timestamp(new java.util.Date().getTime());
			ShoppingCart newSC = new ShoppingCart((Integer)null, timeStamp);
			session.save(newSC);
			SHOPPING_ID = newSC.getScId();
			
			tx.commit();
			
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(insert_cart);
//			closeConnection(con);
			if(session != null) {
				session.close();
			}
		}
		return SHOPPING_ID;
	}

	
	public static Cart doCart(int SHOPPING_ID, Integer I_ID, Vector ids, Vector quantities) {
		Cart cart = null;
//		Connection con = null;
		Session session = null;
		Transaction tx = null;
		
		try {
//			con = getConnection();
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();

			if (I_ID != null) {
				addItem(session, SHOPPING_ID, I_ID.intValue());
			}
			refreshCart(session, SHOPPING_ID, ids, quantities);
			addRandomItemToCartIfNecessary(session, SHOPPING_ID);
			resetCartTime(session, SHOPPING_ID);
			cart = Database.innerGetCart(session, SHOPPING_ID, 0.0);

			// Close connection
//			con.commit();
			tx.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeConnection(con);
		}
		return cart;
	}

	// This function finds the shopping cart item associated with SHOPPING_ID
	// and I_ID. If the item does not already exist, we create one with QTY=1,
	// otherwise we increment the quantity.

	private static void addItem(Session session, int SHOPPING_ID, int I_ID) throws Exception {
//		PreparedStatement find_entry = null;
//		ResultSet rs = null;
		
		try {
			// Prepare SQL
//			find_entry = con
//					.prepareStatement("SELECT scl_qty FROM shopping_cart_line WHERE scl_sc_id = ? AND scl_i_id = ?");
			String queryString = "SELECT shopping_cart_line.sclQty FROM ShoppingCartLine shopping_cart_line WHERE shopping_cart_line.id.sclScId = ? AND shopping_cart_line.id.sclIId = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setCacheable(true);
			queryObject.setInteger(0, SHOPPING_ID);
			queryObject.setInteger(1, I_ID);
			List list = queryObject.list();
			

			// Set parameter
//			find_entry.setInt(1, SHOPPING_ID);
//			find_entry.setInt(2, I_ID);
//			rs = find_entry.executeQuery();
			
			if(list!=null && !list.isEmpty()) {
				int currqty=(Integer)list.get(0);
				currqty += 1;
				String updateString = "UPDATE ShoppingCartLine shopping_cart_line SET shopping_cart_line.sclQty = ? WHERE shopping_cart_line.id.sclScId = ? AND shopping_cart_line.id.sclIId = ?";
				Query updateObject = session.createQuery(updateString);
//				updateObject.setCacheable(true);
				updateObject.setInteger(0, currqty);
				updateObject.setInteger(1, SHOPPING_ID);
				updateObject.setInteger(2, I_ID);
				updateObject.executeUpdate();
			} else {
				ShoppingCartLineId sclId = new ShoppingCartLineId(SHOPPING_ID,I_ID);
				ShoppingCartLine sclObj = new ShoppingCartLine(sclId,1);
				session.save(sclObj);
			}
			
			// Results
//			if (rs.next()) {
//				// The shopping cart id, item pair were already in the table
//				int currqty = rs.getInt("scl_qty");
//				currqty += 1;
//				PreparedStatement update_qty = con
//						.prepareStatement("UPDATE shopping_cart_line SET scl_qty = ? WHERE scl_sc_id = ? AND scl_i_id = ?");
//				update_qty.setInt(1, currqty);
//				update_qty.setInt(2, SHOPPING_ID);
//				update_qty.setInt(3, I_ID);
//				update_qty.executeUpdate();
//				update_qty.close();
//			} else {// We need to add a new row to the table.
//
//				// Stick the item info in a new shopping_cart_line
//				PreparedStatement put_line = con
//						.prepareStatement("INSERT into shopping_cart_line (scl_sc_id, scl_qty, scl_i_id) VALUES (?,?,?)");
//				put_line.setInt(1, SHOPPING_ID);
//				put_line.setInt(2, 1);
//				put_line.setInt(3, I_ID);
//				put_line.executeUpdate();
//				put_line.close();
//			}

		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(find_entry);
		}
	}

	private static void refreshCart(Session session, int SHOPPING_ID, Vector ids, Vector quantities) throws Exception {
//		PreparedStatement statement = null;
		int i;
		try {
			
			for (i = 0; i < ids.size(); i++) {
				String I_IDstr = (String) ids.elementAt(i);
				String QTYstr = (String) quantities.elementAt(i);
				int I_ID = Integer.parseInt(I_IDstr);
				int QTY = Integer.parseInt(QTYstr);

				if (QTY == 0) { // We need to remove the item from the cart
//					statement = con
//							.prepareStatement("DELETE FROM shopping_cart_line WHERE scl_sc_id = ? AND scl_i_id = ?");
//					statement.setInt(1, SHOPPING_ID);
//					statement.setInt(2, I_ID);
//					statement.executeUpdate();
//					con.commit();
					
					Query deleteObject = session
							.createQuery("DELETE FROM ShoppingCartLine shopping_cart_line WHERE shopping_cart_line.id.sclScId = ? AND shopping_cart_line.id.sclIId = ?");
//					deleteObject.setCacheable(true);
					deleteObject.setInteger(0, SHOPPING_ID);
					deleteObject.setInteger(1, I_ID);
					deleteObject.executeUpdate();
				} else { // we update the quantity
//					statement = con
//							.prepareStatement("UPDATE shopping_cart_line SET scl_qty = ? WHERE scl_sc_id = ? AND scl_i_id = ?");
//					statement.setInt(1, QTY);
//					statement.setInt(2, SHOPPING_ID);
//					statement.setInt(3, I_ID);
//					statement.executeUpdate();
//					con.commit();
					
					Query updateObject = session
							.createQuery("UPDATE ShoppingCartLine shopping_cart_line SET shopping_cart_line.sclQty = ? WHERE shopping_cart_line.id.sclScId = ? AND shopping_cart_line.id.sclIId = ?");
//					updateObject.setCacheable(true);
					updateObject.setInteger(0, QTY);
					updateObject.setInteger(1, SHOPPING_ID);
					updateObject.setInteger(2, I_ID);
					updateObject.executeUpdate();
				}
			}
			
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeStmt(statement);
		}
	}

	private static void addRandomItemToCartIfNecessary(Session session, int SHOPPING_ID) throws Exception {
		// check and see if the cart is empty. If it's not, we do
		// nothing.
		int related_item = 0;
//		PreparedStatement get_cart = null;
//		ResultSet rs = null;

		try {

			// Check to see if the cart is empty
//			get_cart = con
//					.prepareStatement("SELECT COUNT(*) from shopping_cart_line where scl_sc_id = ?");
//			get_cart.setInt(1, SHOPPING_ID);
//			rs = get_cart.executeQuery();
//			rs.next();
//			if (rs.getInt(1) == 0) {
//				// Cart is empty
//				int rand_id = Util.getRandomI_ID();
//				related_item = getRelated1(rand_id);
//				addItem(con, SHOPPING_ID, related_item);
//			}
			
			String queryString = "SELECT COUNT(*) from ShoppingCartLine shopping_cart_line where shopping_cart_line.id.sclScId = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setCacheable(true);
			queryObject.setInteger(0, SHOPPING_ID);
			List list = queryObject.list();
			long countResult = (Long)list.get(0);;
			if(countResult==0){
				int rand_id = Util.getRandomI_ID();
				related_item = getRelated1(session, rand_id);
				addItem(session, SHOPPING_ID, related_item);
			}

		} catch (java.lang.Exception ex) {
//			ex.printStackTrace();
			System.out.println("Adding entry to shopping cart failed: shopping id = " + SHOPPING_ID
					+ " related_item = " + related_item);
			throw ex;
		} finally {
//			closeResultSet(rs);
//			closeStmt(get_cart);
		}
	}

	// Only called from this class
	private static void resetCartTime(Session session, int SHOPPING_ID) throws Exception {
//		PreparedStatement statement = null;
		try {
//			statement = con
//					.prepareStatement("UPDATE shopping_cart SET sc_time = CURRENT TIMESTAMP WHERE sc_id = ?");
//			// Set parameter
//			statement.setInt(1, SHOPPING_ID);
//			statement.executeUpdate();
//			con.commit();
			
			Query updateObject = session
					.createQuery("UPDATE ShoppingCart shopping_cart SET shopping_cart.scTime = ? WHERE shopping_cart.scId = ?");
//			updateObject.setCacheable(true);
			updateObject.setTimestamp(0, new java.util.Date());
			updateObject.setInteger(1, SHOPPING_ID);
			updateObject.executeUpdate();
			
		} catch (java.lang.Exception ex) {
//			ex.printStackTrace();
			throw ex;
		} finally {
//			closeStmt(statement);
		}
	}

	public static Cart getCart(int SHOPPING_ID, double c_discount) {
		Cart mycart = null;
//		Connection con = null;
		Session session = null;
		Transaction tx = null;
		try {
//			con = getConnection();
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
						
			mycart = innerGetCart(session, SHOPPING_ID, c_discount);
//			con.commit();
			tx.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeConnection(con);
			if(session != null) {
				session.close();
			}
		}
		return mycart;
	}

	// time .05s
	private static Cart innerGetCart(Session session, int SHOPPING_ID, double c_discount) throws Exception {
		Cart mycart = null;
//		PreparedStatement get_cart = null;
//		ResultSet rs = null;
		try {
			Query query = session.createQuery("SELECT shopping_cart_line, item " + "FROM ShoppingCartLine shopping_cart_line, Item item "
					+ "WHERE shopping_cart_line.id.sclIId = item.IId AND shopping_cart_line.id.sclScId = ?");
//			get_cart.setInt(1, SHOPPING_ID);
			query.setCacheable(true);
			query.setInteger(0, SHOPPING_ID);
			
//			rs = get_cart.executeQuery();
			
			List list = query.list();
			if(list==null) {
				list = new ArrayList();
			}
			mycart = new Cart(list, c_discount);
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(get_cart);
		}
		return mycart;
	}

	// ************** Customer / Order code below *************************

	// This should probably return an error code if the customer
	// doesn't exist, but ...
	public static void refreshSession(int C_ID) {
//		Connection con = null;
//		PreparedStatement updateLogin = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			// Prepare SQL
//			con = getConnection();
//			updateLogin = con
//					.prepareStatement("UPDATE customer SET c_login = CURRENT TIMESTAMP, c_expiration = CURRENT TIMESTAMP + 2 HOURS WHERE c_id = ?");

			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			Query query = session
					.createQuery("update Customer customer set customer.CLogin=?, customer.CExpiration=? where customer.CId=?");
			
//			query.setCacheable(true);
			
			java.util.Date now = new java.util.Date();
			java.util.Calendar cal=java.util.Calendar.getInstance();   
			cal.setTime(now);   
			cal.add(java.util.Calendar.HOUR_OF_DAY,2);
					
			query.setTimestamp(0, now);
			query.setTimestamp(1, cal.getTime());
			query.setInteger(2, C_ID);
			
			query.executeUpdate();
			
			tx.commit();
			
			// Set parameter
//			updateLogin.setInt(1, C_ID);
//			updateLogin.executeUpdate();
//			con.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeStmt(updateLogin);
//			closeConnection(con);
		}
	}

	public static Customer createNewCustomer(Customer cust) {
//		Connection con = null;
//		PreparedStatement insert_customer_row = null;
//		ResultSet rs = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			// Get largest customer ID already in use.
//			con = getConnection();
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();

			cust.c_discount = (int) (java.lang.Math.random() * 51);
			cust.c_balance = 0.0;
			cust.c_ytd_pmt = 0.0;
			// FIXME - Use SQL CURRENT_TIME to do this
			cust.c_last_visit = new Date(System.currentTimeMillis());
			cust.c_since = new Date(System.currentTimeMillis());
			cust.c_login = new Date(System.currentTimeMillis());
			cust.c_expiration = new Date(System.currentTimeMillis() + 7200000);// milliseconds
			// in 2
			// hours
//			insert_customer_row = con
//					.prepareStatement(
//							"INSERT into customer (c_uname, c_passwd, c_fname, c_lname, c_addr_id, c_phone, c_email, c_since, c_last_login, c_login, c_expiration, c_discount, c_balance, c_ytd_pmt, c_birthdate, c_data) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
//							Statement.RETURN_GENERATED_KEYS);
//			insert_customer_row.setString(3, cust.c_fname);
//			insert_customer_row.setString(4, cust.c_lname);
//			insert_customer_row.setString(6, cust.c_phone);
//			insert_customer_row.setString(7, cust.c_email);
//			insert_customer_row.setDate(8, new java.sql.Date(cust.c_since.getTime()));
//			insert_customer_row.setDate(9, new java.sql.Date(cust.c_last_visit.getTime()));
//			insert_customer_row.setDate(10, new java.sql.Date(cust.c_login.getTime()));
//			insert_customer_row.setDate(11, new java.sql.Date(cust.c_expiration.getTime()));
//			insert_customer_row.setDouble(12, cust.c_discount);
//			insert_customer_row.setDouble(13, cust.c_balance);
//			insert_customer_row.setDouble(14, cust.c_ytd_pmt);
//			insert_customer_row.setDate(15, new java.sql.Date(cust.c_birthdate.getTime()));
//			insert_customer_row.setString(16, cust.c_data);
			
			cust.addr_id = enterAddress(session, cust.addr_street1, cust.addr_street2, cust.addr_city,
					cust.addr_state, cust.addr_zip, cust.co_name);

//			insert_customer_row.setString(1, cust.c_uname);
//			insert_customer_row.setString(2, cust.c_passwd);
//			insert_customer_row.setInt(5, cust.addr_id);
//			insert_customer_row.executeUpdate();
			
			org.bench4Q.hibernate.Customer customerHib = new org.bench4Q.hibernate.Customer();

			customerHib.setCUname(cust.c_uname);
			customerHib.setCPasswd(cust.c_passwd);
			customerHib.setCFname(cust.c_fname);
			customerHib.setCLname(cust.c_lname);
			customerHib.setCAddrId(cust.addr_id);
			customerHib.setCPhone(cust.c_phone);
			customerHib.setCEmail(cust.c_email);
			customerHib.setCSince(cust.c_since);
			customerHib.setCLastLogin(cust.c_last_visit);
			customerHib.setCLogin(new Timestamp(cust.c_login.getTime()));
			customerHib.setCExpiration(new Timestamp(cust.c_expiration.getTime()));
			customerHib.setCDiscount((float)cust.c_discount);
			customerHib.setCBalance(cust.c_balance);
			customerHib.setCYtdPmt(cust.c_ytd_pmt);
			customerHib.setCBirthdate(cust.c_birthdate);
			customerHib.setCData(cust.c_data);
			
			session.save(customerHib);
			
			
//			rs = insert_customer_row.getGeneratedKeys();
//			if (rs.next()) {
//				cust.c_id = rs.getInt(1);
//			}
			
			cust.c_id = customerHib.getCId();
			
			cust.c_uname = Util.DigSyl(cust.c_id, 0);
			cust.c_passwd = cust.c_uname.toLowerCase();
			
			customerHib.setCUname(cust.c_uname);
			customerHib.setCPasswd(cust.c_passwd);
			
//			PreparedStatement updateUnameANDPasswd = con
//					.prepareStatement("UPDATE customer SET c_uname = ?, c_passwd = ? WHERE c_id = ?");
//			updateUnameANDPasswd.setString(1, cust.c_uname);
//			updateUnameANDPasswd.setString(2, cust.c_passwd);
//			updateUnameANDPasswd.setLong(3, cust.c_id);
//			updateUnameANDPasswd.executeUpdate();
			
			session.update(customerHib);
			
			tx.commit();
//			con.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(insert_customer_row);
//			closeConnection(con);
			if(session != null) {
				session.close();
			}
		}
		return cust;
	}

	// BUY CONFIRM

	public static BuyConfirmResult doBuyConfirm(int shopping_id, int customer_id, String cc_type,
			long cc_number, String cc_name, Date cc_expiry, String shipping) {

		Session session = null;
		Transaction tx = null;

		BuyConfirmResult result = new BuyConfirmResult();
//		Connection con = null;

		try {
//			con = getConnection();
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
						
			double c_discount = getCDiscount(session, customer_id);
			result.cart = innerGetCart(session, shopping_id, c_discount);
			int ship_addr_id = getCAddr(session, customer_id);
			result.order_id = enterOrder(session, customer_id, result.cart, ship_addr_id, shipping,
					c_discount);
			enterCCXact(session, result.order_id, cc_type, cc_number, cc_name, cc_expiry,
					result.cart.SC_TOTAL, ship_addr_id);
			clearCart(session, shopping_id);
//			con.commit();
			tx.commit();
		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeConnection(con);
		}
		return result;
	}

	public static BuyConfirmResult doBuyConfirm(int shopping_id, int customer_id, String cc_type,
			long cc_number, String cc_name, Date cc_expiry, String shipping, String street_1,
			String street_2, String city, String state, String zip, String country) {
		Session session = null;
		Transaction tx = null;
		BuyConfirmResult result = new BuyConfirmResult();
//		Connection con = null;
		try {
//			con = getConnection();
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			double c_discount = getCDiscount(session, customer_id);
			result.cart = innerGetCart(session, shopping_id, c_discount);
			int ship_addr_id = enterAddress(session, street_1, street_2, city, state, zip, country);
			result.order_id = enterOrder(session, customer_id, result.cart, ship_addr_id, shipping,
					c_discount);
			enterCCXact(session, result.order_id, cc_type, cc_number, cc_name, cc_expiry,
					result.cart.SC_TOTAL, ship_addr_id);
			clearCart(session, shopping_id);
//			con.commit();
			tx.commit();

		} catch (java.lang.Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
//			closeConnection(con);
		}
		return result;
	}

	// DB query time: .05s
	private static double getCDiscount(Session session, int c_id) throws Exception {
		double c_discount = 0.0;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		try {
			// Prepare SQL
//			statement = con
//					.prepareStatement("SELECT c_discount FROM customer WHERE customer.c_id = ?");
			Query query = session
					.createQuery("SELECT customer.CDiscount FROM Customer customer WHERE customer.CId = ?");
			query.setCacheable(true);
			query.setInteger(0, c_id);
			
			// Set parameter
//			statement.setInt(1, c_id);
//			rs = statement.executeQuery();
			
			List list = query.list();
			// Results
//			rs.next();
//			c_discount = rs.getDouble(1);
			c_discount = (Float) list.get(0);

		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(statement);
		}
		return c_discount;
	}

	// DB time: .05s
	public static int getCAddrID(Session session, int c_id) throws Exception {
		int c_addr_id = 0;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		try {
			// Prepare SQL
//			statement = con
//					.prepareStatement("SELECT c_addr_id FROM customer WHERE customer.c_id = ?");

			Query query = session
					.createQuery("SELECT customer.CAddrId FROM Customer customer WHERE customer.CId = ?");
			query.setCacheable(true);
			query.setInteger(0, c_id);
			
			List list = query.list();
			
			c_addr_id = (Integer) list.get(0);

			// Set parameter
//			statement.setInt(1, c_id);
//			rs = statement.executeQuery();

			// Results
//			rs.next();
//			c_addr_id = rs.getInt(1);
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(statement);
		}
		return c_addr_id;
	}

	public static int getCAddr(Session session, int c_id) throws Exception {
		int c_addr_id = 0;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
		try {
			// Prepare SQL
//			statement = con
//					.prepareStatement("SELECT c_addr_id FROM customer WHERE customer.c_id = ?");
			Query query = session
					.createQuery("SELECT customer.CAddrId FROM Customer customer WHERE customer.CId = ?");
			query.setCacheable(true);
			query.setInteger(0, c_id);
			
			List list = query.list();
			
			c_addr_id = (Integer) list.get(0);

			// Set parameter
//			statement.setInt(1, c_id);
//			rs = statement.executeQuery();

//			// Results
//			rs.next();
//			c_addr_id = rs.getInt(1);
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(statement);
		}
		return c_addr_id;
	}

	private static void enterCCXact(Session session, int o_id, // Order id
			String cc_type, long cc_number, String cc_name, Date cc_expiry, double total, // Total
			// from
			// shopping
			// cart
			int ship_addr_id) throws Exception {
//		PreparedStatement statement = null;

		// Updates the CC_XACTS table
		if (cc_type.length() > 10)
			cc_type = cc_type.substring(0, 10);
		if (cc_name.length() > 30)
			cc_name = cc_name.substring(0, 30);

		try {
			// Prepare SQL
//			statement = con
//					.prepareStatement("INSERT into cc_xacts (cx_o_id, cx_type, cx_num, cx_name, cx_expire, cx_xact_amt, cx_xact_date, cx_co_id) "
//							+ "VALUES (?, ?, ?, ?, ?, ?, CURRENT DATE, (SELECT co_id FROM address, country WHERE addr_id = ? AND addr_co_id = co_id))");
//
//			// Set parameter
//			statement.setInt(1, o_id); // cx_o_id
//			statement.setString(2, cc_type); // cx_type
//			statement.setLong(3, cc_number); // cx_num
//			statement.setString(4, cc_name); // cx_name
//			statement.setDate(5, cc_expiry); // cx_expiry
//			statement.setDouble(6, total); // cx_xact_amount
//			statement.setInt(7, ship_addr_id); // ship_addr_id
//			statement.executeUpdate();
			
			Query queryObject = session
					.createQuery("SELECT country.coId FROM Address address, Country country WHERE address.addrId = ? AND address.addrCoId = country.coId");
			queryObject.setCacheable(true);
			queryObject.setInteger(0, ship_addr_id);
			List listAddrId = queryObject.list();

			CcXacts ccXacts = new CcXacts(o_id, cc_type, String
					.valueOf(cc_number), cc_name, cc_expiry, (String) null,
					total, new java.util.Date(), (Integer) listAddrId.get(0));
			
			session.save(ccXacts);
			
//			con.commit();
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeStmt(statement);
		}
	}

	private static void clearCart(Session session, int shopping_id) throws Exception {
		// Empties all the lines from the shopping_cart_line for the
		// shopping id. Does not remove the actually shopping cart
//		PreparedStatement statement = null;
		try {
			// Prepare SQL
//			statement = con.prepareStatement("DELETE FROM shopping_cart_line WHERE scl_sc_id = ?");
			Query delObject = session
					.createQuery("DELETE FROM ShoppingCartLine shopping_cart_line WHERE shopping_cart_line.id.sclScId = ?");
			delObject.setInteger(0, shopping_id);
			delObject.executeUpdate();

			// Set parameter
//			statement.setInt(1, shopping_id);
//			statement.executeUpdate();
			// by dch
//			con.commit();
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeStmt(statement);
		}
	}

	private static int enterAddress(Session session, // Do we need to do this as
			// part of a transaction?
			String street1, String street2, String city, String state, String zip, String country) throws Exception {
		// returns the address id of the specified address. Adds a
		// new address to the table if needed
		int addr_id = 0;
//		PreparedStatement get_co_id = null;
//		PreparedStatement match_address = null;
//		PreparedStatement insert_address_row = null;
//		ResultSet rs = null;
//		ResultSet rs2 = null;

		// Get the country ID from the country table matching this address.

		// Is it safe to assume that the country that we are looking
		// for will be there?
		try {
//			get_co_id = con.prepareStatement("SELECT co_id FROM country WHERE co_name = ?");
//			get_co_id.setString(1, country);
//			rs = get_co_id.executeQuery();
//			rs.next();
//			int addr_co_id = rs.getInt("co_id");
//			rs.close();
			
			CountryDAO countryDAO = new CountryDAO();
			List listCountry = countryDAO.findByCoName(country);
			Country countryObj = (Country)listCountry.get(0);
			int addr_co_id = countryObj.getCoId();
			
			

			// Get address id for this customer, possible insert row in
			// address table
			Query match_address = session.createQuery("SELECT address.addrId FROM Address address "
					+ "WHERE address.addrStreet1 = ? " + "AND address.addrStreet2 = ? " + "AND address.addrCity = ? "
					+ "AND address.addrState = ? " + "AND address.addrZip = ? " + "AND address.addrCoId = ?");
			match_address.setCacheable(true);
			match_address.setString(0, street1);
			match_address.setString(1, street2);
			match_address.setString(2, city);
			match_address.setString(3, state);
			match_address.setString(4, zip);
			match_address.setInteger(5, addr_co_id);
//			rs = match_address.executeQuery();
			List listMatchAddress = match_address.list();
			if(listMatchAddress==null||listMatchAddress.isEmpty()){
				org.bench4Q.hibernate.Address addrNew = new org.bench4Q.hibernate.Address(
						(Integer) null, street1, street2, city, state, zip,
						addr_co_id);
				session.save(addrNew);
				addr_id=addrNew.getAddrId();
			} else {
				addr_id = (Integer)listMatchAddress.get(0);
			}
			
//			if (!rs.next()) {// We didn't match an address in the addr table
//				insert_address_row = con.prepareStatement(
//						"INSERT into address (addr_street1, addr_street2, addr_city, addr_state, addr_zip, addr_co_id) "
//								+ "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//				insert_address_row.setString(1, street1);
//				insert_address_row.setString(2, street2);
//				insert_address_row.setString(3, city);
//				insert_address_row.setString(4, state);
//				insert_address_row.setString(5, zip);
//				insert_address_row.setInt(6, addr_co_id);
//				insert_address_row.executeUpdate();
//				rs2 = insert_address_row.getGeneratedKeys();
//				if (rs2.next()) {
//					addr_id = rs2.getInt(1);
//				}
//			} else { // We actually matched
//				addr_id = rs.getInt("addr_id");
//			}
		} catch (java.lang.Exception ex) {
//			ex.printStackTrace();
			throw ex;
		} finally {
//			closeResultSet(rs);
//			closeResultSet(rs2);
//			closeStmt(get_co_id);
//			closeStmt(match_address);
//			closeStmt(insert_address_row);
		}
		return addr_id;
	}

	private static int enterOrder(Session session, int customer_id, Cart cart, int ship_addr_id,
			String shipping, double c_discount) throws Exception {
		int o_id = 0;
//		PreparedStatement insert_row = null;
//		ResultSet rs = null;
		try {
			java.util.Date now = new java.util.Date();
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(now);
			cal.add(java.util.Calendar.DAY_OF_MONTH, Util.getRandom(7));
			
			java.util.Date shipDate = cal.getTime();
			
			Orders orders = new Orders();
			orders.setOCId(customer_id);
			orders.setODate(now);
			orders.setOSubTotal(cart.SC_SUB_TOTAL);
			orders.setOTax(8.25);
			orders.setOTotal(cart.SC_TOTAL);
			orders.setOShipType(shipping);
			orders.setOShipDate(shipDate);
			orders.setOBillAddrId(getCAddrID(session, customer_id));
			orders.setOShipAddrId(ship_addr_id);
			orders.setOStatus("Pending");
			
			session.save(orders);
			
//			insert_row = con
//					.prepareStatement(
//							"INSERT into orders (o_c_id, o_date, o_sub_total, o_tax, o_total, o_ship_type, o_ship_date, o_bill_addr_id, o_ship_addr_id, o_status) "
//									+ "VALUES ( ?, CURRENT DATE, ?, 8.25, ?, ?, CURRENT DATE + ? DAYS, ?, ?, 'Pending')",
//							Statement.RETURN_GENERATED_KEYS);
//			insert_row.setInt(1, customer_id);
//			insert_row.setDouble(2, cart.SC_SUB_TOTAL);
//			insert_row.setDouble(3, cart.SC_TOTAL);
//			insert_row.setString(4, shipping);
//			insert_row.setInt(5, Util.getRandom(7));
//			insert_row.setInt(6, getCAddrID(con, customer_id));
//			insert_row.setInt(7, ship_addr_id);
//
//			insert_row.executeUpdate();
//			rs = insert_row.getGeneratedKeys();
//			if (rs.next()) {
//				o_id = rs.getInt(1);
//			}
			o_id = orders.getOId();
			
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(insert_row);
		}

		Enumeration e = cart.lines.elements();
		int counter = 0;
		while (e.hasMoreElements()) {
			// - Creates one or more 'order_line' rows.
			CartLine cart_line = (CartLine) e.nextElement();
			addOrderLine(session, counter, o_id, cart_line.scl_i_id, cart_line.scl_qty, c_discount,
					Util.getRandomString(20, 100));
			counter++;

			// - Adjusts the stock for each item ordered
			int stock = getStock(session, cart_line.scl_i_id);
			if ((stock - cart_line.scl_qty) < 10) {
				setStock(session, cart_line.scl_i_id, stock - cart_line.scl_qty + 21);
			} else {
				setStock(session, cart_line.scl_i_id, stock - cart_line.scl_qty);
			}
		}
		return o_id;
	}

	private static void addOrderLine(Session session, int ol_id, int ol_o_id, int ol_i_id,
			int ol_qty, double ol_discount, String ol_comment) throws Exception {
		int success = 0;
//		PreparedStatement insert_row = null;
		try {
//			insert_row = con
//					.prepareStatement("INSERT into order_line (ol_id, ol_o_id, ol_i_id, ol_qty, ol_discount, ol_comments) "
//							+ "VALUES (?, ?, ?, ?, ?, ?)");
//
//			insert_row.setInt(1, ol_id);
//			insert_row.setInt(2, ol_o_id);
//			insert_row.setInt(3, ol_i_id);
//			insert_row.setInt(4, ol_qty);
//			insert_row.setDouble(5, ol_discount);
//			insert_row.setString(6, ol_comment);
//			insert_row.executeUpdate();
			
			org.bench4Q.hibernate.OrderLine ol = new org.bench4Q.hibernate.OrderLine();
			org.bench4Q.hibernate.OrderLineId olId = new org.bench4Q.hibernate.OrderLineId();
			
			olId.setOlId(ol_id);
			olId.setOlOId(ol_o_id);
			ol.setId(olId);
			ol.setOlComments(ol_comment);
			ol.setOlDiscount(ol_discount);
			ol.setOlIId(ol_i_id);
			ol.setOlQty(ol_qty);
			
			session.save(ol);
						
			// dch
			// con.commit();
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeStmt(insert_row);
		}
	}

	private static int getStock(Session session, int i_id) throws Exception {
		int stock = 0;
//		PreparedStatement get_stock = null;
//		ResultSet rs = null;
		try {
//			get_stock = con.prepareStatement("SELECT i_stock FROM item WHERE i_id = ?");
			Query query = session.createQuery("SELECT item.IStock FROM Item item WHERE item.IId = ?");
			query.setCacheable(true);
			query.setInteger(0, i_id);
			List list = query.list();
			stock = (Integer)list.get(0);
			// Set parameter
//			get_stock.setInt(1, i_id);
//			rs = get_stock.executeQuery();
//
//			// Results
//			rs.next();
//			stock = rs.getInt("i_stock");
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeResultSet(rs);
//			closeStmt(get_stock);
		}
		return stock;
	}

	private static void setStock(Session session, int i_id, int new_stock) throws Exception {
//		PreparedStatement update_row = null;
		try {
//			update_row = con.prepareStatement("UPDATE item SET i_stock = ? WHERE i_id = ?");
//			update_row.setInt(1, new_stock);
//			update_row.setInt(2, i_id);
//			update_row.executeUpdate();
			
			Query updateObject = session
					.createQuery("UPDATE Item item SET item.IStock = ? WHERE item.IId = ?");
//			updateObject.setCacheable(true);
			updateObject.setInteger(0, new_stock);
			updateObject.setInteger(1, i_id);
			updateObject.executeUpdate();
			
			// by dch
			// con.commit();
		} catch (java.lang.Exception ex) {
			throw ex;
//			ex.printStackTrace();
		} finally {
//			closeStmt(update_row);
		}
	}

//	public static void verifyDBConsistency() {
//		Connection con = null;
//		PreparedStatement get_ids = null;
//		ResultSet rs = null;
//		try {
//			con = getConnection();
//			int this_id;
//			int id_expected = 1;
//			// First verify customer table
//			get_ids = con.prepareStatement("SELECT c_id FROM customer");
//			rs = get_ids.executeQuery();
//			while (rs.next()) {
//				this_id = rs.getInt("c_id");
//				while (this_id != id_expected) {
//					System.out.println("Missing C_ID " + id_expected);
//					id_expected++;
//				}
//				id_expected++;
//			}
//
//			id_expected = 1;
//			// Verify the item table
//			get_ids = con.prepareStatement("SELECT i_id FROM item");
//			rs = get_ids.executeQuery();
//			while (rs.next()) {
//				this_id = rs.getInt("i_id");
//				while (this_id != id_expected) {
//					System.out.println("Missing I_ID " + id_expected);
//					id_expected++;
//				}
//				id_expected++;
//			}
//
//			id_expected = 1;
//			// Verify the address table
//			get_ids = con.prepareStatement("SELECT addr_id FROM address");
//			rs = get_ids.executeQuery();
//			while (rs.next()) {
//				this_id = rs.getInt("addr_id");
//				// System.out.println(this_cid+"\n");
//				while (this_id != id_expected) {
//					System.out.println("Missing ADDR_ID " + id_expected);
//					id_expected++;
//				}
//				id_expected++;
//			}
//
//			con.commit();
//		} catch (java.lang.Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			closeResultSet(rs);
//			closeStmt(get_ids);
//			closeConnection(con);
//		}
//	}

}
