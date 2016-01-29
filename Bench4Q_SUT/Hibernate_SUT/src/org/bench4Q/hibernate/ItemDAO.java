package org.bench4Q.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for Item
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.bench4Q.hibernate.Item
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings(value={"rawtypes","deprecation"})
public class ItemDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ItemDAO.class);
	// property constants
	public static final String _ITITLE = "ITitle";
	public static final String _IAID = "IAId";
	public static final String _IPUBLISHER = "IPublisher";
	public static final String _ISUBJECT = "ISubject";
	public static final String _IDESC = "IDesc";
	public static final String _IRELATED1 = "IRelated1";
	public static final String _IRELATED2 = "IRelated2";
	public static final String _IRELATED3 = "IRelated3";
	public static final String _IRELATED4 = "IRelated4";
	public static final String _IRELATED5 = "IRelated5";
	public static final String _ITHUMBNAIL = "IThumbnail";
	public static final String _IIMAGE = "IImage";
	public static final String _ISRP = "ISrp";
	public static final String _ICOST = "ICost";
	public static final String _ISTOCK = "IStock";
	public static final String _IISBN = "IIsbn";
	public static final String _IPAGE = "IPage";
	public static final String _IBACKING = "IBacking";
	public static final String _IDIMENSIONS = "IDimensions";

	public void save(Item transientInstance) {
		log.debug("saving Item instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Item persistentInstance) {
		log.debug("deleting Item instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Item findById(java.lang.Integer id) {
		log.debug("getting Item instance with id: " + id);
		try {
			Item instance = (Item) getSession().get(
					"org.bench4Q.hibernate.Item", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Item instance) {
		log.debug("finding Item instance by example");
		try {
			List results = getSession().createCriteria(
					"org.bench4Q.hibernate.Item").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Item instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Item as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByITitle(Object ITitle) {
		return findByProperty(_ITITLE, ITitle);
	}

	public List findByIAId(Object IAId) {
		return findByProperty(_IAID, IAId);
	}

	public List findByIPublisher(Object IPublisher) {
		return findByProperty(_IPUBLISHER, IPublisher);
	}

	public List findByISubject(Object ISubject) {
		return findByProperty(_ISUBJECT, ISubject);
	}

	public List findByIDesc(Object IDesc) {
		return findByProperty(_IDESC, IDesc);
	}

	public List findByIRelated1(Object IRelated1) {
		return findByProperty(_IRELATED1, IRelated1);
	}

	public List findByIRelated2(Object IRelated2) {
		return findByProperty(_IRELATED2, IRelated2);
	}

	public List findByIRelated3(Object IRelated3) {
		return findByProperty(_IRELATED3, IRelated3);
	}

	public List findByIRelated4(Object IRelated4) {
		return findByProperty(_IRELATED4, IRelated4);
	}

	public List findByIRelated5(Object IRelated5) {
		return findByProperty(_IRELATED5, IRelated5);
	}

	public List findByIThumbnail(Object IThumbnail) {
		return findByProperty(_ITHUMBNAIL, IThumbnail);
	}

	public List findByIImage(Object IImage) {
		return findByProperty(_IIMAGE, IImage);
	}

	public List findByISrp(Object ISrp) {
		return findByProperty(_ISRP, ISrp);
	}

	public List findByICost(Object ICost) {
		return findByProperty(_ICOST, ICost);
	}

	public List findByIStock(Object IStock) {
		return findByProperty(_ISTOCK, IStock);
	}

	public List findByIIsbn(Object IIsbn) {
		return findByProperty(_IISBN, IIsbn);
	}

	public List findByIPage(Object IPage) {
		return findByProperty(_IPAGE, IPage);
	}

	public List findByIBacking(Object IBacking) {
		return findByProperty(_IBACKING, IBacking);
	}

	public List findByIDimensions(Object IDimensions) {
		return findByProperty(_IDIMENSIONS, IDimensions);
	}

	public List findAll() {
		log.debug("finding all Item instances");
		try {
			String queryString = "from Item";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Item merge(Item detachedInstance) {
		log.debug("merging Item instance");
		try {
			Item result = (Item) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Item instance) {
		log.debug("attaching dirty Item instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Item instance) {
		log.debug("attaching clean Item instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}