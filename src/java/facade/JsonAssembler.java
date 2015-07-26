package facade;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.SP_Class;
import entity.SemesterPeriod;
import entity.StudyPoint;
import entity.StudyPointUser;
import entity.Task;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JsonAssembler {

  EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudyPointSystemPU");
  private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
  //private static final Gson gson = new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public String getAllClasses() {
    ClassFacade classFacade = new ClassFacade(emf);
    List<SP_Class> classes = classFacade.findSP_ClassEntities();
    JsonArray jsonClasses = new JsonArray();
    for (SP_Class c : classes) {
      JsonObject jo = new JsonObject();
      jo.addProperty("_id", c.getId());
      jo.addProperty("nameShort", c.getNameShort());
      jsonClasses.add(jo);
    }
    String jsonStr = gson.toJson(jsonClasses); //The JSON string is ready
    return jsonStr;
  }

  /*
   Get all classes for this student
   */
  public String getClassesForStudent(int studentId) {
    EntityManager em = getEntityManager();
    try {
      List<SP_Class> classes = em.find(StudyPointUser.class, studentId).getsP_Classes();
      JsonArray jsonClasses = new JsonArray();
      for (SP_Class c : classes) {
        JsonObject jo = new JsonObject();
        jo.addProperty("_id", c.getId());
        jo.addProperty("nameShort", c.getNameShort());
        jsonClasses.add(jo);
      }
      String jsonStr = gson.toJson(jsonClasses);
      return jsonStr;
    } finally {
      em.close();
    }
  }

  public String getClass(String id) {
    ClassFacade classFacade = new ClassFacade(emf);
    SP_Class c = classFacade.findSP_Class(id);
    JsonObject classAsJson = new JsonObject();
    classAsJson.addProperty("_id", c.getId());
    classAsJson.addProperty("nameShort", c.getNameShort());
    classAsJson.addProperty("description", c.getSemesterDescription());
    classAsJson.addProperty("maxPointForSemester", c.getMaxPointForSemester());
    classAsJson.addProperty("requiredPoints", c.getRequiredPoints());
    JsonArray jsonPeriods = new JsonArray();
    for (SemesterPeriod p : c.getPeriods()) {
      JsonObject jsonPeriod = new JsonObject();
      jsonPeriod.addProperty("_id", p.getId());
      jsonPeriod.addProperty("periodName", p.getPeriodName());
      jsonPeriods.add(jsonPeriod);
    }
    classAsJson.add("periods", jsonPeriods);
    return gson.toJson(classAsJson);
  }

  public String getFullClassInfo(String id) {
    ClassFacade classFacade = new ClassFacade(emf);
    SP_Class c = classFacade.findSP_Class(id);
    JsonObject classAsJson = new JsonObject();
    classAsJson.addProperty("_id", c.getId());
    classAsJson.addProperty("nameShort", c.getNameShort());
    classAsJson.addProperty("description", c.getSemesterDescription());
    classAsJson.addProperty("maxPointForSemester", c.getMaxPointForSemester());
    classAsJson.addProperty("requiredPoints", c.getRequiredPoints());
    JsonArray jsonStudents = new JsonArray();
    for (StudyPointUser user : c.getUsers()) {
      JsonObject jsonUser = new JsonObject();
      jsonUser.addProperty("id", user.getId());
      jsonUser.addProperty("firstName", user.getFirstName());
      jsonUser.addProperty("lastName", user.getLastName());
      jsonStudents.add(jsonUser);
    }
    classAsJson.add("students", jsonStudents);

    JsonArray jsonPeriods = new JsonArray();
    for (SemesterPeriod p : c.getPeriods()) {
      JsonObject jsonPeriod = new JsonObject();
      jsonPeriod.addProperty("_id", p.getId());
      jsonPeriod.addProperty("periodName", p.getPeriodName());
      jsonPeriods.add(jsonPeriod);
    }
    classAsJson.add("periods", jsonPeriods);
    return gson.toJson(classAsJson);
  }

  public String getStudyPointsForStudent(String classId, int studentId) {
    ClassFacade classFacade = new ClassFacade(emf);
    SP_Class c = classFacade.findSP_Class(classId);
    JsonObject classAsJson = new JsonObject();
    classAsJson.addProperty("maxPointForSemester", c.getMaxPointForSemester());
    classAsJson.addProperty("requiredPoints", c.getRequiredPoints());
    classAsJson.addProperty("studentName", "XXXXXX");
    JsonArray jsonPeriods = new JsonArray();
    for (SemesterPeriod p : c.getPeriods()) {
      JsonObject jsonPeriod = new JsonObject();
      //jsonPeriod.addProperty("_id", p.getId());
      jsonPeriod.addProperty("periodName", p.getPeriodName());
      jsonPeriod.addProperty("periodDescription", p.getPeriodDescription());
      JsonArray jsonTasks = new JsonArray();
      for (Task task : p.getTasks()) {
        JsonObject jsonTask = new JsonObject();
        jsonTask.addProperty("name", task.getName());
        jsonTask.addProperty("maxScore", task.getMaxScore());
        jsonTask.addProperty("score", task.getStudyPointForStudent(studentId).getScore());
        jsonTask.addProperty("studyPointId", task.getStudyPointForStudent(studentId).getId());
        jsonTasks.add(jsonTask);
      }
      jsonPeriod.add("tasks", jsonTasks);
      jsonPeriods.add(jsonPeriod);
    }
    classAsJson.add("periods", jsonPeriods);
    return gson.toJson(classAsJson);
  }

  /*
   Returns a JSON structure contaning 
   Used for the handout studypoints for a class/period
   1) Information about the requested Period, including the owner class
   2) All tasks for the requested Period
   3) All students for the requeste Period, and for each student a list of his studypoints for this period 
   */
  public String getPeriod(int id) {
    SemesterPeriodFacade facade = new SemesterPeriodFacade(emf);
    SemesterPeriod sp = facade.findSemesterPeriod(id);
    JsonObject periodJson = new JsonObject();
    periodJson.addProperty("id", sp.getId());
    periodJson.addProperty("classId", sp.getInClass().getId());
    periodJson.addProperty("periodName", sp.getPeriodName());
    periodJson.addProperty("periodDescription", sp.getPeriodDescription());

    JsonArray jsonTasks = new JsonArray();
    for (Task t : sp.getTasks()) {
      JsonObject jsonTask = new JsonObject();
      jsonTask.addProperty("name", t.getName());
      jsonTask.addProperty("maxScore", t.getMaxScore());
      //jsonTask.addProperty("id", t.getId()); TODO: figure out whether id is nessecary
      jsonTasks.add(jsonTask);
    }

    periodJson.add("tasks", jsonTasks);

    List<StudyPointUser> users = sp.getInClass().getUsers();
    JsonArray jsonUsers = new JsonArray();
    for (StudyPointUser user : users) {
      JsonObject jsonUser = new JsonObject();
      jsonUser.addProperty("userId", user.getId());
      jsonUser.addProperty("userId", user.getId());
      jsonUser.addProperty("fullName", user.getFullName());

      JsonArray jsonStudyPoints = new JsonArray();
      for (StudyPoint studyPoint : user.getStudyPoints(id)) {
        JsonObject jsonStudyPoint = new JsonObject();
        jsonStudyPoint.addProperty("id", studyPoint.getId());
        jsonStudyPoint.addProperty("maxScore", studyPoint.getTask().getMaxScore());
        jsonStudyPoint.addProperty("score", studyPoint.getScore());
        //jsonStudyPoint.addProperty("userId", studyPoint.getStudyPointUser().getId());//TODO Remove me
        jsonStudyPoints.add(jsonStudyPoint);
      }
      jsonUser.add("scores", jsonStudyPoints);

      jsonUsers.add(jsonUser);
    }

    periodJson.add("students", jsonUsers);
    return gson.toJson(periodJson);
  }

  public String editStudyPoints(String jsonAsString) {
    StudyPointFacade studyPointFacade = new StudyPointFacade(emf);
    JsonArray scores = new JsonParser().parse(jsonAsString).getAsJsonArray();
    for (JsonElement s : scores) {
      int id = s.getAsJsonObject().get("id").getAsInt();
      int score = s.getAsJsonObject().get("score").getAsInt();
      System.out.println(s.getAsJsonObject().get("score").getAsInt());
      System.out.println("" + id + ", " + score);
      try {
        studyPointFacade.edit(id, score);
      } catch (Exception ex) {
        Logger.getLogger(JsonAssembler.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return "Todo";
  }

  public String removeStudentsFromClass(String jsonAsString) {
    JsonObject jsonClass = new JsonParser().parse(jsonAsString).getAsJsonObject();
    String classId = jsonClass.get("id").getAsString();
    JsonArray studentsToRemoveJson = jsonClass.getAsJsonArray("studentsToRemove");
    List<Integer> studentsToRemove = new ArrayList();
    for (JsonElement studentJson : studentsToRemoveJson) {
      studentsToRemove.add(studentJson.getAsInt());
    }
    EntityManager em = getEntityManager();
    try {
      SP_Class theClass = em.find(SP_Class.class, classId);
      em.getTransaction().begin();
      for (Iterator<StudyPointUser> iter = theClass.getUsers().listIterator(); iter.hasNext();) {
        StudyPointUser user = iter.next();
        for (int idToRemove : studentsToRemove) {
           if(user.getId() == idToRemove){
             iter.remove();
           }
        }
      }
      em.merge(theClass);
      em.getTransaction().commit();
    } finally {
      em.close();
    }

    return "todo";
  }
}
