/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author plaul1
 */
@Entity
@Table(name="USER_ROLE")
public class UserRole implements Serializable {
  @ManyToMany(mappedBy = "roles")
  private List<StudyPointUser> studyPointUsers;
  
  
  private static final long serialVersionUID = 1L;
  @Id
  private String roleName ;

  public List<StudyPointUser> getStudyPointUsers() {
    return studyPointUsers;
  }
  
  public void addStudyPointUser(StudyPointUser user){
    studyPointUsers.add(user);
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
  
  
  
  
}
