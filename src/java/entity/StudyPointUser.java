package entity;

import entity.SP_Class;
import entity.StudyPoint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.TableGenerator;

/**
 *
 * @author plaul1
 */
@Entity
@NamedQueries({
   @NamedQuery(name = "StudyPointUser.findByUsername", query = "SELECT s FROM StudyPointUser s WHERE s.userName = :username")})
public class StudyPointUser implements Serializable {
  
  private static final long serialVersionUID = 1L;
  @Id
  @TableGenerator(table = "IDs", name = "idGenerator", initialValue = 10000)
  @GeneratedValue(strategy = GenerationType.TABLE,generator = "idGenerator")
  private Integer id;

  @ManyToMany(mappedBy = "users")
  private List<SP_Class> sP_Classes;

  public List<SP_Class> getsP_Classes() {
    return sP_Classes;
  }
  
  @OneToMany(mappedBy = "studyPointUser")
  private List<StudyPoint> studyPoints = new ArrayList();

  public void setStudyPoints(List<StudyPoint> studyPoints) {
    this.studyPoints = studyPoints;
  }

  public List<StudyPoint> getStudyPoints() {
    return studyPoints;
  }
  public List<StudyPoint> getStudyPoints(int period_id) {
    List<StudyPoint> filteredPoints = new ArrayList();
    for(StudyPoint p : studyPoints){
      if(p.getTask().getSemesterPeriod().getId() == period_id){
        filteredPoints.add(p);
      }
    }
    return filteredPoints;
  }

  
  @ManyToMany
  @JoinTable(name = "studyPointUsers_roles", joinColumns = {
  @JoinColumn(name = "userName", referencedColumnName = "userName")}, inverseJoinColumns = {
  @JoinColumn(name = "roleName")})

  private List<UserRole> roles = new ArrayList();
  
  @Column(unique=true)
  private String userName;
  
  private String firstName;
  private String lastName;
  private String passwordInitial;
  private String email;
  private String phone;
  //private String UserRole;
  private String password;
  
  public void addRole(UserRole role){
    roles.add(role);
  }

  public List<UserRole> getRoles() {
    return roles;
  }
  
  public List<String> getRolesAsStrings() {
    List<String> rolesAsStrings = new ArrayList();
    for(UserRole role : roles){
      rolesAsStrings.add(role.getRoleName());
    }
    return rolesAsStrings;
  }
  
  

  public Integer getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getFullName(){
    return firstName + " "+ lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPasswordInitial() {
    return passwordInitial;
  }

  public void setPasswordInitial(String passwordInitial) {
    this.passwordInitial = passwordInitial;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

//  public String getRole() {
//    return UserRole;
//  }
//
//  public void setRole(String Role) {
//    this.UserRole = Role;
//  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public void addClass(SP_Class _class){
    sP_Classes.add(_class);
  }
  public void addStudyPoint(StudyPoint sp){
    studyPoints.add(sp);
  }
  
}
