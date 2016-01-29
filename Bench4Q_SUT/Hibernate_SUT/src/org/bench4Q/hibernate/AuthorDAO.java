package org.bench4Q.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Author entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.bench4Q.hibernate.Author
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings(value={"rawtypes","deprecation"})
public class AuthorDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AuthorDAO.class);
	// property constants
	public static final String _AFNAME = "AFname";
	public static final String _ALNAME = "ALname";
	public static final String _AMNAME = "AMname";
	public static final String _ABIO = "ABio";

	public void save(Author transientInstance) {
		log.debug("saving Author instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Author persistentInstance) {
		log.debug("deleting Author instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Author findById(java.lang.Integer id) {
		log.debug("getting Author instance with id: " + id);
		try {
			Author instance = (Author) getSession().get(
					"org.bench4Q.hibernate.Author", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Author instance) {
		log.debug("finding Author instance by example");
		try {
			List results = getSession().createCriteria(
					"org.bench4Q.hibernate.Author").add(
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
		log.debug("finding Author instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Author as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAFname(Object AFname) {
		return findByProperty(_AFNAME, AFname);
	}

	public List findByALname(Object ALname) {
		return findByProperty(_ALNAME, ALname);
	}

	public List findByAMname(Object AMname) {
		return findByProperty(_AMNAME, AMname);
	}

	public List findByABio(Object ABio) {
		return findByProperty(_ABIO, ABio);
	}

	public List findAll() {
		log.debug("finding all Author instances");
		try {
			String queryString = "from Author";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Author merge(Author detachedInstance) {
		log.debug("merging Author instance");
		try {
			Author result = (Author) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Author instance) {
		log.debug("attaching dirty Author instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Author instance) {
		log.debug("attaching clean Author instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}