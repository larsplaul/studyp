package entity.development;

import entity.SemesterPeriod;
import entity.StudyPoint;
import entity.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-14T13:27:45")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, Integer> id;
    public static volatile ListAttribute<Task, StudyPoint> studyPoints;
    public static volatile SingularAttribute<Task, Integer> maxScore;
    public static volatile SingularAttribute<Task, String> name;
    public static volatile SingularAttribute<Task, SemesterPeriod> semesterPeriod;

}