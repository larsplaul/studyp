package entity;

import entity.SP_Class;
import entity.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-05T11:29:32")
@StaticMetamodel(SemesterPeriod.class)
public class SemesterPeriod_ { 

    public static volatile SingularAttribute<SemesterPeriod, Integer> id;
    public static volatile SingularAttribute<SemesterPeriod, SP_Class> inClass;
    public static volatile ListAttribute<SemesterPeriod, Task> tasks;
    public static volatile SingularAttribute<SemesterPeriod, String> periodDescription;
    public static volatile SingularAttribute<SemesterPeriod, String> periodName;

}