
package localTesting;

import javax.persistence.Persistence;

class dbSetup {
  
  public static void main(String[] args) {
    Persistence.generateSchema("StudyPointSystemPU", null);
  }
  
}
