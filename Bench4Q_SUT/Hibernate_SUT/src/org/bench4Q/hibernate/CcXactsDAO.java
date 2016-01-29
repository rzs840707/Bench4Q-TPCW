package org.bench4Q.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CcXacts entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.bench4Q.hibernate.CcXacts
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings(value={"rawtypes","deprecation"})
public class CcXactsDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CcXactsDAO.class);
	// property constants
	public static final String CX_TYPE = "cxType";
	public static final String CX_NUM = "cxNum";
	public static final String CX_NAME = "cxName";
	public static final String CX_AUTH_ID = "cxAuthId";
	public static final String CX_XACT_AMT = "cxXactAmt";
	public static final String CX_CO_ID = "cxCoId";

	public void save(CcXacts transientInstance) {
		log.debug("saving CcXacts instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CcXacts persistentInstance) {
		log.debug("deleting CcXacts instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CcXacts findById(java.lang.Integer id) {
		log.debug("getting CcXacts instance with id: " + id);
		try {
			CcXacts instance = (CcXacts) getSession().get(
					"org.bench4Q.hibernate.CcXacts", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CcXacts instance) {
		log.debug("finding CcXacts instance by example");
		try {
			List results = getSession().createCriteria(
					"org.bench4Q.hibernate.CcXacts").add(
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
		log.debug("finding CcXacts instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CcXacts as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCxType(Object cxType) {
		return findByProperty(CX_TYPE, cxType);
	}

	public List findByCxNum(Object cxNum) {
		return findByProperty(CX_NUM, cxNum);
	}

	public List findByCxName(Object cxName) {
		return findByProperty(CX_NAME, cxName);
	}

	public List findByCxAuthId(Object cxAuthId) {
		return findByProperty(CX_AUTH_ID, cxAuthId);
	}

	public List findByCxXactAmt(Object cxXactAmt) {
		return findByProperty(CX_XACT_AMT, cxXactAmt);
	}

	public List findByCxCoId(Object cxCoId) {
		return findByProperty(CX_CO_ID, cxCoId);
	}

	public List findAll() {
		log.debug("finding all CcXacts instances");
		try {
			String queryString = "from CcXacts";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CcXacts merge(CcXacts detachedInstance) {
		log.debug("merging CcXacts instance");
		try {
			CcXacts result = (CcXacts) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CcXacts instance) {
		log.debug("attaching dirty CcXacts instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CcXacts instance) {
		log.debug("attaching clean CcXacts instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}