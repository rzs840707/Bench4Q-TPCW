package org.bench4Q.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * OrderLine entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.bench4Q.hibernate.OrderLine
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings(value={"rawtypes","deprecation"})
public class OrderLineDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(OrderLineDAO.class);
	// property constants
	public static final String OL_IID = "olIId";
	public static final String OL_QTY = "olQty";
	public static final String OL_DISCOUNT = "olDiscount";
	public static final String OL_COMMENTS = "olComments";

	public void save(OrderLine transientInstance) {
		log.debug("saving OrderLine instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OrderLine persistentInstance) {
		log.debug("deleting OrderLine instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OrderLine findById(org.bench4Q.hibernate.OrderLineId id) {
		log.debug("getting OrderLine instance with id: " + id);
		try {
			OrderLine instance = (OrderLine) getSession().get(
					"org.bench4Q.hibernate.OrderLine", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OrderLine instance) {
		log.debug("finding OrderLine instance by example");
		try {
			List results = getSession().createCriteria(
					"org.bench4Q.hibernate.OrderLine").add(
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
		log.debug("finding OrderLine instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from OrderLine as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOlIId(Object olIId) {
		return findByProperty(OL_IID, olIId);
	}

	public List findByOlQty(Object olQty) {
		return findByProperty(OL_QTY, olQty);
	}

	public List findByOlDiscount(Object olDiscount) {
		return findByProperty(OL_DISCOUNT, olDiscount);
	}

	public List findByOlComments(Object olComments) {
		return findByProperty(OL_COMMENTS, olComments);
	}

	public List findAll() {
		log.debug("finding all OrderLine instances");
		try {
			String queryString = "from OrderLine";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OrderLine merge(OrderLine detachedInstance) {
		log.debug("merging OrderLine instance");
		try {
			OrderLine result = (OrderLine) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OrderLine instance) {
		log.debug("attaching dirty OrderLine instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OrderLine instance) {
		log.debug("attaching clean OrderLine instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}