package org.bench4Q.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ShoppingCart entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.bench4Q.hibernate.ShoppingCart
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings(value={"rawtypes","deprecation"})
public class ShoppingCartDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ShoppingCartDAO.class);

	// property constants

	public void save(ShoppingCart transientInstance) {
		log.debug("saving ShoppingCart instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ShoppingCart persistentInstance) {
		log.debug("deleting ShoppingCart instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ShoppingCart findById(java.lang.Integer id) {
		log.debug("getting ShoppingCart instance with id: " + id);
		try {
			ShoppingCart instance = (ShoppingCart) getSession().get(
					"org.bench4Q.hibernate.ShoppingCart", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ShoppingCart instance) {
		log.debug("finding ShoppingCart instance by example");
		try {
			List results = getSession().createCriteria(
					"org.bench4Q.hibernate.ShoppingCart").add(
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
		log.debug("finding ShoppingCart instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ShoppingCart as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ShoppingCart instances");
		try {
			String queryString = "from ShoppingCart";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ShoppingCart merge(ShoppingCart detachedInstance) {
		log.debug("merging ShoppingCart instance");
		try {
			ShoppingCart result = (ShoppingCart) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ShoppingCart instance) {
		log.debug("attaching dirty ShoppingCart instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ShoppingCart instance) {
		log.debug("attaching clean ShoppingCart instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}