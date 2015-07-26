package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StudyPoint implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
  private Integer id;
  
  @ManyToOne
  private Task task;
  @ManyToOne
  private StudyPointUser studyPointUser;

  public StudyPoint(int score) {
    this.score = score;
  }

  public StudyPoint() {
  }
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  private int score;

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public StudyPointUser getStudyPointUser() {
    return studyPointUser;
  }

  public void setStudyPointUser(StudyPointUser studyPointUser) {
    this.studyPointUser = studyPointUser;
  }
  
}
