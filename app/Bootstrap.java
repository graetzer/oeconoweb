import play.test.*;
import play.jobs.*;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job {
    
    public void doJob() {
        // Load default data if the database is empty
        if(User.count() == 0) {
            Fixtures.loadModels("initial.yml");
        }
    }
    
}