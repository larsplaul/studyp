package entity.deploy;

import entity.StudyPointUser;
import entity.UserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-14T13:27:45")
@StaticMetamodel(UserRole.class)
public class UserRole_ { 

    public static volatile ListAttribute<UserRole, StudyPointUser> studyPointUsers;
    public static volatile SingularAttribute<UserRole, String> roleName;

}