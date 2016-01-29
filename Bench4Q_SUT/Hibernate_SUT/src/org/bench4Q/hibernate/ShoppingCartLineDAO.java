package org.bench4Q.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ShoppingCartLine entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.bench4Q.hibernate.ShoppingCartLine
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings(value={"rawtypes","deprecation"})
public class ShoppingCartLineDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ShoppingCartLineDAO.class);
	// property constants
	public static final String SCL_QTY = "sclQty";

	public void save(ShoppingCartLine transientInstance) {
		log.debug("saving ShoppingCartLine instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ShoppingCartLine persistentInstance) {
		log.debug("deleting ShoppingCartLine instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ShoppingCartLine findById(org.bench4Q.hibernate.ShoppingCartLineId id) {
		log.debug("getting ShoppingCartLine instance with id: " + id);
		try {
			ShoppingCartLine instance = (ShoppingCartLine) getSession().get(
					"org.bench4Q.hibernate.ShoppingCartLine", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ShoppingCartLine instance) {
		log.debug("finding ShoppingCartLine instance by example");
		try {
			List results = getSession().createCriteria(
					"org.bench4Q.hibernate.ShoppingCartLine").add(
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
		log.debug("finding ShoppingCartLine instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ShoppingCartLine as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySclQty(Object sclQty) {
		return findByProperty(SCL_QTY, sclQty);
	}

	public List findAll() {
		log.debug("finding all ShoppingCartLine instances");
		try {
			String queryString = "from ShoppingCartLine";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ShoppingCartLine merge(ShoppingCartLine detachedInstance) {
		log.debug("merging ShoppingCartLine instance");
		try {
			ShoppingCartLine result = (ShoppingCartLine) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ShoppingCartLine instance) {
		log.debug("attaching dirty ShoppingCartLine instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ShoppingCartLine instance) {
		log.debug("attaching clean ShoppingCartLine instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}