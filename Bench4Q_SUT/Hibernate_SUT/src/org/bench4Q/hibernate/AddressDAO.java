package org.bench4Q.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Address entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.bench4Q.hibernate.Address
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings(value={"rawtypes","deprecation"})
public class AddressDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AddressDAO.class);
	// property constants
	public static final String ADDR_STREET1 = "addrStreet1";
	public static final String ADDR_STREET2 = "addrStreet2";
	public static final String ADDR_CITY = "addrCity";
	public static final String ADDR_STATE = "addrState";
	public static final String ADDR_ZIP = "addrZip";
	public static final String ADDR_CO_ID = "addrCoId";

	public void save(Address transientInstance) {
		log.debug("saving Address instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Address persistentInstance) {
		log.debug("deleting Address instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Address findById(java.lang.Integer id) {
		log.debug("getting Address instance with id: " + id);
		try {
			Address instance = (Address) getSession().get(
					"org.bench4Q.hibernate.Address", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Address instance) {
		log.debug("finding Address instance by example");
		try {
			List results = getSession().createCriteria(
					"org.bench4Q.hibernate.Address").add(
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
		log.debug("finding Address instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Address as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAddrStreet1(Object addrStreet1) {
		return findByProperty(ADDR_STREET1, addrStreet1);
	}

	public List findByAddrStreet2(Object addrStreet2) {
		return findByProperty(ADDR_STREET2, addrStreet2);
	}

	public List findByAddrCity(Object addrCity) {
		return findByProperty(ADDR_CITY, addrCity);
	}

	public List findByAddrState(Object addrState) {
		return findByProperty(ADDR_STATE, addrState);
	}

	public List findByAddrZip(Object addrZip) {
		return findByProperty(ADDR_ZIP, addrZip);
	}

	public List findByAddrCoId(Object addrCoId) {
		return findByProperty(ADDR_CO_ID, addrCoId);
	}

	public List findAll() {
		log.debug("finding all Address instances");
		try {
			String queryString = "from Address";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Address merge(Address detachedInstance) {
		log.debug("merging Address instance");
		try {
			Address result = (Address) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Address instance) {
		log.debug("attaching dirty Address instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Address instance) {
		log.debug("attaching clean Address instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}