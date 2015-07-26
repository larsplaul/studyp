/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.StudyPoint;
import entity.StudyPointUser;
import entity.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author plaul1
 */
public class StudyPointUserFacade implements Serializable {

  public StudyPointUserFacade(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(StudyPointUser studyPointUser) {
    if (studyPointUser.getStudyPoints() == null) {
      studyPointUser.setStudyPoints(new ArrayList<StudyPoint>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      List<StudyPoint> attachedStudyPoints = new ArrayList<StudyPoint>();
      for (StudyPoint studyPointsStudyPointToAttach : studyPointUser.getStudyPoints()) {
        studyPointsStudyPointToAttach = em.getReference(studyPointsStudyPointToAttach.getClass(), studyPointsStudyPointToAttach.getId());
        attachedStudyPoints.add(studyPointsStudyPointToAttach);
      }
      studyPointUser.setStudyPoints(attachedStudyPoints);
      em.persist(studyPointUser);
      for (StudyPoint studyPointsStudyPoint : studyPointUser.getStudyPoints()) {
        StudyPointUser oldStudyPointUserOfStudyPointsStudyPoint = studyPointsStudyPoint.getStudyPointUser();
        studyPointsStudyPoint.setStudyPointUser(studyPointUser);
        studyPointsStudyPoint = em.merge(studyPointsStudyPoint);
        if (oldStudyPointUserOfStudyPointsStudyPoint != null) {
          oldStudyPointUserOfStudyPointsStudyPoint.getStudyPoints().remove(studyPointsStudyPoint);
          oldStudyPointUserOfStudyPointsStudyPoint = em.merge(oldStudyPointUserOfStudyPointsStudyPoint);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(StudyPointUser studyPointUser) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      StudyPointUser persistentStudyPointUser = em.find(StudyPointUser.class, studyPointUser.getId());
      List<StudyPoint> studyPointsOld = persistentStudyPointUser.getStudyPoints();
      List<StudyPoint> studyPointsNew = studyPointUser.getStudyPoints();
      List<StudyPoint> attachedStudyPointsNew = new ArrayList<StudyPoint>();
      for (StudyPoint studyPointsNewStudyPointToAttach : studyPointsNew) {
        studyPointsNewStudyPointToAttach = em.getReference(studyPointsNewStudyPointToAttach.getClass(), studyPointsNewStudyPointToAttach.getId());
        attachedStudyPointsNew.add(studyPointsNewStudyPointToAttach);
      }
      studyPointsNew = attachedStudyPointsNew;
      studyPointUser.setStudyPoints(studyPointsNew);
      studyPointUser = em.merge(studyPointUser);
      for (StudyPoint studyPointsOldStudyPoint : studyPointsOld) {
        if (!studyPointsNew.contains(studyPointsOldStudyPoint)) {
          studyPointsOldStudyPoint.setStudyPointUser(null);
          studyPointsOldStudyPoint = em.merge(studyPointsOldStudyPoint);
        }
      }
      for (StudyPoint studyPointsNewStudyPoint : studyPointsNew) {
        if (!studyPointsOld.contains(studyPointsNewStudyPoint)) {
          StudyPointUser oldStudyPointUserOfStudyPointsNewStudyPoint = studyPointsNewStudyPoint.getStudyPointUser();
          studyPointsNewStudyPoint.setStudyPointUser(studyPointUser);
          studyPointsNewStudyPoint = em.merge(studyPointsNewStudyPoint);
          if (oldStudyPointUserOfStudyPointsNewStudyPoint != null && !oldStudyPointUserOfStudyPointsNewStudyPoint.equals(studyPointUser)) {
            oldStudyPointUserOfStudyPointsNewStudyPoint.getStudyPoints().remove(studyPointsNewStudyPoint);
            oldStudyPointUserOfStudyPointsNewStudyPoint = em.merge(oldStudyPointUserOfStudyPointsNewStudyPoint);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = studyPointUser.getId();
        if (findStudyPointUser(id) == null) {
          throw new NonexistentEntityException("The studyPointUser with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      StudyPointUser studyPointUser;
      try {
        studyPointUser = em.getReference(StudyPointUser.class, id);
        studyPointUser.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The studyPointUser with id " + id + " no longer exists.", enfe);
      }
      List<StudyPoint> studyPoints = studyPointUser.getStudyPoints();
      for (StudyPoint studyPointsStudyPoint : studyPoints) {
        studyPointsStudyPoint.setStudyPointUser(null);
        studyPointsStudyPoint = em.merge(studyPointsStudyPoint);
      }
      em.remove(studyPointUser);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<StudyPointUser> findStudyPointUserEntities() {
    return findStudyPointUserEntities(true, -1, -1);
  }

  public List<StudyPointUser> findStudyPointUserEntities(int maxResults, int firstResult) {
    return findStudyPointUserEntities(false, maxResults, firstResult);
  }

  private List<StudyPointUser> findStudyPointUserEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(StudyPointUser.class));
      Query q = em.createQuery(cq);
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public StudyPointUser findStudyPointUser(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(StudyPointUser.class, id);
    } finally {
      em.close();
    }
  }
 
  public StudyPointUser findStudyPointUser(String  userName) {
    EntityManager em = getEntityManager();
    try {
      Query query= em.createNamedQuery("StudyPointUser.findByUsername",StudyPointUser.class);
      query.setParameter("username", userName);
      StudyPointUser user = (StudyPointUser) query.getSingleResult();
      return user;
    } finally {
      em.close();
    }
  }
  
  public String authenticateUser(String  userName,String password) {
    EntityManager em = getEntityManager();
    try {
      Query query= em.createNamedQuery("StudyPointUser.findByUsername",StudyPointUser.class);
      query.setParameter("username", userName);
      StudyPointUser user = (StudyPointUser) query.getSingleResult();
      //Todo System is hardcode to allow only one role per user
      return user != null && user.getPassword().equals(password) ? user.getRoles().get(0).getRoleName() : null;
      //throw new SecurityException("Username and or password does not match any known entities");
    } finally {
      em.close();
    }
  }

  public int getStudyPointUserCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<StudyPointUser> rt = cq.from(StudyPointUser.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
  public static void main(String[] args) {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudyPointSystemPU");
    StudyPointUserFacade facade = new StudyPointUserFacade(emf);
    StudyPointUser user = facade.findStudyPointUser("test");
    System.out.println(user.getPassword());
    System.out.println(user.getRoles().size());
  }
  
}
