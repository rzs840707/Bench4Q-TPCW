package org.bench4Q.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Orders entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.bench4Q.hibernate.Orders
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings(value={"rawtypes","deprecation"})
public class OrdersDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(OrdersDAO.class);
	// property constants
	public static final String _OCID = "OCId";
	public static final String _OSUB_TOTAL = "OSubTotal";
	public static final String _OTAX = "OTax";
	public static final String _OTOTAL = "OTotal";
	public static final String _OSHIP_TYPE = "OShipType";
	public static final String _OBILL_ADDR_ID = "OBillAddrId";
	public static final String _OSHIP_ADDR_ID = "OShipAddrId";
	public static final String _OSTATUS = "OStatus";

	public void save(Orders transientInstance) {
		log.debug("saving Orders instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Orders persistentInstance) {
		log.debug("deleting Orders instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Orders findById(java.lang.Integer id) {
		log.debug("getting Orders instance with id: " + id);
		try {
			Orders instance = (Orders) getSession().get(
					"org.bench4Q.hibernate.Orders", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Orders instance) {
		log.debug("finding Orders instance by example");
		try {
			List results = getSession().createCriteria(
					"org.bench4Q.hibernate.Orders").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Orders instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Orders as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOCId(Object OCId) {
		return findByProperty(_OCID, OCId);
	}

	public List findByOSubTotal(Object OSubTotal) {
		return findByProperty(_OSUB_TOTAL, OSubTotal);
	}

	public List findByOTax(Object OTax) {
		return findByProperty(_OTAX, OTax);
	}

	public List findByOTotal(Object OTotal) {
		return findByProperty(_OTOTAL, OTotal);
	}

	public List findByOShipType(Object OShipType) {
		return findByProperty(_OSHIP_TYPE, OShipType);
	}

	public List findByOBillAddrId(Object OBillAddrId) {
		return findByProperty(_OBILL_ADDR_ID, OBillAddrId);
	}

	public List findByOShipAddrId(Object OShipAddrId) {
		return findByProperty(_OSHIP_ADDR_ID, OShipAddrId);
	}

	public List findByOStatus(Object OStatus) {
		return findByProperty(_OSTATUS, OStatus);
	}

	public List findAll() {
		log.debug("finding all Orders instances");
		try {
			String queryString = "from Orders";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Orders merge(Orders detachedInstance) {
		log.debug("merging Orders instance");
		try {
			Orders result = (Orders) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Orders instance) {
		log.debug("attaching dirty Orders instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orders instance) {
		log.debug("attaching clean Orders instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}