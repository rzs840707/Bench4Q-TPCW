package org.bench4Q.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Customer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.bench4Q.hibernate.Customer
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings(value={"rawtypes","deprecation"})
public class CustomerDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CustomerDAO.class);
	// property constants
	public static final String _CUNAME = "CUname";
	public static final String _CPASSWD = "CPasswd";
	public static final String _CFNAME = "CFname";
	public static final String _CLNAME = "CLname";
	public static final String _CADDR_ID = "CAddrId";
	public static final String _CPHONE = "CPhone";
	public static final String _CEMAIL = "CEmail";
	public static final String _CDISCOUNT = "CDiscount";
	public static final String _CBALANCE = "CBalance";
	public static final String _CYTD_PMT = "CYtdPmt";
	public static final String _CDATA = "CData";

	public void save(Customer transientInstance) {
		log.debug("saving Customer instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Customer persistentInstance) {
		log.debug("deleting Customer instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Customer findById(java.lang.Integer id) {
		log.debug("getting Customer instance with id: " + id);
		try {
			Customer instance = (Customer) getSession().get(
					"org.bench4Q.hibernate.Customer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Customer instance) {
		log.debug("finding Customer instance by example");
		try {
			List results = getSession().createCriteria(
					"org.bench4Q.hibernate.Customer").add(
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
		log.debug("finding Customer instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Customer as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCUname(Object CUname) {
		return findByProperty(_CUNAME, CUname);
	}

	public List findByCPasswd(Object CPasswd) {
		return findByProperty(_CPASSWD, CPasswd);
	}

	public List findByCFname(Object CFname) {
		return findByProperty(_CFNAME, CFname);
	}

	public List findByCLname(Object CLname) {
		return findByProperty(_CLNAME, CLname);
	}

	public List findByCAddrId(Object CAddrId) {
		return findByProperty(_CADDR_ID, CAddrId);
	}

	public List findByCPhone(Object CPhone) {
		return findByProperty(_CPHONE, CPhone);
	}

	public List findByCEmail(Object CEmail) {
		return findByProperty(_CEMAIL, CEmail);
	}

	public List findByCDiscount(Object CDiscount) {
		return findByProperty(_CDISCOUNT, CDiscount);
	}

	public List findByCBalance(Object CBalance) {
		return findByProperty(_CBALANCE, CBalance);
	}

	public List findByCYtdPmt(Object CYtdPmt) {
		return findByProperty(_CYTD_PMT, CYtdPmt);
	}

	public List findByCData(Object CData) {
		return findByProperty(_CDATA, CData);
	}

	public List findAll() {
		log.debug("finding all Customer instances");
		try {
			String queryString = "from Customer";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Customer merge(Customer detachedInstance) {
		log.debug("merging Customer instance");
		try {
			Customer result = (Customer) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Customer instance) {
		log.debug("attaching dirty Customer instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Customer instance) {
		log.debug("attaching clean Customer instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}