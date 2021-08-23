import java.rmi.RemoteException;

import com.frsummit.HRM.api.server.Invoker;
import com.frsummit.HRM.api.server.config.GlobalConstance;
import com.frsummit.HRM.api.server.entity.User;
import com.frsummit.HRM.api.server.shared.InvokerPostprocessingStrategyEnum;
import com.frsummit.HRM.api.server.shared.InvokerPreprocessingStrategyEnum;
import com.frsummit.HRM.api.server.shared.command.Command;

public class Main {

    private static GlobalConstance __;

    public static void main( String[] args ) {
        __ = new GlobalConstance();

        String bean = "UserService";
        String method = "findUserByEmail";
        Command command = new Command(
                bean,
                method,
                new String[] { "java.lang.String" },
                new String[] { "henrifoko@gmail.com" },
                InvokerPreprocessingStrategyEnum.REGULAR,
                InvokerPostprocessingStrategyEnum.POJO_LIST );

        Invoker invoker;
        try {
            invoker = new Invoker();
            Object obj = invoker.exec( command );
            System.out.println( ( (User) obj ).getActive() );
        } catch ( RemoteException e ) {
            e.printStackTrace();
        }
    }
}
