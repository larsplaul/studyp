package entity.deploy;

import entity.StudyPoint;
import entity.StudyPointUser;
import entity.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-14T13:27:45")
@StaticMetamodel(StudyPoint.class)
public class StudyPoint_ { 

    public static volatile SingularAttribute<StudyPoint, Integer> id;
    public static volatile SingularAttribute<StudyPoint, Task> task;
    public static volatile SingularAttribute<StudyPoint, StudyPointUser> studyPointUser;
    public static volatile SingularAttribute<StudyPoint, Integer> score;

}